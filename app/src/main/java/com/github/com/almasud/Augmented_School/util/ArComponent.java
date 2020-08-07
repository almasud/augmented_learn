package com.github.com.almasud.Augmented_School.util;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.github.com.almasud.Augmented_School.BaseApplication;
import com.github.com.almasud.Augmented_School.R;
import com.github.com.almasud.Augmented_School.model.entity.ArModel;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * An augmented reality (AR) component class. Consists of methods related to augmented reality (AR).
 *
 * @author Abdullah Almasud
 */
public class ArComponent {
    private static final String TAG = ArComponent.class.getSimpleName();
    // Set the min and max scales of the TransformableNode's ScaleController
    // Default min is 0.75, default max is 1.75
    private static final float MODEL_MIN_SCALE = 0.20f;
    private static final float MODEL_MAX_SCALE = 20.0f;

    private Context mContext;
    private ArFragment mArFragment;
    private AnchorNode mAnchorNode;

    private ArComponent(ArComponentBuilder arComponentBuilder) {
        mContext = arComponentBuilder.context;
        mArFragment = arComponentBuilder.arFragment;
        mAnchorNode = arComponentBuilder.anchorNode;
    }

    public Context getContext() {
        return mContext;
    }

    public ArFragment getArFragment() {
        return mArFragment;
    }

    public AnchorNode getAnchorNode() {
        return mAnchorNode;
    }

    /**
     * Builder class of {@link ArComponent}.
     */
    public static class ArComponentBuilder {
        private Context context;
        private ArFragment arFragment;
        private AnchorNode anchorNode;

        public ArComponentBuilder(Context context) {
            this.context = context;
        }

        /**
         * @param arFragment The {@link ArFragment} to get a {@link TransformableNode}.
         * @return An instance of {@link ArComponentBuilder}.
         */
        public ArComponentBuilder setArFragment(ArFragment arFragment) {
            this.arFragment = arFragment;
            return this;
        }

        /**
         * @param anchorNode The parent of the {@link TransformableNode}.
         * @return An instance of {@link ArComponentBuilder}.
         */
        public ArComponentBuilder setAnchorNode(AnchorNode anchorNode) {
            this.anchorNode = anchorNode;
            return this;
        }

        public ArComponent build() {
            return new ArComponent(this);
        }
    }

    /**
     * Set a {@link TransformableNode} of {@link ModelRenderable} from a given {@link ArModel}
     * on the top of an {@link AnchorNode}.
     * @param modelRenderable A {@link ModelRenderable} to be set on {@link TransformableNode}.
     * @param arModel An {@link ArModel} instance to get the {@link ModelRenderable}'s data.
     * @param localScale An initial scale of the {@link TransformableNode}.
     */
    public void setTransformableModel(
            @NonNull ModelRenderable modelRenderable, @NonNull ArModel arModel, final float localScale) {
        // Get a transformable node from arFragment
        TransformableNode transformableNode = new TransformableNode(mArFragment.getTransformationSystem());
        // Set the model to transformableNode
        transformableNode.setRenderable(modelRenderable);

        // Get the scale controller of transformable node to be controlled
        transformableNode.getScaleController().setMinScale(MODEL_MIN_SCALE);
        transformableNode.getScaleController().setMaxScale(MODEL_MAX_SCALE);

        // Set the local scale of the node
        transformableNode.setLocalScale(new Vector3(localScale, localScale, localScale));
        // Set the local rotation in direction (x, y, z) in degrees 180
        transformableNode.setLocalRotation(Quaternion.axisAngle(new Vector3(0, 1f, 0), 180f));
        // Set the parent after setting all local custom value
        transformableNode.setParent(mAnchorNode);
        // Finally select the transformable node to be rendered
        transformableNode.select();

        // Set a name layout at the top of the transformable node
        setTransformableModelName(transformableNode, arModel, false);
    }

