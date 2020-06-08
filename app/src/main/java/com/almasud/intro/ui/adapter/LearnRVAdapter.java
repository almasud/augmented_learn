package com.almasud.intro.ui.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.almasud.intro.R;
import com.almasud.intro.databinding.ItemRealViewBinding;
import com.almasud.intro.model.ArModel;
import com.almasud.intro.ui.activity.LearnArActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * The adapter subclass of {@link RecyclerView.Adapter} for {@link LearnArActivity}.
 *
 * @author Abdullah Almasud
 */
public class LearnRVAdapter extends RecyclerView.Adapter<LearnRVAdapter.ARModelViewHolder> {
    private ItemRealViewBinding mViewBinding;
    private List<ArModel> mArModels;
    private LearnArActivity mActivity;
    private int mRowIndex;
    private Disposable mModelDisposable;

    public LearnRVAdapter(List<ArModel> ArModels, LearnArActivity activity, int selectedItem) {
        mArModels = ArModels;
        mActivity = activity;
        mRowIndex = selectedItem;
    }

    @NonNull
    @Override
    public ARModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mViewBinding = ItemRealViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ARModelViewHolder(mViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ARModelViewHolder holder, int position) {
        ArModel ARModel = mArModels.get(position);
        holder.setARModel(ARModel);
        holder.itemView.setOnClickListener(view -> {
            // Subscribe to selectModelCallback() observable of the activity
            // to get the selected item.
            mModelDisposable = mActivity.selectModelCallback(position)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
            mRowIndex = position;
            notifyDataSetChanged();
        });
        if (mRowIndex == position)
            holder.itemView.setBackgroundColor(mActivity.getResources().getColor(R.color.colorPrimaryTransparent_66));
        else
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return mArModels.size();
    }

    public class ARModelViewHolder extends RecyclerView.ViewHolder {
        private ItemRealViewBinding viewBinding;

        public ARModelViewHolder(@NonNull ItemRealViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }

        public void setARModel(ArModel ARModel) {
            viewBinding.ivItemRealView.setImageResource(ARModel.getPhoto());
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        // Dispose the mModelDisposable
        if (mModelDisposable != null && !mModelDisposable.isDisposed()) {
            mModelDisposable.dispose();
        }
    }
}
