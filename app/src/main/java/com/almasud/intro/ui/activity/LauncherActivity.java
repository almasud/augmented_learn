package com.almasud.intro.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.almasud.intro.R;
import com.almasud.intro.databinding.ActivityLauncherBinding;

public class LauncherActivity extends AppCompatActivity {
    private ActivityLauncherBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLauncherBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.launcher_logo);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(LauncherActivity.this::startHomeScreen, 1000);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mBinding.imageAppLogo.startAnimation(animation);
    }

    private void startHomeScreen() {
        Intent intent = new Intent(LauncherActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
