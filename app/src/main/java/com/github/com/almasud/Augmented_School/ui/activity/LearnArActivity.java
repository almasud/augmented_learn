package com.github.com.almasud.Augmented_School.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.com.almasud.Augmented_School.BaseApplication;
import com.github.com.almasud.Augmented_School.R;
import com.github.com.almasud.Augmented_School.databinding.ActivityLearnArBinding;
import com.github.com.almasud.Augmented_School.model.entity.ArModel;
import com.github.com.almasud.Augmented_School.model.entity.Subject;
import com.github.com.almasud.Augmented_School.model.util.EventMessage;
import com.github.com.almasud.Augmented_School.ui.adapter.LearnRVAdapter;
import com.github.com.almasud.Augmented_School.ui.util.SnackbarHelper;
import com.github.com.almasud.Augmented_School.util.ArComponent;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.reactivex.Completable;

public class LearnArActivity extends AppCompatActivity {
    private static final String TAG = LearnArActivity.class.getSimpleName();
    private ActivityLearnArBinding mViewBinding;
    private ArFragment mArFragment;
    private LearnRVAdapter mRVAdapter;
    private static Bundle sBundle;
    private int mSelectedItem;
    private List<CompletableFuture<ModelRenderable>> mCompletableFutureModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = ActivityLearnArBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());

        // Get the bundle from intent if exists
        if (getIntent().getBundleExtra(BaseApplication.BUNDLE) != null) {
            sBundle = getIntent().getBundleExtra(BaseApplication.BUNDLE);
            mSelectedItem = sBundle.getInt(ArModel.SELECTED_ITEM);
        }

        // Set toolbar as an actionbar
        setSupportActionBar(mViewBinding.toolbarRealView.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set a subtitle of the actionbar
        getSupportActionBar().setSubtitle(new StringBuilder(
                getResources().getString(R.string.real_view)).append(" | ")
                .append(Subject.getSubjectName(this, sBundle.getInt(ArModel.SUBJECT)))
        );

        // Initialize the RV adapter
        mRVAdapter = new LearnRVAdapter((List<ArModel>) sBundle.getSerializable(ArModel.LIST_ITEM), this, mSelectedItem);
        // Set a layout manager to RV
        mViewBinding.rvRealView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        // Set the adapter to RV
        mViewBinding.rvRealView.setAdapter(mRVAdapter);
        // Set a smooth scroll of RV
        mViewBinding.rvRealView.smoothScrollToPosition(mSelectedItem);

        // Get Fragment
        mArFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragmentAnimal);
        // Load all the models asynchronously
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mCompletableFutureModels = new ArComponent.ArComponentBuilder(this)
                    .build().getAllCompletableFutureModels((List<ArModel>) sBundle.getSerializable(ArModel.LIST_ITEM), (File) sBundle.getSerializable(ArModel.MODEL_DIRECTORY));
        }

        // After tap on plane add the model
        mArFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            Anchor anchor = hitResult.createAnchor();
            AnchorNode anchorNode = new AnchorNode(anchor);
            anchorNode.setParent(mArFragment.getArSceneView().getScene());

            // Set the selected ModelRenderable into TransformableModel
            try {
                // Set the initial scale of model.
                float modelLocalScale = (sBundle.getInt(ArModel.SUBJECT) == Subject.SUBJECT_ALPHABET_BENGALI
                        || sBundle.getInt(ArModel.SUBJECT) == Subject.SUBJECT_ALPHABET_ENGLISH)? 0.3f
                        : (sBundle.getInt(ArModel.SUBJECT) == Subject.SUBJECT_VOWEL_BENGALI
                        || sBundle.getInt(ArModel.SUBJECT) == Subject.SUBJECT_NUMBER_BENGALI
                        || sBundle.getInt(ArModel.SUBJECT) == Subject.SUBJECT_NUMBER_ENGLISH)? 0.25f
                        : (sBundle.getInt(ArModel.SUBJECT) == Subject.SUBJECT_ANIMAL_ENGLISH)? 15.0f: 1.0f;

                // Set the transformable model.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    new ArComponent.ArComponentBuilder(this)
                            .setArFragment(mArFragment)
                            .setAnchorNode(anchorNode).build()
                            .setTransformableModel(
                                    mCompletableFutureModels.get(mSelectedItem)
                                            .getNow(null),
                                    ((List<ArModel>) sBundle.getSerializable(ArModel.LIST_ITEM)).get(mSelectedItem),
                                    modelLocalScale
                            );
                }
            } catch (Exception e) {
                Log.e(TAG, "onCreate: Couldn't render the model: " + e.getMessage());
                SnackbarHelper.getInstance().showMessage(
                        this, "Error: Couldn't render the model!"
                );
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    /**
     * The callback method for selecting the {@link ArModel} model in the {@link LearnArActivity}.
     * @param position The position of the {@link ArModel} model to be viewed (Augmented).
     * @return A {@link Completable} observable to it's subscribers.
     */
    public Completable selectModelCallback(int position) {
        return Completable.create(emitter -> {
            mSelectedItem = position;
            // Signal the subscribers for completing the task
            emitter.onComplete();
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
