package com.github.com.almasud.Augmented_School.view.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.github.com.almasud.Augmented_School.BaseApplication;
import com.github.com.almasud.Augmented_School.BuildConfig;
import com.github.com.almasud.Augmented_School.R;
import com.github.com.almasud.Augmented_School.databinding.AboutDialogBinding;
import com.github.com.almasud.Augmented_School.databinding.ActivityHomeBinding;
import com.github.com.almasud.Augmented_School.model.entity.App;
import com.github.com.almasud.Augmented_School.model.entity.Language;
import com.github.com.almasud.Augmented_School.model.util.EventMessage;
import com.github.com.almasud.Augmented_School.view.util.SnackbarHelper;
import com.github.com.almasud.Augmented_School.viewmodel.AppVM;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.InputStream;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private ActivityHomeBinding mViewBinding;
    private Animation mAnimation;
    private App mApp;

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

        if (BaseApplication.isInternetAvailable(this)) {
            // Get app data from server for checking any update is available or not
            AppVM appVM = new ViewModelProvider(this).get(AppVM.class);
            LiveData<App> appLiveData = appVM.getAppLiveData();
            appLiveData.observe(this, app -> {
                mApp = app;  // Assign the value of mApp for check update manually.
                // Show an alert dialog to download the app if available any update
                if (app.getVersionCode() > BuildConfig.VERSION_CODE)
                    BaseApplication.checkUpdateWithDownload(this, app);
            });
        }
    }

    @Override
    public void onBackPressed() {
        BaseApplication.setAlertDialog(
                this, null, getResources().getString(R.string.action_choose),
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_check_update:
                if (BaseApplication.isInternetAvailable(this)) {
                    if (mApp != null) {
                        // Show an alert dialog to download the app if available any update
                        // otherwise show no update available message
                        if (mApp.getVersionCode() > BuildConfig.VERSION_CODE) {
                            BaseApplication.checkUpdateWithDownload(this, mApp);
                        } else {
                            SnackbarHelper.getInstance().showMessageWithDismiss(this, getResources().getString(R.string.not_update_available));
                        }
                    }
                } else {
                    SnackbarHelper.getInstance().showMessageWithDismiss(this, getResources().getString(R.string.not_internet_available));
                }
                return true;
            case R.id.action_share:
//                String appDownloadLink = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID;
                String appDownloadLink = getResources().getString(R.string.app_download_link);
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(
                        Intent.EXTRA_TEXT, "I am using the \"" + getResources().getString(R.string.app_name) + "\" app for preschool children." +
                                " It's really a beautiful app for learning with Augmented Reality.\n\nYou can download this app from: " +
                                appDownloadLink
                );
                startActivity(Intent.createChooser(
                        shareIntent, "Share the link of this application to..."
                ));
                return true;
            case R.id.action_report:
                Intent reportEmail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", getResources().getString(R.string.report_email), null
                ));
                reportEmail.putExtra(
                        Intent.EXTRA_SUBJECT, "Report for \"" + getResources().getString(R.string.app_name) + "\" app: "
                );
                startActivity(Intent.createChooser(
                        reportEmail, "Send an email to report a problem"
                ));
                return true;
            case R.id.action_about:
                AboutDialogBinding viewBinding = AboutDialogBinding.inflate(LayoutInflater.from(this));
                viewBinding.aboutDialogCoverPhoto.setImageResource(R.drawable.app_logo);
                viewBinding.aboutDialogTitle.setText(String.format("%s (version %s)", getResources().getString(R.string.app_name), BuildConfig.VERSION_NAME));
                // Load content text from assets
                try {
                    InputStream inputStream = getAssets().open("about_content.txt");
                    int size = inputStream.available();
                    byte[] buffer = new byte[size];
                    inputStream.read(buffer);
                    inputStream.close();
                    // Set the text into UI
                    viewBinding.aboutDialogContent.setText(new String(buffer));
                    // Change the font of text view
                    BaseApplication.changeTextViewFont(
                            this, Language.BENGALI, Typeface.NORMAL, viewBinding.aboutDialogContent
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                viewBinding.aboutDialogDeveloperName.setText(getResources().getString(R.string.developer_name));
                viewBinding.aboutDialogDeveloperName.setOnClickListener(view -> {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", getResources().getString(R.string.contact_email), null
                    ));
                    startActivity(Intent.createChooser(
                            emailIntent, "Send an email to developer"
                    ));
                });
                // Change the font of text view (s)
                BaseApplication.changeTextViewFont(
                        this, Language.ENGLISH, Typeface.NORMAL,
                        viewBinding.aboutDialogTitle, viewBinding.aboutDialogDeveloperName
                );

                // Set the custom view into alert dialog
                BaseApplication.setAlertDialog(
                        this, viewBinding.getRoot(), null, -1,
                        null, () -> {}, getResources().getString(R.string.finish)
                );
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
