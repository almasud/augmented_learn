package com.github.almasud.augmented_learn.view.adapter;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.almasud.augmented_learn.BaseApplication;
import com.github.almasud.augmented_learn.R;
import com.github.almasud.augmented_learn.databinding.ItemSubjectChooseSubjectBinding;
import com.github.almasud.augmented_learn.model.entity.ArModel;
import com.github.almasud.augmented_learn.model.entity.Subject;
import com.github.almasud.augmented_learn.util.AppResource;
import com.github.almasud.augmented_learn.view.activity.LearnActivity;
import com.github.almasud.augmented_learn.view.activity.ScanActivity;
import com.github.almasud.augmented_learn.view.activity.SubjectChooseActivity;
import com.github.almasud.augmented_learn.view.activity.TestActivity;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * The adapter subclass of {@link RecyclerView.Adapter} for {@link SubjectChooseActivity}.
 *
 * @author Abdullah Almasud
 */
public class SubjectChooseSubjectRV extends RecyclerView.Adapter<SubjectChooseSubjectRV.SubjectViewHolder> {
    private static final String TAG = "SubjectChooseSubjectRV";
    private ItemSubjectChooseSubjectBinding mViewBinding;
    private Animation mAnimation;
    private List<Subject> mSubjects;
    private SubjectChooseActivity mActivity;
    private int mChooseService;

    public SubjectChooseSubjectRV(List<Subject> subjects, SubjectChooseActivity activity, int chooseService) {
        mSubjects = subjects;
        mActivity = activity;
        mChooseService = chooseService;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mViewBinding = ItemSubjectChooseSubjectBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        // Load an animation for navigation items
        mAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.click_item);

        return new SubjectViewHolder(mViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        // Remove margin end of the last item view
        if (position == (mSubjects.size() - 1)) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMarginEnd(0);
            holder.itemView.setLayoutParams(layoutParams);
        }

        // Get each subject and set it into UI
        Subject subject = mSubjects.get(position);
        holder.itemView.setTag(subject.getId());
        holder.setSubject(subject);
        Log.d(TAG, "onBindViewHolder: Subject Name: " + subject.getName());

        // Set the click listener for each item
        holder.itemView.setOnClickListener(view -> {
            // Start an animation for each item click
            view.startAnimation(mAnimation);
            // Set the animation listener to trigger an event
            mAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    // To avoid the block of UI (main) thread execute the tasks within a new thread.
                    new Handler().post(() -> {
                        Bundle bundle = new Bundle();
                        // Put the information into the bundle
                        bundle.putSerializable(ArModel.SUBJECT, subject);

                        // Set an activity for each service
                        if (mChooseService == BaseApplication.LEARN) {
                            BaseApplication.getInstance()
                                    .startNewActivity(
                                            mActivity, LearnActivity.class, bundle
                                    );
                        } else if (mChooseService == BaseApplication.TEST) {
                            BaseApplication.getInstance()
                                    .startNewActivity(
                                            mActivity, TestActivity.class, bundle
                                    );
                        } else if (mChooseService == BaseApplication.SCAN) {
                            // Check whether the AR is supported for this device or not to avoid crashing the application
                            if (BaseApplication.isSupportedAROrShowDialog(mActivity)) {
                                File downloadDirectory = mActivity.getExternalFilesDir(
                                        File.separator + AppResource.DIRECTORY_MODELS_ROOT
                                );
                                File modelDirectory = mActivity.getExternalFilesDir(
                                        File.separator + AppResource.getModelUri(subject.getId()).getPath()
                                );
                                Log.d(TAG, "onAnimationEnd: modelDirectory: "+ modelDirectory);
                                Log.d(TAG, "onAnimationEnd: modelDirectory list: "+ Arrays.asList(modelDirectory.list()));

                                // Check whether the model directory contains any item or not
                                if (modelDirectory.listFiles().length > 1) {
                                    BaseApplication.getInstance()
                                            .startNewActivity(
                                                    mActivity, ScanActivity.class, bundle
                                            );
                                } else {
                                    // If the model directory not contains any item
                                    String downloadURL = subject.getModelURL();
                                    BaseApplication.setAlertDialog(
                                            mActivity, null, mActivity.getResources().getString(R.string.action_choose),
                                            R.drawable.ic_help, mActivity.getResources().getString(R.string.need_download_models),
                                            () -> BaseApplication.download(
                                                    mActivity, downloadURL, downloadDirectory
                                            ), null, () -> {}, null
                                    );
                                }
                            }
                        }
                    });
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return mSubjects.size();
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {
        private ItemSubjectChooseSubjectBinding itemSubjectChooseSubjectBinding;

        public SubjectViewHolder(@NonNull ItemSubjectChooseSubjectBinding viewBinding) {
            super(viewBinding.getRoot());
            this.itemSubjectChooseSubjectBinding = viewBinding;
        }

        private void setSubject(Subject subject) {
            // Add a font according to language type
            BaseApplication.changeTextViewFont(
                    mActivity, subject.getLanguage().getId(), Typeface.NORMAL,
                    itemSubjectChooseSubjectBinding.tvSubjectName
            );
            // Set the subject name and cover photo
            itemSubjectChooseSubjectBinding.tvSubjectName.setText(subject.getName());
            // Load the images using Glide to remove the unexpected white background for gif transparent images
            Log.d(TAG, "setSubject: photo path: " + AppResource.getAssetImageUri(subject.getCoverPhoto()).getPath());
            Glide.with(mActivity).load(AppResource.getAssetImageUri(subject.getCoverPhoto()))
                    .into(itemSubjectChooseSubjectBinding.ivSubjectCover);
        }
    }
}
