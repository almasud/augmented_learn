package com.github.almasud.augmented_learn.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.almasud.augmented_learn.R;
import com.github.almasud.augmented_learn.databinding.ActivityLauncherBinding;

public class LauncherActivity extends AppCompatActivity {
    private ActivityLauncherBinding mViewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = ActivityLauncherBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());

        // Load the images using Glide to remove the unexpected white background for gif transparent images
        Glide.with(this).load(R.drawable.school_children).into(mViewBinding.ivAppLogo);
        Glide.with(this).load(R.drawable.augmented_logo).into(mViewBinding.ivAppNameOne);
        Glide.with(this).load(R.drawable.learn_logo).into(mViewBinding.ivAppNameTwo);

        // Load animations
        Animation animationFirst = AnimationUtils.loadAnimation(this, R.anim.launcher_logo_first);
        Animation animationSecond = AnimationUtils.loadAnimation(this, R.anim.launcher_logo_second);

        // Set animation listener for first
        animationFirst.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Start the second animation after end the first
                mViewBinding.ivAppNameTwo.setVisibility(View.VISIBLE);
                mViewBinding.ivAppNameTwo.startAnimation(animationSecond);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // Set animation listener for second
        animationSecond.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Start home screen after end the second animation
                new Handler().postDelayed(LauncherActivity.this::startHomeScreen, 1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // Start the first animation
        mViewBinding.ivAppNameOne.startAnimation(animationFirst);
    }

    private void startHomeScreen() {
        Intent intent = new Intent(LauncherActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
