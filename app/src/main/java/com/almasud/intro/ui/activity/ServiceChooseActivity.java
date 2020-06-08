package com.almasud.intro.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.almasud.intro.BaseApplication;
import com.almasud.intro.R;
import com.almasud.intro.databinding.ActivityServiceChooseBinding;

public class ServiceChooseActivity extends AppCompatActivity {
    private ActivityServiceChooseBinding mViewBinding;
    private Animation mAnimation;
    private static int ACTIVITY = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = ActivityServiceChooseBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());

        // Get the bundle from intent if exists
        Bundle bundle = getIntent().getBundleExtra(BaseApplication.BUNDLE);
        if (bundle != null) {
            if (bundle.getString(BaseApplication.ACTIVITY_NAME).equals(BaseApplication.ACTIVITY_LEARN))
                ACTIVITY = BaseApplication.LEARN;
            else if (bundle.getString(BaseApplication.ACTIVITY_NAME).equals(BaseApplication.ACTIVITY_TEST))
                ACTIVITY = BaseApplication.TEST;
            else if (bundle.getString(BaseApplication.ACTIVITY_NAME).equals(BaseApplication.ACTIVITY_SCAN))
                ACTIVITY = BaseApplication.SCAN;
        }

        // Set a toolbar as an actionbar
        setSupportActionBar((Toolbar) mViewBinding.toolbarLearnNTest.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (ACTIVITY == BaseApplication.LEARN)
            getSupportActionBar().setSubtitle(R.string.learn);
        else if (ACTIVITY == BaseApplication.TEST)
            getSupportActionBar().setSubtitle(R.string.test);
        else if (ACTIVITY == BaseApplication.SCAN)
            getSupportActionBar().setSubtitle(R.string.scan);

        // Load an animation for navigation items
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.click_item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    /**
     * Event for each item clicked
     * @param view The view to click
     */
    public void onClickItem(View view) {
        // start an animation for each item
        switch (view.getId()) {
            case R.id.wrapperAlphabet:
                mViewBinding.wrapperAlphabet.startAnimation(mAnimation);
                break;
            case R.id.wrapperAnimals:
                mViewBinding.wrapperAnimals.startAnimation(mAnimation);
                break;
        }
        // Set an animation listener for each item
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // To avoid the block of UI (main) thread execute the tasks within a new thread.
                new Handler().post(() -> {
                    Bundle bundle = new Bundle();
                    // Set an Activity for each item
                    switch (view.getId()) {
                        case R.id.wrapperAlphabet:
                            // Set the type of ARModel.
                            bundle.putString(BaseApplication.MODEL_TYPE, BaseApplication.MODEL_ALPHABET);

                            if (ACTIVITY == BaseApplication.LEARN) {
                                BaseApplication.getInstance()
                                        .startNewActivity(ServiceChooseActivity.this,
                                                LearnActivity.class, bundle);
                            } else if (ACTIVITY == BaseApplication.TEST) {
                                BaseApplication.getInstance()
                                        .startNewActivity(ServiceChooseActivity.this,
                                                TestActivity.class, bundle);
                            } else if (ACTIVITY == BaseApplication.SCAN) {
                                // Check whether the sceneform is supported for this device or not
                                // to avoid crashing the application.
                                if (BaseApplication.isSupportedSceneformOrShowDialog(
                                        ServiceChooseActivity.this)) {
                                    BaseApplication.getInstance()
                                            .startNewActivity(ServiceChooseActivity.this,
                                                    ScanActivity.class, bundle);
                                }

                            }
                            break;
                        case R.id.wrapperAnimals:
                            // Set the type of ARModel.
                            bundle.putString(BaseApplication.MODEL_TYPE, BaseApplication.MODEL_ANIMAL);

                            if (ACTIVITY == BaseApplication.LEARN) {
                                BaseApplication.getInstance()
                                        .startNewActivity(ServiceChooseActivity.this,
                                                LearnActivity.class, bundle);
                            } else if (ACTIVITY == BaseApplication.TEST) {
                                BaseApplication.getInstance()
                                        .startNewActivity(ServiceChooseActivity.this,
                                                TestActivity.class, bundle);
                            } else if (ACTIVITY == BaseApplication.SCAN) {
                                // Check whether the sceneform is supported for this device or not
                                // to avoid crashing the application.
                                if (BaseApplication.isSupportedSceneformOrShowDialog(
                                        ServiceChooseActivity.this)) {
                                    BaseApplication.getInstance()
                                            .startNewActivity(ServiceChooseActivity.this,
                                                    ScanActivity.class, bundle);
                                }

                            }
                            break;
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
