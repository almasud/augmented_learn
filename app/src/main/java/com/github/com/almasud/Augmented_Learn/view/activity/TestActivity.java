package com.github.com.almasud.Augmented_Learn.view.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.bumptech.glide.Glide;
import com.github.com.almasud.Augmented_Learn.BaseApplication;
import com.github.com.almasud.Augmented_Learn.R;
import com.github.com.almasud.Augmented_Learn.databinding.ActivityTestBinding;
import com.github.com.almasud.Augmented_Learn.model.entity.ArModel;
import com.github.com.almasud.Augmented_Learn.model.entity.Subject;
import com.github.com.almasud.Augmented_Learn.model.util.EventMessage;
import com.github.com.almasud.Augmented_Learn.util.AppResource;
import com.github.com.almasud.Augmented_Learn.view.util.SnackbarHelper;
import com.github.com.almasud.Augmented_Learn.viewmodel.ArVM;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TestActivity extends AppCompatActivity {
    private ActivityTestBinding mViewBinding;
    private Animation mAnimation;
    private List<ArModel> mArModels;
    // Declare static to hold the bundle data for starting a new screen with the bundle
    private static Bundle sBundle;
    private Subject mSubject;
    private final int ITEMS_IN_SINGLE_TEST = 4;
    private int[] mTestRandNumbers;
    private int mNumberOfTest, mNumberOfTotalTest, mNumberOfTry;
    private MediaPlayer mMediaPlayerCorrect, mMediaPlayerWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());

        // Get the bundle from intent if exists
        if (getIntent().getBundleExtra(BaseApplication.BUNDLE) != null) {
            sBundle = getIntent().getBundleExtra(BaseApplication.BUNDLE);
            mSubject = (Subject) sBundle.getSerializable(ArModel.SUBJECT);
        }

        // Set toolbar as an actionbar
        setSupportActionBar(mViewBinding.toolbarTest.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set a subtitle of the actionbar
        getSupportActionBar().setSubtitle(
                getResources().getString(R.string.test) + " | " + mSubject.getName()
        );
        // Change the toolbar title and subtitle font
        BaseApplication.changeToolbarTitleFont(
                this, mSubject.getLanguage().getId(),
                Typeface.NORMAL, mViewBinding.toolbarTest.getRoot()
        );

        // Load an animation for navigation items
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.click_item);

        // Create media players for sound effects of correct and wrong result
        mMediaPlayerCorrect = MediaPlayer.create(TestActivity.this, R.raw.correct_answer);
        mMediaPlayerWrong = MediaPlayer.create(TestActivity.this, R.raw.wrong_answer);

        // Get an instance of ViewModel
        ArVM arVM = new ViewModelProvider(this).get(ArVM.class);
        // Get the list of live data of ArModel from ArViewModel
        LiveData<List<ArModel>> arModelListLiveData = arVM.getArModelsLivedData(mSubject.getId());
        // Observe the list of ArModel from ArViewModel
        arModelListLiveData.observe(this, arModels -> {
            // Set the value of mArModels (list of ArModel).
            mArModels = arModels;

            if (mArModels.size() > 0) {
                // Add a font according to language type
                BaseApplication.changeTextViewFont(
                        this, mSubject.getLanguage().getId(),
                        Typeface.NORMAL, mViewBinding.tvTestModelType, mViewBinding.tvTestItemName
                );
                // Set the name of model type
                mViewBinding.tvTestModelType.setText(mSubject.getName());

                // Initialize the test
                startTest(null, null, true);

                // Create sound effects of correct and wrong result.
                mMediaPlayerCorrect = MediaPlayer.create(TestActivity.this, R.raw.correct_answer);
                mMediaPlayerWrong = MediaPlayer.create(TestActivity.this, R.raw.wrong_answer);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    /**
     * Start a test with a set of {@link ArModel} items {@link Random}ly.
     * It also shows a correct or wrong clicked for each {@link ArModel} item.
     *
     * @param wrapperView The parent {@link View} of an individual
     * {@link ArModel} item.
     * @param resultImageView The {@link TextView} inside and individual
     * {@link ArModel} item.
     * @param result A boolean value that indicates the {@link ArModel}
     * item to be set as correct or wrong.
     */
    private void startTest(@Nullable View wrapperView, @Nullable ImageView resultImageView, boolean result) {
        // Check the wrapper view and result text view are null or not
        if (wrapperView != null && resultImageView != null) {
            // Increment the value of mNumberOfTry
            ++mNumberOfTry;
            // For vector drawable animation
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat;
            AnimatedVectorDrawable animatedVectorDrawable;

            if (result) {
                // Play the sound effect of correct result
                if (!mMediaPlayerCorrect.isPlaying())
                    mMediaPlayerCorrect.start();

                // Set success background to wrapper
                wrapperView.setBackground(ResourcesCompat.getDrawable(
                        getResources(), R.drawable.bg_round_success, null)
                );
                // Visible and set the result text view to correct
                resultImageView.setVisibility(View.VISIBLE);
                // Set drawable resource
                resultImageView.setImageResource(R.drawable.avd_check_mark);
                resultImageView.setColorFilter(ContextCompat.getColor(this, R.color.colorSuccess));
                Drawable resultImageDrawable = resultImageView.getDrawable();
                // Set vector drawable animation
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (resultImageDrawable instanceof AnimatedVectorDrawable) {
                        animatedVectorDrawable = (AnimatedVectorDrawable) resultImageDrawable;
                        animatedVectorDrawable.start();
                    } else if (resultImageDrawable instanceof AnimatedVectorDrawableCompat){
                        animatedVectorDrawableCompat = (AnimatedVectorDrawableCompat) resultImageDrawable;
                        animatedVectorDrawableCompat.start();
                    }
                } else {
                    if (resultImageDrawable instanceof AnimatedVectorDrawableCompat){
                        animatedVectorDrawableCompat = (AnimatedVectorDrawableCompat) resultImageDrawable;
                        animatedVectorDrawableCompat.start();
                    } else {
                        // Set drawable resource
                        resultImageView.setImageResource(R.drawable.check_mark);
                    }
                }

                // Wait the amount of correct sound duration time before hide the correct result
                // text, reset the correct background of wrapper and start a new test.
                new Handler().postDelayed(() -> {
                    // Hide the result text and reset the wrong background
                    resultImageView.setVisibility(View.GONE);
                    wrapperView.setBackground(ResourcesCompat.getDrawable(
                            getResources(), R.drawable.bg_round, null)
                    );

                    // Start a new test
                    if (mArModels.size() > ITEMS_IN_SINGLE_TEST) {
                        // Remove the correct items from mAnimals
                        if (wrapperView == mViewBinding.wrapperTestOneOne)
                            mArModels.remove(mTestRandNumbers[0]);
                        else if (wrapperView == mViewBinding.wrapperTestOneTwo)
                            mArModels.remove(mTestRandNumbers[1]);
                        else if (wrapperView == mViewBinding.wrapperTestTwoOne)
                            mArModels.remove(mTestRandNumbers[2]);
                        else if (wrapperView == mViewBinding.wrapperTestTwoTwo)
                            mArModels.remove(mTestRandNumbers[3]);

                        // Reset the mTestRandItems value and start a new test
                        mTestRandNumbers = BaseApplication.getUniqueRandNumbers(mArModels.size(), ITEMS_IN_SINGLE_TEST);
                        // Increment the value of mNumberOfTest and set it to the UI
                        mViewBinding.tvTestNumberNOutOf.setText(String.format(new Locale("en-US"), "%d/%d", ++mNumberOfTest, mNumberOfTotalTest));

                        // Set test images of the Animals
                        // Load the images using Glide to remove the unexpected white background for gif transparent images
                        Glide.with(this).load(AppResource.getAssetImageUri(mArModels.get(mTestRandNumbers[0]).getPhoto()))
                                .into(mViewBinding.ivTestOneOne);
                        Glide.with(this).load(AppResource.getAssetImageUri(mArModels.get(mTestRandNumbers[1]).getPhoto()))
                                .into(mViewBinding.ivTestOneTwo);
                        Glide.with(this).load(AppResource.getAssetImageUri(mArModels.get(mTestRandNumbers[2]).getPhoto()))
                                .into(mViewBinding.ivTestTwoOne);
                        Glide.with(this).load(AppResource.getAssetImageUri(mArModels.get(mTestRandNumbers[3]).getPhoto()))
                                .into(mViewBinding.ivTestTwoTwo);

                        // Get the item to be found
                        ArModel arModel = mArModels.get(
                                mTestRandNumbers[new Random().nextInt(ITEMS_IN_SINGLE_TEST)]
                        );
                        // Play the voice of the item to be found
                        BaseApplication.playVoice(arModel, false);
                        // Also play the voice of the item to be found after click on the hear button
                        mViewBinding.ibTestHear.setOnClickListener(view ->
                                BaseApplication.playVoice(arModel, false)
                        );

                        // Set the name of the item to be found
                        mViewBinding.tvTestItemName.setText(arModel.getName());
                        // Hide the name of the item to be found for vowel & alphabet category
                        if (arModel.getExtraName() != null)
                            mViewBinding.tvTestItemName.setVisibility(View.GONE);
                    } else {
                        // After finish the test the result should be display. Since all the
                        // test state must be corrected before proceed to another test state,
                        // So here we consider only the number of try to evaluate the result.
                        if (mNumberOfTest == mNumberOfTotalTest) {
                            String dialogTitle;
                            int dialogIconRes;
                            String dialogMessage;

                            if ((mNumberOfTry - mNumberOfTotalTest) == 0) {
                                // Try without any wrong
                                dialogTitle = getResources().getString(R.string.congratulations);
                                dialogIconRes = R.drawable.ic_sentiment_satisfied_black;
                                dialogMessage = getResources().getString(R.string.try_all_correct_message);
                            } else if (mNumberOfTry <= ((mNumberOfTotalTest * ITEMS_IN_SINGLE_TEST) / 2)) {
                                // Try with some wrong
                                dialogTitle = getResources().getString(R.string.good);
                                dialogIconRes = R.drawable.ic_sentiment_satisfied_black;
                                dialogMessage = getResources().getString(R.string.try_average_correct_message);
                            } else if (((mNumberOfTotalTest * ITEMS_IN_SINGLE_TEST) - mNumberOfTry) == 0) {
                                // Try with all wrong (except corrected one)
                                dialogTitle = getResources().getString(R.string.bad);
                                dialogIconRes = R.drawable.ic_sentiment_dissatisfied_black;
                                dialogMessage = getResources().getString(R.string.try_all_wrong_message);
                            } else {
                                // Try with most wrong
                                dialogTitle = getResources().getString(R.string.not_bad);
                                dialogIconRes = R.drawable.ic_sentiment_neutral_black;
                                dialogMessage = getResources().getString(R.string.try_most_wrong_message);
                            }

                            // Set an alert dialog to show the test result
                            BaseApplication.setAlertDialog(
                                    this, null, dialogTitle, dialogIconRes, dialogMessage,
                                    () -> {
                                        // Start the test again for positive action button
                                        Intent intent = new Intent(this, TestActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.putExtra(BaseApplication.BUNDLE, sBundle);
                                        startActivity(intent);
                                    }, getResources().getString(R.string.action_test_again),
                                    this::finish, getResources().getString(R.string.action_close)
                            );
                        }
                    }
                }, mMediaPlayerCorrect.getDuration());
            } else {
                // Play the sound effect of wrong result
                if (!mMediaPlayerWrong.isPlaying())
                    mMediaPlayerWrong.start();

                // Set wrong background to wrapper
                wrapperView.setBackground(ResourcesCompat.getDrawable(
                        getResources(), R.drawable.bg_round_failed, null)
                );
                // Visible and set the result text view to wrong
                resultImageView.setVisibility(View.VISIBLE);
                // Set drawable resource
                resultImageView.setImageResource(R.drawable.avd_cross_mark);
                resultImageView.setColorFilter(ContextCompat.getColor(this, R.color.colorFailed));
                Drawable resultImageDrawable = resultImageView.getDrawable();
                // Set vector drawable animation
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (resultImageDrawable instanceof AnimatedVectorDrawable) {
                        animatedVectorDrawable = (AnimatedVectorDrawable) resultImageDrawable;
                        animatedVectorDrawable.start();
                    } else if (resultImageDrawable instanceof AnimatedVectorDrawableCompat){
                        animatedVectorDrawableCompat = (AnimatedVectorDrawableCompat) resultImageDrawable;
                        animatedVectorDrawableCompat.start();
                    }
                } else {
                    if (resultImageDrawable instanceof AnimatedVectorDrawableCompat){
                        animatedVectorDrawableCompat = (AnimatedVectorDrawableCompat) resultImageDrawable;
                        animatedVectorDrawableCompat.start();
                    } else {
                        // Set drawable resource
                        resultImageView.setImageResource(R.drawable.cross_mark);
                    }
                }

                // Wait the amount of wrong sound duration time before hide the wrong result
                // text and reset the wrong background of wrapper.
                new Handler().postDelayed(() -> {
                    // Hide the result text and reset the wrong background
                    resultImageView.setVisibility(View.GONE);
                    wrapperView.setBackground(ResourcesCompat.getDrawable(
                            getResources(), R.drawable.bg_round, null)
                    );
                }, mMediaPlayerWrong.getDuration());
            }
        } else {
            // Code for the test initialization
            // Assign the mTestRandItems value
            mTestRandNumbers = BaseApplication.getUniqueRandNumbers(mArModels.size(), ITEMS_IN_SINGLE_TEST);
            // Set the value of mNumberOfTotalTest
            mNumberOfTotalTest = (mArModels.size() - ITEMS_IN_SINGLE_TEST) + 1;
            // Increment (initialize) the value of mNumberOfTest and set it to the UI
            mViewBinding.tvTestNumberNOutOf.setText(String.format(new Locale("en-US"), "%d/%d", ++mNumberOfTest, mNumberOfTotalTest));

            // Set test images of the Animals
            // Load the images using Glide to remove the unexpected white background for gif transparent images
            Glide.with(this).load(AppResource.getAssetImageUri(mArModels.get(mTestRandNumbers[0]).getPhoto()))
                    .into(mViewBinding.ivTestOneOne);
            Glide.with(this).load(AppResource.getAssetImageUri(mArModels.get(mTestRandNumbers[1]).getPhoto()))
                    .into(mViewBinding.ivTestOneTwo);
            Glide.with(this).load(AppResource.getAssetImageUri(mArModels.get(mTestRandNumbers[2]).getPhoto()))
                    .into(mViewBinding.ivTestTwoOne);
            Glide.with(this).load(AppResource.getAssetImageUri(mArModels.get(mTestRandNumbers[3]).getPhoto()))
                    .into(mViewBinding.ivTestTwoTwo);

            // Get the item to be found
            ArModel arModel = mArModels.get(
                    mTestRandNumbers[new Random().nextInt(ITEMS_IN_SINGLE_TEST)]
            );
            // Play the voice of the item to be found
            BaseApplication.playVoice(arModel, false);
            // Also play the voice of the item to be found after click on the hear button
            mViewBinding.ibTestHear.setOnClickListener(view ->
                    BaseApplication.playVoice(arModel, false)
            );

            // Set the name of the item to be found
            mViewBinding.tvTestItemName.setText(arModel.getName());
            // Hide the name of the item to be found for vowel & alphabet category
            if (arModel.getExtraName() != null)
                mViewBinding.tvTestItemName.setVisibility(View.GONE);
        }
    }

    /**
     * Event for each item clicked
     * @param view The view to click
     */
    public void onClickItem(View view) {
        // start an animation for each item
        switch (view.getId()) {
            case R.id.wrapperTestOneOne:
                mViewBinding.wrapperTestOneOne.startAnimation(mAnimation);
                break;
            case R.id.wrapperTestOneTwo:
                mViewBinding.wrapperTestOneTwo.startAnimation(mAnimation);
                break;
            case R.id.wrapperTestTwoOne:
                mViewBinding.wrapperTestTwoOne.startAnimation(mAnimation);
                break;
            case R.id.wrapperTestTwoTwo:
                mViewBinding.wrapperTestTwoTwo.startAnimation(mAnimation);
                break;
        }
        // Set an animation listener for each item
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Set an action for each item
                switch (view.getId()) {
                    case R.id.wrapperTestOneOne:
                        // Check the result is correct or wrong
                        if (mViewBinding.tvTestItemName.getText().equals(mArModels.get(mTestRandNumbers[0]).getName()))
                            startTest(mViewBinding.wrapperTestOneOne, mViewBinding.ivTestResultOneOne, true);
                        else
                            startTest(mViewBinding.wrapperTestOneOne, mViewBinding.ivTestResultOneOne, false);
                        break;
                    case R.id.wrapperTestOneTwo:
                        // Check the result is correct or wrong
                        if (mViewBinding.tvTestItemName.getText().equals(mArModels.get(mTestRandNumbers[1]).getName()))
                            startTest(mViewBinding.wrapperTestOneTwo, mViewBinding.ivTestResultOneTwo, true);
                        else
                            startTest(mViewBinding.wrapperTestOneTwo, mViewBinding.ivTestResultOneTwo, false);
                        break;
                    case R.id.wrapperTestTwoOne:
                        // Check the result is correct or wrong
                        if (mViewBinding.tvTestItemName.getText().equals(mArModels.get(mTestRandNumbers[2]).getName()))
                            startTest(mViewBinding.wrapperTestTwoOne, mViewBinding.ivTestResultTwoOne, true);
                        else
                            startTest(mViewBinding.wrapperTestTwoOne, mViewBinding.ivTestResultTwoOne, false);
                        break;
                    case R.id.wrapperTestTwoTwo:
                        // Check the result is correct or wrong
                        if (mViewBinding.tvTestItemName.getText().equals(mArModels.get(mTestRandNumbers[3]).getName()))
                            startTest(mViewBinding.wrapperTestTwoTwo, mViewBinding.ivTestResultTwoTwo, true);
                        else
                            startTest(mViewBinding.wrapperTestTwoTwo, mViewBinding.ivTestResultTwoTwo, false);
                        break;
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Stop the media players if playing
        if (mMediaPlayerCorrect.isPlaying()) {
            mMediaPlayerCorrect.stop();
        }
        if (mMediaPlayerWrong.isPlaying()) {
            mMediaPlayerWrong.stop();
        }
        // Release the medial players
        mMediaPlayerCorrect.release();
        mMediaPlayerWrong.release();
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