    /**
     * Set a {@link TransformableNode} of {@link ModelRenderable} from a given {@link ArModel} and
     * {@link File} directory on the top of an {@link AnchorNode}.
     * @param arModel An {@link ArModel} class to get the {@link ModelRenderable}'s data.
     * @param localScale An initial scale of the {@link TransformableNode}.
     * @param fileDirectory A {@link File} directory to be load the {@link ModelRenderable}.
     */
    public void setTransformableModel(
            @NonNull ArModel arModel, final float localScale, @NonNull File fileDirectory) {
        // Get a transformable node from arFragment
        TransformableNode transformableNode = new TransformableNode(mArFragment.getTransformationSystem());

        // Start loading the models asynchronously
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            CompletableFuture<ModelRenderable> completableFuture = getCompletableFutureModel(arModel, fileDirectory);

            // If the model is not loaded, then recurse when is loaded.
            if (!completableFuture.isDone()) {
                // Set a name layout as a progress bar at the top of the transformable node
                setTransformableModelName(transformableNode, arModel, true);

                Log.i(TAG, "setTransformableModel: The " + arModel.getName() + " model is not loaded yet!, Trying to reload the model again...");
                CompletableFuture.allOf(completableFuture)
                        .thenAccept(aVoid -> setTransformableModel(arModel, localScale, fileDirectory))
                        .exceptionally(throwable -> {
                            Log.e(TAG, "setTransformableModel: Couldn't load the " + arModel.getName() + " model: " + throwable.getMessage());
                            return null;
                        });
            } else {
                // Set the model to transformableNode
                transformableNode.setRenderable(completableFuture.getNow(null));

                // Get the scale controller of transformable node to be controlled
                transformableNode.getScaleController().setMinScale(MODEL_MIN_SCALE);
                transformableNode.getScaleController().setMaxScale(MODEL_MAX_SCALE);

                // Set the local scale of the node
                transformableNode.setLocalScale(new Vector3(localScale, localScale, localScale));
                // Set the local rotation in direction (x, y, z) in degrees 180
                transformableNode.setLocalRotation(Quaternion.axisAngle(new Vector3(0, 1f, 0), 180f));
                // Set the parent after setting all local custom value
                transformableNode.setParent(mAnchorNode);
                // Finally select the transformable node to be rendered
                transformableNode.select();

                // Set a name layout at the top of the transformable node
                setTransformableModelName(transformableNode, arModel, false);
            }
        }
    }

    /**
     * Set a {@link ViewRenderable} to a {@link TransformableNode} to show a {@link Layout}
     * to be hold a name, a listen and cancel button of the {@link ArModel}.
     * @param transformableNode The {@link TransformableNode} to get the position
     * to be placed a new {@link TransformableNode} on the top of it.
     * @param arModel An {@link ArModel} to get the {@link ModelRenderable}'s data.
     */
    private void setTransformableModelName(
            @NonNull TransformableNode transformableNode,
            @NonNull ArModel arModel, boolean isProgressBar) {
        // AR Scenform required minimumSDK <= 24
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Get the layout to be display a name and hold a speak and cancel button or a progress bar
            ViewRenderable.builder().setView(mContext, R.layout.item_ar_name)
                    .build().thenAccept(viewRenderable -> {
                        // Get a transformable node from arFragment
                        TransformableNode nameTransformableNode = new TransformableNode(mArFragment.getTransformationSystem());
                        // Set the viewRenderable to the transformableNode
                        nameTransformableNode.setRenderable(viewRenderable);
                        // Set the local position of the name layout based on the transformable node.
                        nameTransformableNode.setLocalPosition(
                                new Vector3(
                                        transformableNode.getLocalPosition().x,
                                        transformableNode.getLocalPosition().y + 0.80f,
                                        transformableNode.getLocalPosition().z
                                ));
                        // Set the parent after setting all local custom value
                        nameTransformableNode.setParent(mAnchorNode);
                        // Finally select the node to be rendered
                        nameTransformableNode.select();

                        // Get the parent layout
                        View parentView = viewRenderable.getView();
                        // After clicking the cancel button remove the item.
                        parentView.findViewById(R.id.buttonCancelItemName)
                                .setOnClickListener(view -> mAnchorNode.setParent(null));

                        // Check whether isProgressBar request or not
                        if (isProgressBar) {
                            Log.i(TAG, "setTransformableNodeName: Progress bar is showing for " + arModel.getName());
                            // Hide the item name layout and show the progressbar layout
                            parentView.findViewById(R.id.wrapperItemName).setVisibility(View.GONE);
                            parentView.findViewById(R.id.wrapperProgressBarItemName).setVisibility(View.VISIBLE);
                            ((TextView) parentView.findViewById(R.id.nameProgressBarText))
                                    .setText(String.format(
                                            "Please wait, %s is loading...", arModel.getName()
                                    ));
                        } else {
                            Log.i(TAG, "setTransformableNodeName: Item name is showing for " + arModel.getName());
                            // Hide the the progressbar layout and show item name layout
                            parentView.findViewById(R.id.wrapperProgressBarItemName).setVisibility(View.GONE);
                            parentView.findViewById(R.id.wrapperItemName).setVisibility(View.VISIBLE);

                            // Get the view of name and hear button
                            ImageButton hearButton = parentView.findViewById(R.id.ibItemHear);
                            TextView nameTV = parentView.findViewById(R.id.tvItemName);
                            // Add a font according to language type
                            BaseApplication.changeTextViewFont(
                                    mContext, arModel.getSubject().getLanguage().getId(),
                                    Typeface.NORMAL, nameTV
                            );

                            // Set the name of item except vowel & alphabet category of ArModel
                            if (arModel.getExtraName() == null)
                                nameTV.setText(arModel.getName());
                            else
                                nameTV.setVisibility(View.GONE);

                            // Play the voice of the item after click on the hear button
                            hearButton.setOnClickListener(view ->
                                    BaseApplication.playVoice(arModel, false)
                            );
                        }
                    });
        }
    }

    /**
     * Used to load and get a {@link List} of {@link CompletableFuture} of {@link ModelRenderable}'s asynchronously.
     * @param models A {@link List} of {@link ArModel} to get the {@link File}s name.
     * @param fileDirectory A {@link File} directory where the {@link File}s are located.
     * @return A {@link List} of {@link CompletableFuture} of {@link ModelRenderable}'s.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<CompletableFuture<ModelRenderable>> getAllCompletableFutureModels(
            @NonNull List<ArModel> models, @NonNull File fileDirectory) {
        // Start loading the models asynchronously.
        List<CompletableFuture<ModelRenderable>> completableFutures = models.stream()
               .map(arModel -> ModelRenderable.builder()
                       .setSource(mContext, Uri.parse(fileDirectory.getAbsolutePath() + File.separator + arModel.getFileName()))
                       .build()
               ).collect(Collectors.toList());

        // If any of the models are not loaded, then recurse when all are loaded.
        if (!completableFutures.get(0).isDone()) {
            Log.i(TAG, "getAllCompletableFutureModels: The models are all not loaded yet!, Trying to reload the models again...");
            CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[models.size()]))
                    .thenAccept(aVoid -> getAllCompletableFutureModels(models, fileDirectory))
                    .exceptionally(throwable -> {
                        Log.e(TAG, "getAllCompletableFutureModels: Couldn't load the models: " + throwable.getMessage());
                        return null;
                    });
        }

        return completableFutures;
    }

    /**
     * Used to load and get a {@link ModelRenderable}'s {@link CompletableFuture} asynchronously.
     * @param arModel The {@link ArModel} to to get the {@link File}s name.
     * @param fileDirectory A {@link File} directory where the {@link File}s are located.
     * @return A {@link CompletableFuture} of {@link ModelRenderable}.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<ModelRenderable> getCompletableFutureModel(
            @NonNull ArModel arModel, @NonNull File fileDirectory) {

        return ModelRenderable.builder()
                .setSource(mContext, Uri.parse(fileDirectory.getAbsolutePath() + File.separator + arModel.getFileName())).build();
    }
}
