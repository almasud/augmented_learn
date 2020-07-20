package com.almasud.intro.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.almasud.intro.BaseApplication;
import com.almasud.intro.R;
import com.almasud.intro.databinding.ActivitySubjectChooseBinding;
import com.almasud.intro.model.entity.ArModel;
import com.almasud.intro.model.entity.Subject;
import com.almasud.intro.model.util.EventMessage;
import com.almasud.intro.ui.util.SnackbarHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SubjectChooseActivity extends AppCompatActivity {
    private ActivitySubjectChooseBinding mViewBinding;
    private Animation mAnimation;
    private int mChooseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = ActivitySubjectChooseBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());

        // Get the bundle from intent if exists
        Bundle bundle = getIntent().getBundleExtra(BaseApplication.BUNDLE);
        if (bundle != null) {
            if (bundle.getString(BaseApplication.SERVICE_NAME).equals(BaseApplication.SERVICE_LEARN))
                mChooseService = BaseApplication.LEARN;
            else if (bundle.getString(BaseApplication.SERVICE_NAME).equals(BaseApplication.SERVICE_TEST))
                mChooseService = BaseApplication.TEST;
            else if (bundle.getString(BaseApplication.SERVICE_NAME).equals(BaseApplication.SERVICE_SCAN))
                mChooseService = BaseApplication.SCAN;
        }

        // Set a toolbar as an actionbar
        setSupportActionBar(mViewBinding.toolServiceChoose.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (mChooseService == BaseApplication.LEARN)
            getSupportActionBar().setSubtitle(R.string.learn);
        else if (mChooseService == BaseApplication.TEST)
            getSupportActionBar().setSubtitle(R.string.test);
        else if (mChooseService == BaseApplication.SCAN)
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
            case R.id.wrapperVowelBengali:
                mViewBinding.wrapperVowelBengali.startAnimation(mAnimation);
                break;
            case R.id.wrapperAlphabetBengali:
                mViewBinding.wrapperAlphabetBengali.startAnimation(mAnimation);
                break;
            case R.id.wrapperNumberBengali:
                mViewBinding.wrapperNumberBengali.startAnimation(mAnimation);
                break;
            case R.id.wrapperAlphabetEnglish:
                mViewBinding.wrapperAlphabetEnglish.startAnimation(mAnimation);
                break;
            case R.id.wrapperNumberEnglish:
                mViewBinding.wrapperNumberEnglish.startAnimation(mAnimation);
                break;
            case R.id.wrapperAnimalEnglish:
                mViewBinding.wrapperAnimalEnglish.startAnimation(mAnimation);
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
                    // Set the type of ArModel
                    switch (view.getId()) {
                        case R.id.wrapperVowelBengali:
                            bundle.putInt(ArModel.SUBJECT, Subject.SUBJECT_VOWEL_BENGALI);
                            break;
                        case R.id.wrapperAlphabetBengali:
                            bundle.putInt(ArModel.SUBJECT, Subject.SUBJECT_ALPHABET_BENGALI);
                            break;
                        case R.id.wrapperNumberBengali:
                            bundle.putInt(ArModel.SUBJECT, Subject.SUBJECT_NUMBER_BENGALI);
                            break;
                        case R.id.wrapperAlphabetEnglish:
                            bundle.putInt(ArModel.SUBJECT, Subject.SUBJECT_ALPHABET_ENGLISH);
                            break;
                        case R.id.wrapperNumberEnglish:
                            bundle.putInt(ArModel.SUBJECT, Subject.SUBJECT_NUMBER_ENGLISH);
                            break;
                        case R.id.wrapperAnimalEnglish:
                            bundle.putInt(ArModel.SUBJECT, Subject.SUBJECT_ANIMAL_ENGLISH);
                            break;
                    }

                    // Set an activity for each service
                    if (mChooseService == BaseApplication.LEARN) {
                        BaseApplication.getInstance()
                                .startNewActivity(
                                        SubjectChooseActivity.this,
                                        LearnActivity.class, bundle
                                );
                    } else if (mChooseService == BaseApplication.TEST) {
                        BaseApplication.getInstance()
                                .startNewActivity(
                                        SubjectChooseActivity.this,
                                        TestActivity.class, bundle
                                );
                    } else if (mChooseService == BaseApplication.SCAN) {
                        // Check whether the AR is supported for this device or not
                        // to avoid crashing the application.
                        if (BaseApplication.isSupportedAROrShowDialog(
                                SubjectChooseActivity.this)) {
                            BaseApplication.getInstance()
                                    .startNewActivity(
                                            SubjectChooseActivity.this,
                                            ScanActivity.class, bundle
                                    );
                        }
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
