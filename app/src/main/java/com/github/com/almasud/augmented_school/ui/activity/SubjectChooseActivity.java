package com.github.com.almasud.augmented_school.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.com.almasud.augmented_school.BaseApplication;
import com.github.com.almasud.augmented_school.R;
import com.github.com.almasud.augmented_school.databinding.ActivitySubjectChooseBinding;
import com.github.com.almasud.augmented_school.model.entity.ArModel;
import com.github.com.almasud.augmented_school.model.entity.Subject;
import com.github.com.almasud.augmented_school.model.util.EventMessage;
import com.github.com.almasud.augmented_school.ui.util.SnackbarHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.Arrays;

public class SubjectChooseActivity extends AppCompatActivity {
    private static final String TAG = SubjectChooseActivity.class.getSimpleName();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_subject_choose, menu);
        if (mChooseService != BaseApplication.SCAN)
            menu.findItem(R.id.action_get_ar_book).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_get_ar_book)
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(BaseApplication.URL_AR_BOOK)));
        return super.onOptionsItemSelected(item);  // This makes sure onSupportNavigateUp() is called
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
                    String modelDirectoryName = null;
                    String modelDownloadURL = null;
                    int modelSubject = -1;

                    // Set the type of ArModel
                    switch (view.getId()) {
                        case R.id.wrapperVowelBengali:
                            modelSubject = Subject.SUBJECT_VOWEL_BENGALI;
                            modelDirectoryName = BaseApplication.DIRECTORY_VOWELS_BENGALI;
                            modelDownloadURL = BaseApplication.URL_VOWELS_BENGALI;
                            break;
                        case R.id.wrapperAlphabetBengali:
                            modelSubject = Subject.SUBJECT_ALPHABET_BENGALI;
                            modelDirectoryName = BaseApplication.DIRECTORY_ALPHABETS_BENGALI;
                            modelDownloadURL = BaseApplication.URL_ALPHABETS_BENGALI;
                            break;
                        case R.id.wrapperNumberBengali:
                            modelSubject = Subject.SUBJECT_NUMBER_BENGALI;
                            modelDirectoryName = BaseApplication.DIRECTORY_NUMBERS_BENGALI;
                            modelDownloadURL = BaseApplication.URL_NUMBERS_BENGALI;
                            break;
                        case R.id.wrapperAlphabetEnglish:
                            modelSubject = Subject.SUBJECT_ALPHABET_ENGLISH;
                            modelDirectoryName = BaseApplication.DIRECTORY_ALPHABETS_ENGLISH;
                            modelDownloadURL = BaseApplication.URL_ALPHABETS_ENGLISH;
                            break;
                        case R.id.wrapperNumberEnglish:
                            modelSubject = Subject.SUBJECT_NUMBER_ENGLISH;
                            modelDirectoryName = BaseApplication.DIRECTORY_NUMBERS_ENGLISH;
                            modelDownloadURL = BaseApplication.URL_NUMBERS_ENGLISH;
                            break;
                        case R.id.wrapperAnimalEnglish:
                            modelSubject = Subject.SUBJECT_ANIMAL_ENGLISH;
                            modelDirectoryName = BaseApplication.DIRECTORY_ANIMALS_ENGLISH;
                            modelDownloadURL = BaseApplication.URL_ANIMALS_ENGLISH;
                            break;
                    }

                    // Put the information into the bundle
                    bundle.putInt(ArModel.SUBJECT, modelSubject);
                    bundle.putString(ArModel.MODEL_DOWNLOAD_URL, modelDownloadURL);
                    File modelDirectory = BaseApplication.getExternalFileDirModelsRoot(
                            SubjectChooseActivity.this, modelDirectoryName
                    );
                    bundle.putSerializable(ArModel.MODEL_DIRECTORY, modelDirectory);

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
                        // Check whether the AR is supported for this device or not to avoid crashing the application
                        if (BaseApplication.isSupportedAROrShowDialog(SubjectChooseActivity.this)) {
                            File downloadDirectory = BaseApplication.getExternalFileDirModelsRoot(
                                    SubjectChooseActivity.this, ""
                            );
                            Log.d(TAG, "onAnimationEnd: modelDirectory: "+ modelDirectory);
                            Log.d(TAG, "onAnimationEnd: modelDirectory list: "+ Arrays.asList(modelDirectory.list()));

                            // Check whether the model directory contains any item or not
                            if (modelDirectory.list().length > 1) {
                                BaseApplication.getInstance()
                                        .startNewActivity(
                                                SubjectChooseActivity.this,
                                                ScanActivity.class, bundle
                                        );
                            } else {
                                // If the model directory not contains any item
                                String downloadURL = modelDownloadURL;
                                BaseApplication.setAlertDialog(
                                        SubjectChooseActivity.this, getResources().getString(R.string.action_choose),
                                        R.drawable.ic_help, getResources().getString(R.string.need_download_models),
                                        () -> {
                                            if (downloadURL != null) {
                                                BaseApplication.download(
                                                        SubjectChooseActivity.this, downloadURL, downloadDirectory
                                                );
                                            }
                                        }, null, () -> {}, null
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
