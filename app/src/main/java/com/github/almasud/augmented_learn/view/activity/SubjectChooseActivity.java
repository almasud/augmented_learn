package com.github.almasud.augmented_learn.view.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.almasud.augmented_learn.BaseApplication;
import com.github.almasud.augmented_learn.R;
import com.github.almasud.augmented_learn.databinding.ActivitySubjectChooseBinding;
import com.github.almasud.augmented_learn.model.entity.Language;
import com.github.almasud.augmented_learn.model.entity.Subject;
import com.github.almasud.augmented_learn.model.util.EventMessage;
import com.github.almasud.augmented_learn.view.adapter.SubjectChooseLanguageRV;
import com.github.almasud.augmented_learn.view.util.SnackbarHelper;
import com.github.almasud.augmented_learn.viewmodel.ArVM;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class SubjectChooseActivity extends AppCompatActivity {
    private ActivitySubjectChooseBinding mViewBinding;
    private int mChooseService;
    private SubjectChooseLanguageRV mChooseLanguageRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = ActivitySubjectChooseBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());

        // Get the bundle from intent if exists
        Bundle bundle = getIntent().getBundleExtra(BaseApplication.BUNDLE);
        if (bundle != null) {
            switch (bundle.getString(BaseApplication.SERVICE_NAME)) {
                case BaseApplication.SERVICE_LEARN:
                    mChooseService = BaseApplication.LEARN;
                    break;
                case BaseApplication.SERVICE_TEST:
                    mChooseService = BaseApplication.TEST;
                    break;
                case BaseApplication.SERVICE_SCAN:
                    mChooseService = BaseApplication.SCAN;
                    break;
            }
        }

        // Set a toolbar as an actionbar
        setSupportActionBar(mViewBinding.toolbarServiceChoose.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (mChooseService == BaseApplication.LEARN)
            getSupportActionBar().setSubtitle(R.string.learn);
        else if (mChooseService == BaseApplication.TEST)
            getSupportActionBar().setSubtitle(R.string.test);
        else if (mChooseService == BaseApplication.SCAN)
            getSupportActionBar().setSubtitle(R.string.scan);

        // Change the toolbar title and subtitle font
        BaseApplication.changeToolbarTitleFont(
                this, Language.ENGLISH, Typeface.NORMAL,
                mViewBinding.toolbarServiceChoose.getRoot()
        );

        // Get an instance of ViewModel
        ArVM arVM = new ViewModelProvider(this).get(ArVM.class);
        // Get the list of live data from ArViewModel
        LiveData<List<Language>> languagesLiveData = arVM.getLanguagesLivedData();
        // Observe the list of live data
        languagesLiveData.observe(this, languages -> {
            if (languages.size() > 0) {
                // Initialize the RecyclerView adapter
                mChooseLanguageRVAdapter = new SubjectChooseLanguageRV(
                        languages, this, mChooseService
                );
                // Set a layout manager to RecyclerView
                mViewBinding.rvLanguage.setLayoutManager(
                        new LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                );
                // Set the adapter to RecyclerView
                mViewBinding.rvLanguage.setAdapter(mChooseLanguageRVAdapter);
            }
        });
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
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Subject.URL_AR_BOOK)));
        return super.onOptionsItemSelected(item);  // This makes sure onSupportNavigateUp() is called
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Register EventBus to get event message
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
