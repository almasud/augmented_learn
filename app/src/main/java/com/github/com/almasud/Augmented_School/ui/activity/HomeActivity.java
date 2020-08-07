package com.github.com.almasud.Augmented_School.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.github.com.almasud.Augmented_School.BaseApplication;
import com.github.com.almasud.Augmented_School.R;
import com.github.com.almasud.Augmented_School.databinding.ActivityHomeBinding;
import com.github.com.almasud.Augmented_School.model.entity.Language;
import com.github.com.almasud.Augmented_School.model.util.EventMessage;
import com.github.com.almasud.Augmented_School.ui.util.SnackbarHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding mViewBinding;
    private Animation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());

        // Set a toolbar as an actionbar
        setSupportActionBar(mViewBinding.toolbarHome.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setSubtitle(R.string.learn_with_reality);

        // Change the toolbar title and subtitle font
        BaseApplication.changeToolbarTitleFont(
                this, Language.ENGLISH, Typeface.NORMAL,
                mViewBinding.toolbarHome.getRoot()
        );

        // Add text view font according to language type
        BaseApplication.changeTextViewFont(
                this, Language.ENGLISH, Typeface.NORMAL,
                mViewBinding.tvHomeLearn, mViewBinding.tvHomeTest, mViewBinding.tvHomeScan
        );

        // Load an animation for navigation items
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.click_item);
    }

    @Override
    public void onBackPressed() {
        BaseApplication.setAlertDialog(
                this, getResources().getString(R.string.action_choose),
                R.drawable.ic_help, getResources().getString(R.string.want_to_exit),
                () -> {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }, null, () ->{}, null
        );
    }

    /**
     * Event for each item clicked
     * @param view The view to click
     */
    public void onClickItem(View view) {
        // start an animation for each item
        switch (view.getId()) {
            case R.id.wrapperHomeLearn:
                mViewBinding.wrapperHomeLearn.startAnimation(mAnimation);
                break;
            case R.id.wrapperHomeTest:
                mViewBinding.wrapperHomeTest.startAnimation(mAnimation);
                break;
            case R.id.wrapperHomeScan:
                mViewBinding.wrapperHomeScan.startAnimation(mAnimation);
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
                        case R.id.wrapperHomeLearn:
                            bundle.putString(BaseApplication.SERVICE_NAME, BaseApplication.SERVICE_LEARN);
                            BaseApplication.getInstance()
                                    .startNewActivity(HomeActivity.this,
                                            SubjectChooseActivity.class, bundle
                                    );
                            break;
                        case R.id.wrapperHomeTest:
                            bundle.putString(BaseApplication.SERVICE_NAME, BaseApplication.SERVICE_TEST);
                            BaseApplication.getInstance()
                                    .startNewActivity(HomeActivity.this,
                                            SubjectChooseActivity.class, bundle
                                    );
                            break;
                        case R.id.wrapperHomeScan:
                            // Check whether the AR is supported for this device or not
                            // to avoid crashing the application.
                            if (BaseApplication.isSupportedAROrShowDialog(
                                    HomeActivity.this)) {
                                bundle.putString(BaseApplication.SERVICE_NAME, BaseApplication.SERVICE_SCAN);
                                BaseApplication.getInstance()
                                        .startNewActivity(HomeActivity.this,
                                                SubjectChooseActivity.class, bundle
                                        );
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

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMessage(EventMessage eventMessage) {
        SnackbarHelper.getInstance().showMessage(this, eventMessage.getMessage());
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
