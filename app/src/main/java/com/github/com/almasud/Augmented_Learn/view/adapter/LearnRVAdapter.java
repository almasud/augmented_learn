package com.github.com.almasud.Augmented_Learn.view.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.com.almasud.Augmented_Learn.R;
import com.github.com.almasud.Augmented_Learn.databinding.ItemArBinding;
import com.github.com.almasud.Augmented_Learn.model.entity.ArModel;
import com.github.com.almasud.Augmented_Learn.util.AppResource;
import com.github.com.almasud.Augmented_Learn.view.activity.LearnArActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * The adapter subclass of {@link RecyclerView.Adapter} for {@link LearnArActivity}.
 *
 * @author Abdullah Almasud
 */
public class LearnRVAdapter extends RecyclerView.Adapter<LearnRVAdapter.ArModelViewHolder> {
    private ItemArBinding mViewBinding;
    private List<ArModel> mArModels;
    private LearnArActivity mActivity;
    private int mRowIndex;

    public LearnRVAdapter(List<ArModel> arModels, LearnArActivity activity, int selectedItem) {
        mArModels = arModels;
        mActivity = activity;
        mRowIndex = selectedItem;
    }

    @NonNull
    @Override
    public ArModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mViewBinding = ItemArBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ArModelViewHolder(mViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArModelViewHolder holder, int position) {
        ArModel arModel = mArModels.get(position);
        holder.setArModel(arModel);
        holder.itemView.setOnClickListener(view -> {
            // Subscribe to selectModelCallback() observable of the activity
            // to get the selected item.
            mActivity.selectModelCallback(position)
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

    public class ArModelViewHolder extends RecyclerView.ViewHolder {
        private ItemArBinding viewBinding;

        public ArModelViewHolder(@NonNull ItemArBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }

        public void setArModel(ArModel arModel) {
            Glide.with(mActivity).load(AppResource.getAssetImageUri(arModel.getPhoto()))
                    .into(viewBinding.ivItemRealView);
        }
    }
}
