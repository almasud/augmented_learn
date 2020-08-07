package com.github.com.almasud.Augmented_School.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.github.com.almasud.Augmented_School.BaseApplication;
import com.github.com.almasud.Augmented_School.R;
import com.github.com.almasud.Augmented_School.databinding.ActivityTestBinding;
import com.github.com.almasud.Augmented_School.model.entity.ArModel;
import com.github.com.almasud.Augmented_School.model.entity.Subject;
import com.github.com.almasud.Augmented_School.model.util.EventMessage;
import com.github.com.almasud.Augmented_School.ui.util.SnackbarHelper;
import com.github.com.almasud.Augmented_School.viewmodel.ArViewModel;

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
    private static Bundle sBundle;
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
        if (getIntent().getBundleExtra(BaseApplication.BUNDLE) != null)
            sBundle = getIntent().getBundleExtra(BaseApplication.BUNDLE);

        // Set toolbar as an actionbar
        setSupportActionBar(mViewBinding.toolbarTest.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set a subtitle of the actionbar
        getSupportActionBar().setSubtitle(
                getResources().getString(R.string.test) + " | " +
                ((Subject) sBundle.getSerializable(ArModel.SUBJECT)).getName()
        );
        // Change the toolbar title and subtitle font
        BaseApplication.changeToolbarTitleFont(
                this, ((Subject) sBundle.getSerializable(ArModel.SUBJECT)).getLanguage().getId(),
                Typeface.NORMAL, mViewBinding.toolbarTest.getRoot()
        );

        // Load an animation for navigation items
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.click_item);

        // Create media players for sound effects of correct and wrong result
        mMediaPlayerCorrect = MediaPlayer.create(TestActivity.this, R.raw.correct_answer);
        mMediaPlayerWrong = MediaPlayer.create(TestActivity.this, R.raw.wrong_answer);

        // Get an instance of ARViewModel
        ArViewModel arViewModel = new ViewModelProvider(this).get(ArViewModel.class);
        // Get the list of live data of ArModel from ArViewModel
        LiveData<List<ArModel>> arModelListLiveData = arViewModel.getArModelsBySubjectLivedData(((Subject) sBundle.getSerializable(ArModel.SUBJECT)).getId());
        // Observe the list of ArModel from ArViewModel
        arModelListLiveData.observe(this, arModels -> {
            // Set the value of mArModels (list of ArModel).
            mArModels = arModels;

            if (mArModels.size() > 0) {
                // Add a font according to language type
                BaseApplication.changeTextViewFont(
                        this, ((Subject) sBundle.getSerializable(ArModel.SUBJECT)).getLanguage().getId(),
                        Typeface.NORMAL, mViewBinding.tvTestModelType, mViewBinding.tvTestItemName
                );
                // Set the name of model type
                mViewBinding.tvTestModelType.setText(
                        ((Subject) sBundle.getSerializable(ArModel.SUBJECT)).getName()
                );

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
     * @param resultTextView The {@link TextView} inside and individual
     * {@link ArModel} item.
     * @param result A boolean value that indicates the {@link ArModel}
     * item to be set as correct or wrong.
     */
    private void startTest(@Nullable View wrapperView, @Nullable TextView resultTextView, boolean result) {
        // Check the wrapper view and result text view are null or not
        if (wrapperView != null && resultTextView != null) {
            // Increment the value of mNumberOfTry
            ++mNumberOfTry;

            if (result) {
                // Play the sound effect of correct result
                if (!mMediaPlayerCorrect.isPlaying())
                    mMediaPlayerCorrect.start();

                // Visible and set the result text view to correct
                resultTextView.setVisibility(View.VISIBLE);
                resultTextView.setText(R.string.correct);
                resultTextView.setBackgroundColor(getResources().getColor(R.color.colorSuccess));
                // Set success background to wrapper
                wrapperView.setBackground(getResources().getDrawable(R.drawable.bg_round_success));

                // Wait the amount of correct sound duration time before hide the correct result
                // text, reset the correct background of wrapper and start a new test.
                new Handler().postDelayed(() -> {
                    // Hide the result text and reset the wrong background
                    resultTextView.setVisibility(View.GONE);
                    wrapperView.setBackground(getResources().getDrawable(R.drawable.bg_round));

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
                        mViewBinding.ivTestOneOne.setImageResource(mArModels.get(mTestRandNumbers[0]).getPhoto());
                        mViewBinding.ivTestOneTwo.setImageResource(mArModels.get(mTestRandNumbers[1]).getPhoto());
                        mViewBinding.ivTestTwoOne.setImageResource(mArModels.get(mTestRandNumbers[2]).getPhoto());
                        mViewBinding.ivTestTwoTwo.setImageResource(mArModels.get(mTestRandNumbers[3]).getPhoto());

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
                        if (arModel.getExtraName() != null && arModel.getExtraPhoto() != 0)
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
                                    this, dialogTitle, dialogIconRes, dialogMessage,
                                    () -> {
                                        // Start the test again for positive action button
                                        Intent intent = new Intent(this, TestActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.putExtra(BaseApplication.BUNDLE, sBundle);
                                        startActivity(intent);
                                    }, getResources().getString(R.string.test_again),
                                    this::finish, getResources().getString(R.string.test_finish)
                            );
                        }
                    }
                }, mMediaPlayerCorrect.getDuration());
            } else {
                // Play the sound effect of wrong result
                if (!mMediaPlayerWrong.isPlaying())
                    mMediaPlayerWrong.start();

                // Visible and set the result text view to wrong
                resultTextView.setVisibility(View.VISIBLE);
                resultTextView.setText(R.string.wrong);
                resultTextView.setBackgroundColor(getResources().getColor(R.color.colorFailed));
                // Set wrong background to wrapper
                wrapperView.setBackground(getResources().getDrawable(R.drawable.bg_round_failed));

                // Wait the amount of wrong sound duration time before hide the wrong result
                // text and reset the wrong background of wrapper.
                new Handler().postDelayed(() -> {
                    // Hide the result text and reset the wrong background
                    resultTextView.setVisibility(View.GONE);
                    wrapperView.setBackground(getResources().getDrawable(R.drawable.bg_round));
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
            mViewBinding.ivTestOneOne.setImageResource(mArModels.get(mTestRandNumbers[0]).getPhoto());
            mViewBinding.ivTestOneTwo.setImageResource(mArModels.get(mTestRandNumbers[1]).getPhoto());
            mViewBinding.ivTestTwoOne.setImageResource(mArModels.get(mTestRandNumbers[2]).getPhoto());
            mViewBinding.ivTestTwoTwo.setImageResource(mArModels.get(mTestRandNumbers[3]).getPhoto());

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
            if (arModel.getExtraName() != null && arModel.getExtraPhoto() != 0)
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
                            startTest(mViewBinding.wrapperTestOneOne, mViewBinding.tvTestResultOneOne, true);
                        else
                            startTest(mViewBinding.wrapperTestOneOne, mViewBinding.tvTestResultOneOne, false);
                        break;
                    case R.id.wrapperTestOneTwo:
                        // Check the result is correct or wrong
                        if (mViewBinding.tvTestItemName.getText().equals(mArModels.get(mTestRandNumbers[1]).getName()))
                            startTest(mViewBinding.wrapperTestOneTwo, mViewBinding.tvTestResultOneTwo, true);
                        else
                            startTest(mViewBinding.wrapperTestOneTwo, mViewBinding.tvTestResultOneTwo, false);
                        break;
                    case R.id.wrapperTestTwoOne:
                        // Check the result is correct or wrong
                        if (mViewBinding.tvTestItemName.getText().equals(mArModels.get(mTestRandNumbers[2]).getName()))
                            startTest(mViewBinding.wrapperTestTwoOne, mViewBinding.tvTestResultTwoOne, true);
                        else
                            startTest(mViewBinding.wrapperTestTwoOne, mViewBinding.tvTestResultTwoOne, false);
                        break;
                    case R.id.wrapperTestTwoTwo:
                        // Check the result is correct or wrong
                        if (mViewBinding.tvTestItemName.getText().equals(mArModels.get(mTestRandNumbers[3]).getName()))
                            startTest(mViewBinding.wrapperTestTwoTwo, mViewBinding.tvTestResultTwoTwo, true);
                        else
                            startTest(mViewBinding.wrapperTestTwoTwo, mViewBinding.tvTestResultTwoTwo, false);
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
