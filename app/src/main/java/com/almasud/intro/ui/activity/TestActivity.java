package com.almasud.intro.ui.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.almasud.intro.BaseApplication;
import com.almasud.intro.R;
import com.almasud.intro.databinding.ActivityTestBinding;
import com.almasud.intro.model.ArModel;
import com.almasud.intro.viewmodel.ArViewModel;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = TestActivity.class.getSimpleName();
    private ActivityTestBinding mViewBinding;
    private Animation mAnimation;
    private List<ArModel> mArModels;
    private int[] mTestRandNumbers;
    private final int ITEMS_IN_SINGLE_TEST = 4;
    private MediaPlayer mCorrectResultMP, mWrongResultMP;
    private int mNumberOfTest, mNumberOfTotalTest, mNumberOfTry;
    private static int MODEL_TYPE = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());

        // Get the bundle from intent if exists
        Bundle bundle = getIntent().getBundleExtra(BaseApplication.BUNDLE);
        if (bundle != null) {
            if (bundle.getString(BaseApplication.MODEL_TYPE).equals(BaseApplication.MODEL_ALPHABET))
                MODEL_TYPE = BaseApplication.ALPHABET;
            else if (bundle.getString(BaseApplication.MODEL_TYPE).equals(BaseApplication.MODEL_ANIMAL))
                MODEL_TYPE = BaseApplication.ANIMAL;
        }

        // Set toolbar as an actionbar
        setSupportActionBar((Toolbar) mViewBinding.toolbarTest.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set a subtitle of the actionbar
        if (MODEL_TYPE == BaseApplication.ALPHABET)
            getSupportActionBar().setSubtitle(new StringBuilder(
                    getResources().getString(R.string.test)).append(" | ")
                    .append(getResources().getString(R.string.alphabet))
            );
        else if (MODEL_TYPE == BaseApplication.ANIMAL)
            getSupportActionBar().setSubtitle(new StringBuilder(
                    getResources().getString(R.string.test)).append(" | ")
                    .append(getResources().getString(R.string.animal))
            );

        // Load an animation for navigation items
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.click_item);

        // Create sound effects for correct and wrong result
        mCorrectResultMP = MediaPlayer.create(TestActivity.this, R.raw.correct_answer);
        mWrongResultMP = MediaPlayer.create(TestActivity.this, R.raw.wrong_answer);

        // Get the Animals live data from AnimalVM and Set the Test items
        ArViewModel arViewModel = new ViewModelProvider(this).get(ArViewModel.class);
        // Get the list of live data of ARModel from ARViewModel
        LiveData<List<ArModel>> arModelListLiveData =
                (MODEL_TYPE == BaseApplication.ALPHABET)? arViewModel.getAlphabetsLivedData()
                        : arViewModel.getAnimalsLivedData();

        // Observe the list of ArModel from ArViewModel
        arModelListLiveData.observe(this, arModels -> {
            // Set the value of mArModels (list of ArModel).
            mArModels = arModels;

            if (mArModels.size() > 0) {
                // Set the name of model type
                if (MODEL_TYPE == BaseApplication.ALPHABET)
                    mViewBinding.tvTestModelType.setText(R.string.alphabet);
                else if (MODEL_TYPE == BaseApplication.ANIMAL)
                    mViewBinding.tvTestModelType.setText(R.string.animal);

                // Initialize the test
                startTest(null, null, true);

                // Create sound effects of correct and wrong result.
                mCorrectResultMP = MediaPlayer.create(TestActivity.this, R.raw.correct_answer);
                mWrongResultMP = MediaPlayer.create(TestActivity.this, R.raw.wrong_answer);
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
                // Set the sound effects of correct result
                if (!mCorrectResultMP.isPlaying())
                    mCorrectResultMP.start();

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
                        mTestRandNumbers = getTestRandNumbers(mArModels.size());
                        // Increment the value of mNumberOfTest and set it to the UI
                        mViewBinding.tvTestNumberNOutOf.setText(String.format(new Locale("en-US"), "%d/%d", ++mNumberOfTest, mNumberOfTotalTest));

                        // Set test images of the Animals
                        mViewBinding.ivTestOneOne.setImageResource(mArModels.get(mTestRandNumbers[0]).getPhoto());
                        mViewBinding.ivTestOneTwo.setImageResource(mArModels.get(mTestRandNumbers[1]).getPhoto());
                        mViewBinding.ivTestTwoOne.setImageResource(mArModels.get(mTestRandNumbers[2]).getPhoto());
                        mViewBinding.ivTestTwoTwo.setImageResource(mArModels.get(mTestRandNumbers[3]).getPhoto());

                        // Get the name of the item to be found
                        String name = mArModels.get(
                                mTestRandNumbers[new Random().nextInt(ITEMS_IN_SINGLE_TEST)]
                        ).getName();
                        // Speak the name of the item to be found
                        BaseApplication.speak(name);
                        // After click on the hear button speak the name of the item to be found
                        mViewBinding.ibTestHear.setOnClickListener(view ->
                                BaseApplication.speak(name)
                        );

                        // Set the name of the item to be found
                        mViewBinding.tvTestItemName.setText(name);
                        // Hide the name of the item to be found for Alphabet type of ARModel.
                        if (MODEL_TYPE == BaseApplication.ALPHABET)
                            mViewBinding.tvTestItemName.setVisibility(View.GONE);
                    } else {
                        // After finish the test the result should be display. Since all the
                        // test state must be corrected before proceed to another test state,
                        // So here we consider only the number of try to evaluate the result.
                        if (mNumberOfTest == mNumberOfTotalTest) {
                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

                            if ((mNumberOfTry - mNumberOfTotalTest) == 0) {
                                // Try without any wrong
                                dialogBuilder.setTitle(getResources().getString(R.string.congratulations));
                                dialogBuilder.setIcon(R.drawable.ic_sentiment_satisfied_black);
                                dialogBuilder.setMessage(getResources().getString(R.string.try_all_correct_message));
                            } else if (mNumberOfTry <= ((mNumberOfTotalTest * ITEMS_IN_SINGLE_TEST) / 2)) {
                                // Try with some wrong
                                dialogBuilder.setTitle(getResources().getString(R.string.good));
                                dialogBuilder.setIcon(R.drawable.ic_sentiment_satisfied_black);
                                dialogBuilder.setMessage(getResources().getString(R.string.try_average_correct_message));
                            } else if (((mNumberOfTotalTest * ITEMS_IN_SINGLE_TEST) - mNumberOfTry) == 0) {
                                // Try with all wrong (except corrected one)
                                dialogBuilder.setTitle(getResources().getString(R.string.bad));
                                dialogBuilder.setIcon(R.drawable.ic_sentiment_dissatisfied_black);
                                dialogBuilder.setMessage(getResources().getString(R.string.try_all_wrong_message));
                            } else {
                                // Try with most wrong
                                dialogBuilder.setTitle(getResources().getString(R.string.not_bad));
                                dialogBuilder.setIcon(R.drawable.ic_sentiment_neutral_black);
                                dialogBuilder.setMessage(getResources().getString(R.string.try_most_wrong_message));
                            }

                            dialogBuilder.setCancelable(false);
                            dialogBuilder.setPositiveButton(getResources().getString(R.string.test_again), (dialog, which) -> {
                                // Start the test again
                                Intent intent = new Intent(this, TestActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            });
                            dialogBuilder.setNegativeButton(getResources().getString(R.string.test_finish), (dialogInterface, i) -> {
                                // Exit (finish) the test screen (Activity)
                                finish();
                            });
                            AlertDialog dialog = dialogBuilder.create();
                            dialog.show();
                            // This line always placed after the dialog.show() otherwise get a Null Pinter Exception.
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
                            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setAllCaps(false);
                        }
                    }
                }, mCorrectResultMP.getDuration());
            } else {
                // Set the sound effects of wrong result
                if (!mWrongResultMP.isPlaying())
                    mWrongResultMP.start();

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
                }, mWrongResultMP.getDuration());
            }
        } else {
            // Code for the test initialization
            // Assign the mTestRandItems value
            mTestRandNumbers = getTestRandNumbers(mArModels.size());
            // Set the value of mNumberOfTotalTest
            mNumberOfTotalTest = (mArModels.size() - ITEMS_IN_SINGLE_TEST) + 1;
            // Increment (initialize) the value of mNumberOfTest and set it to the UI
            mViewBinding.tvTestNumberNOutOf.setText(String.format(new Locale("en-US"), "%d/%d", ++mNumberOfTest, mNumberOfTotalTest));

            // Set test images of the Animals
            mViewBinding.ivTestOneOne.setImageResource(mArModels.get(mTestRandNumbers[0]).getPhoto());
            mViewBinding.ivTestOneTwo.setImageResource(mArModels.get(mTestRandNumbers[1]).getPhoto());
            mViewBinding.ivTestTwoOne.setImageResource(mArModels.get(mTestRandNumbers[2]).getPhoto());
            mViewBinding.ivTestTwoTwo.setImageResource(mArModels.get(mTestRandNumbers[3]).getPhoto());

            // Get the name of the item to be found
            String name = mArModels.get(
                    mTestRandNumbers[new Random().nextInt(ITEMS_IN_SINGLE_TEST)]
            ).getName();
            // Speak the name of the item to be found
            BaseApplication.speak(name);
            // After click on the hear button speak the name of the item to be found
            mViewBinding.ibTestHear.setOnClickListener(view -> BaseApplication.speak(name));

            // Set the name of the item to be found
            mViewBinding.tvTestItemName.setText(name);
            // Hide the name of the item to be found for Alphabet type of ArModel
            if (MODEL_TYPE == BaseApplication.ALPHABET)
                mViewBinding.tvTestItemName.setVisibility(View.GONE);
        }
    }

    /**
     * Generate the number of TOTAL_TEST_ITEM int[] array of unique {@link Random}
     * number from the given boundNumber.
     *
     * @param boundNumber The bound number to be {@link Random}.
     * @return The number of TOTAL_TEST_ITEM int[] array of unique {@link Random} number.
     */
    public int[] getTestRandNumbers(int boundNumber) {
        // Random set to hold unique random number.
        Set<Integer> randSet = new HashSet<>(ITEMS_IN_SINGLE_TEST);
        // Add resultSize of random numbers to set
        while (randSet.size() < ITEMS_IN_SINGLE_TEST)
            while (!randSet.add(new Random().nextInt(boundNumber)));
        // Convert the randSet into an integer array to return
        int[] randArray = new int[ITEMS_IN_SINGLE_TEST];
        int i = 0;
        for (Integer integer : randSet)
            randArray[i++] = integer;
        return randArray;
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
        if (mCorrectResultMP.isPlaying()) {
            mCorrectResultMP.stop();
            Log.i(TAG, "The audio file for correct result is successfully stopped.");
        }
        if (mWrongResultMP.isPlaying()) {
            mWrongResultMP.stop();
            Log.i(TAG, "The audio file for wrong result is successfully stopped.");
        }
        // Release the medial players
        mCorrectResultMP.release();
        mWrongResultMP.release();
    }
}
