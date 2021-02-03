package com.github.com.almasud.Augmented_Learn.view.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.github.com.almasud.Augmented_Learn.BaseApplication;
import com.github.com.almasud.Augmented_Learn.R;
import com.github.com.almasud.Augmented_Learn.databinding.FragmentLearnBinding;
import com.github.com.almasud.Augmented_Learn.model.entity.ArModel;
import com.github.com.almasud.Augmented_Learn.util.AppResource;
import com.github.com.almasud.Augmented_Learn.view.activity.LearnActivity;

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

    public LearnFragment(ArModel arModel) {
        mArModel = arModel;
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
        // Load the images using Glide to remove the unexpected white background for gif transparent images
        // Set the photo and name of ArModel
        Glide.with(this).load(AppResource.getAssetImageUri(mArModel.getPhoto()))
                .into(mViewBinding.ivLearnItem);
        // Add text view font according to language type
        BaseApplication.changeTextViewFont(
                getContext(), mArModel.getSubject().getLanguage().getId(), Typeface.NORMAL,
                mViewBinding.tvLearnName
        );

        // Check whether the extra photo and name of ArModel is exists or not
        if (mArModel.getExtraName() != null) {
            mViewBinding.ivLearnExtra.setVisibility(View.VISIBLE);
            Glide.with(this).load(AppResource.getAssetImageUri(mArModel.getExtraPhoto()))
                    .into(mViewBinding.ivLearnExtra);
            mViewBinding.tvLearnName.setText(mArModel.getExtraName());
        } else {
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
