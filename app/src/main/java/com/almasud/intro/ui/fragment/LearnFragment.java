package com.almasud.intro.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.almasud.intro.BaseApplication;
import com.almasud.intro.R;
import com.almasud.intro.databinding.FragmentLearnBinding;
import com.almasud.intro.model.entity.ArModel;
import com.almasud.intro.ui.activity.LearnActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A {@link Fragment} subclass for rendering a page for each animal.
 *
 * @author Abdullah Almasud
 */
public class LearnFragment extends Fragment {
    private FragmentLearnBinding mViewBinding;
    private ArModel mArModel;

    public LearnFragment(ArModel ARModel) {
        mArModel = ARModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mViewBinding = FragmentLearnBinding.inflate(getLayoutInflater(), container, false);
        // Load the animation for real view button
        Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.click_item);
        // start an animation for real view button
        mViewBinding.ivLearnRealView.startAnimation(animation);
        return mViewBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Set the photo of ArModel
        mViewBinding.ivLearn.setImageResource(mArModel.getPhoto());

        // Set the label photo or name of ArModel
        if (mArModel.getModelType().equals(BaseApplication.MODEL_ALPHABET)) {
            mViewBinding.ivLearnLabel.setVisibility(View.VISIBLE);
            mViewBinding.ivLearnLabel.setImageResource(mArModel.getLabelPhoto());
        } else if (mArModel.getModelType().equals(BaseApplication.MODEL_NUMBER)) {
            mViewBinding.tvLearnName.setVisibility(View.VISIBLE);
            mViewBinding.tvLearnName.setText(mArModel.getName());
        } else if (mArModel.getModelType().equals(BaseApplication.MODEL_ANIMAL)) {
            mViewBinding.tvLearnName.setVisibility(View.VISIBLE);
            mViewBinding.tvLearnName.setText(mArModel.getName());
        }

        // Set a click lister for the real view button
        mViewBinding.ivLearnRealView.setOnClickListener(view -> {
            LearnActivity activity = (LearnActivity) getActivity();
            // Set the id of ARModel as an argument to the callback method of the activity.
            activity.getArViewCallback(mArModel.getId())
                    .observeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });
    }
}
