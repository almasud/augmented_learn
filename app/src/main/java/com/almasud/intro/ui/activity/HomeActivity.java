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
import com.almasud.intro.databinding.ActivityHomeBinding;
import com.almasud.intro.model.util.EventMessage;
import com.almasud.intro.ui.util.SnackbarHelper;

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
        setSupportActionBar((Toolbar) mViewBinding.toolbarHome.getRoot());
        getSupportActionBar().setSubtitle(R.string.learn_with_reality);

        // Load an animation for navigation items
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.click_item);
    }

    /**
     * Event for each item clicked
     * @param view The view to click
     */
    public void onClickItem(View view) {
        // start an animation for each item
        switch (view.getId()) {
            case R.id.wrapperLearn:
                mViewBinding.wrapperLearn.startAnimation(mAnimation);
                break;
            case R.id.wrapperTest:
                mViewBinding.wrapperTest.startAnimation(mAnimation);
                break;
            case R.id.wrapperScan:
                mViewBinding.wrapperScan.startAnimation(mAnimation);
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
                        case R.id.wrapperLearn:
                            bundle.putString(BaseApplication.SERVICE_NAME, BaseApplication.SERVICE_LEARN);
                            BaseApplication.getInstance()
                                    .startNewActivity(HomeActivity.this,
                                            ServiceChooseActivity.class, bundle
                                    );
                            break;
                        case R.id.wrapperTest:
                            bundle.putString(BaseApplication.SERVICE_NAME, BaseApplication.SERVICE_TEST);
                            BaseApplication.getInstance()
                                    .startNewActivity(HomeActivity.this,
                                            ServiceChooseActivity.class, bundle
                                    );
                            break;
                        case R.id.wrapperScan:
                            bundle.putString(BaseApplication.SERVICE_NAME, BaseApplication.SERVICE_SCAN);
                            BaseApplication.getInstance()
                                    .startNewActivity(HomeActivity.this,
                                            ServiceChooseActivity.class, bundle
                                    );
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
