package com.github.com.almasud.Augmented_School.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.github.com.almasud.Augmented_School.R;
import com.github.com.almasud.Augmented_School.databinding.ActivityLauncherBinding;

public class LauncherActivity extends AppCompatActivity {
    private ActivityLauncherBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLauncherBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

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
                mBinding.imageAppNameTwo.setVisibility(View.VISIBLE);
                mBinding.imageAppNameTwo.startAnimation(animationSecond);
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
        mBinding.imageAppNameOne.startAnimation(animationFirst);
    }

    private void startHomeScreen() {
        Intent intent = new Intent(LauncherActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
