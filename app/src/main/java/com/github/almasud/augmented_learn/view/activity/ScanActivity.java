package com.github.almasud.augmented_learn.view.activity;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.github.almasud.augmented_learn.BaseApplication;
import com.github.almasud.augmented_learn.R;
import com.github.almasud.augmented_learn.databinding.ActivityScanBinding;
import com.github.almasud.augmented_learn.model.entity.ArModel;
import com.github.almasud.augmented_learn.model.entity.Subject;
import com.github.almasud.augmented_learn.model.util.EventMessage;
import com.github.almasud.augmented_learn.util.AppResource;
import com.github.almasud.augmented_learn.view.fragment.ScanArFragment;
import com.github.almasud.augmented_learn.view.util.SnackbarHelper;
import com.github.almasud.augmented_learn.util.ArComponent;
import com.github.almasud.augmented_learn.viewmodel.ArVM;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.Frame;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.rendering.ModelRenderable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * The class to be scanned images of a pre-generated augmented image database and place
 * it's associated model on the top of the image.
 */
public class ScanActivity extends AppCompatActivity {
    private static final String TAG = "ScanActivity";
    private ActivityScanBinding mViewBinding;
    private ScanArFragment mScanArFragment;
    // Augmented image and its associated center pose anchor, keyed by the augmented
    // image in the database.
    private final Map<AugmentedImage, AnchorNode> mAugmentedImageMap = new HashMap<>();
    private List<ArModel> mArModels = new ArrayList<>();
    private static Subject sSubject;
    private List<CompletableFuture<ModelRenderable>> mCompletableFutureModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = ActivityScanBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());

        // Get the bundle from intent if exists
        if (getIntent().getBundleExtra(BaseApplication.BUNDLE) != null)
            sSubject = ((Subject) getIntent().getBundleExtra(BaseApplication.BUNDLE)
                    .getSerializable(ArModel.SUBJECT)
            );

        // Set toolbar as an actionbar
        setSupportActionBar(mViewBinding.toolbarScan.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set a subtitle of the actionbar
        getSupportActionBar().setSubtitle(
                getResources().getString(R.string.real_view) + " | " + sSubject.getName()
        );
        // Change the toolbar title and subtitle font
        BaseApplication.changeToolbarTitleFont(
                this, sSubject.getLanguage().getId(),
                Typeface.NORMAL, mViewBinding.toolbarScan.getRoot()
        );

        // Set the augmented image database
        ScanArFragment.setImageDatabase(sSubject.getId());

        mScanArFragment = (ScanArFragment) getSupportFragmentManager().findFragmentById(R.id.ArFragmentScan);
        mScanArFragment.getArSceneView().getScene().addOnUpdateListener(this::onUpdateFrame);

        // Get an instance of ViewModel
        ArVM arVM = new ViewModelProvider(this).get(ArVM.class);
        // Get the list of live data of ArModel from ArViewModel
        LiveData<List<ArModel>> arModelListLiveData = arVM.getArModelsLivedData(sSubject.getId());
        // Observe the list of ArModel from ArViewModel
        arModelListLiveData.observe(this, arModels -> {
            // Set the value of mARModels (list of ARModel)
            mArModels = arModels;

            // Load all the models asynchronously
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mCompletableFutureModels =
                        new ArComponent.ArComponentBuilder(this).build()
                                .getAllCompletableFutureModels(
                                        arModels, getExternalFilesDir(
                                                File.separator + AppResource.getModelUri(sSubject.getId()).getPath()
                                        )
                                );
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAugmentedImageMap.isEmpty()) {
            mViewBinding.ivFitToScan.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Registered with the Sceneform Scene object, this method is called at the start
     * of each frame.
     *
     * @param frameTime Time since last frame.
     */
    private void onUpdateFrame(FrameTime frameTime) {
        Frame frame = mScanArFragment.getArSceneView().getArFrame();

        // If there is no frame, just return.
        if (frame == null) {
            return;
        }

        Collection<AugmentedImage> updatedAugmentedImages =
                frame.getUpdatedTrackables(AugmentedImage.class);
        for (AugmentedImage augmentedImage : updatedAugmentedImages) {
            switch (augmentedImage.getTrackingState()) {
                case PAUSED:
                    // When an image is in PAUSED state, but the camera is not PAUSED, it has
                    // been detected, but not yet tracked.
                    SnackbarHelper.getInstance().showMessage(this, "Image detected but not tracked yet.");
                    Log.i(TAG, "onUpdateFrame: " + "Detected Image " + (augmentedImage.getIndex() + 1));
                    break;

                case TRACKING:
                    // Have to switch to UI Thread to update View.
                    mViewBinding.ivFitToScan.setVisibility(View.GONE);

                    // Create a new anchor for newly found images.
                    if (!mAugmentedImageMap.containsKey(augmentedImage)) {
                        AnchorNode anchorNode = new AnchorNode();
                        // Set the anchor based on the center of the image.
                        anchorNode.setAnchor(augmentedImage.createAnchor(augmentedImage.getCenterPose()));

                        // Set the detected ModelRenderable into TransformableModel
                        try {
                            // Set the initial scale of model
                            float modelLocalScale = (sSubject.getId() == Subject.BENGALI_ALPHABET
                                    || sSubject.getId() == Subject.ENGLISH_ALPHABET)? 0.3f
                                    : (sSubject.getId() == Subject.BENGALI_VOWEL
                                    || sSubject.getId() == Subject.BENGALI_NUMBER
                                    || sSubject.getId() == Subject.ENGLISH_NUMBER)? 0.25f
                                    : (sSubject.getId() == Subject.ENGLISH_ANIMAL)? 15.0f: 1.0f;

                            // Set the transformable model
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                new ArComponent.ArComponentBuilder(this)
                                        .setArFragment(mScanArFragment)
                                        .setAnchorNode(anchorNode).build()
                                        .setTransformableModel(
                                                mCompletableFutureModels.get(augmentedImage.getIndex())
                                                        .getNow(null),
                                                mArModels.get(augmentedImage.getIndex()),
                                                modelLocalScale
                                        );
                            }
                        } catch (Exception e) {
                            Log.e(TAG, "onUpdateFrame: Couldn't render the model: " + e.getMessage());
                            SnackbarHelper.getInstance().showMessage(
                                    this, "Error: Couldn't render the model!"
                            );
                        }

                        // Put the augmentedImage with anchorNode to keep the tracked
                        mAugmentedImageMap.put(augmentedImage, anchorNode);
                        // After setting all set the anchorNode as a child of ArFragment
                        mScanArFragment.getArSceneView().getScene().addChild(anchorNode);
                    }
                    break;

                case STOPPED:
                    mAugmentedImageMap.remove(augmentedImage);
                    break;
            }
        }
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
