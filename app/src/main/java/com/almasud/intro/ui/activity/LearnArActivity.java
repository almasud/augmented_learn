package com.almasud.intro.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.almasud.intro.BaseApplication;
import com.almasud.intro.R;
import com.almasud.intro.databinding.ActivityLearnArBinding;
import com.almasud.intro.model.entity.ArModel;
import com.almasud.intro.ui.adapter.LearnRVAdapter;
import com.almasud.intro.ui.util.SnackbarHelper;
import com.almasud.intro.util.ArComponent;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.reactivex.Completable;

public class LearnArActivity extends AppCompatActivity {
    private static final String TAG = LearnArActivity.class.getSimpleName();
    private ActivityLearnArBinding mViewBinding;
    private ArFragment mArFragment;
    private LearnRVAdapter mRVAdapter;
    private List<ArModel> mArModels;
    private static int MODEL_TYPE = -1;
    private int mSelectedItem = -1;
    private List<CompletableFuture<ModelRenderable>> mCompletableFutureModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = ActivityLearnArBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());

        // Get the bundle from intent if exists
        Bundle bundle = getIntent().getBundleExtra(BaseApplication.BUNDLE);
        if (bundle != null) {
            // Get the list of ArModel.
            mArModels = (List<ArModel>) bundle.getSerializable(BaseApplication.ITEM_LIST);

            // Get the type of ArModel
            if (bundle.getString(BaseApplication.MODEL_TYPE).equals(BaseApplication.MODEL_ALPHABET))
                MODEL_TYPE = BaseApplication.ALPHABET;
            else if (bundle.getString(BaseApplication.MODEL_TYPE).equals(BaseApplication.MODEL_NUMBER))
                MODEL_TYPE = BaseApplication.NUMBER;
            else if (bundle.getString(BaseApplication.MODEL_TYPE).equals(BaseApplication.MODEL_ANIMAL))
                MODEL_TYPE = BaseApplication.ANIMAL;

            // Get the selected item of ArModel.
            mSelectedItem = bundle.getInt(BaseApplication.SELECTED_ITEM, 0);
        }

        // Set toolbar as an actionbar
        setSupportActionBar((Toolbar) mViewBinding.toolbarRealView.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set a subtitle of the actionbar
        if (MODEL_TYPE == BaseApplication.ALPHABET)
            getSupportActionBar().setSubtitle(new StringBuilder(
                    getResources().getString(R.string.real_view)).append(" | ")
                    .append(getResources().getString(R.string.alphabet))
            );
        else if (MODEL_TYPE == BaseApplication.NUMBER)
            getSupportActionBar().setSubtitle(new StringBuilder(
                    getResources().getString(R.string.real_view)).append(" | ")
                    .append(getResources().getString(R.string.number))
            );
        else if (MODEL_TYPE == BaseApplication.ANIMAL)
            getSupportActionBar().setSubtitle(new StringBuilder(
                    getResources().getString(R.string.real_view)).append(" | ")
                    .append(getResources().getString(R.string.animal))
            );

        // Initialize the RV adapter
        mRVAdapter = new LearnRVAdapter(mArModels, this, mSelectedItem);
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
                    .build().getAllCompletableFutureModels(mArModels);
        }

        // After tap on plane add the model
        mArFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            Anchor anchor = hitResult.createAnchor();
            AnchorNode anchorNode = new AnchorNode(anchor);
            anchorNode.setParent(mArFragment.getArSceneView().getScene());

            // Set the selected ModelRenderable into TransformableModel
            try {
                // Set the initial scale of model.
                float modelLocalScale = (MODEL_TYPE == BaseApplication.ALPHABET)? 0.3f
                        : (MODEL_TYPE == BaseApplication.NUMBER)? 0.25f: 15.0f;

                // Set the transformable model.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    new ArComponent.ArComponentBuilder(this)
                            .setArFragment(mArFragment)
                            .setAnchorNode(anchorNode).build()
                            .setTransformableModel(
                                    mCompletableFutureModels.get(mSelectedItem)
                                            .getNow(null),
                                    mArModels.get(mSelectedItem), modelLocalScale
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
}
