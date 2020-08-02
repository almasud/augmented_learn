package com.github.com.almasud.Augmented_School.ui.fragment;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.github.com.almasud.Augmented_School.model.entity.ArModel;
import com.github.com.almasud.Augmented_School.model.entity.Subject;
import com.github.com.almasud.Augmented_School.ui.util.SnackbarHelper;
import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.sceneform.ux.ArFragment;

import java.io.IOException;
import java.io.InputStream;

/**
 * Extend the {@link ArFragment} to customize the ARCore session configuration to
 * include Augmented Images for {@link ArModel}.
 */
public class ScanArFragment extends ArFragment {
  private static final String TAG = ScanArFragment.class.getSimpleName();

  // This is a pre-created image database containing the images. The index of the db
  // must be same as it's model class index to get the expected result.
  private static String sImageDbPath;

  // Augmented image configuration and rendering.
  // Load a single image (true) or a pre-generated image database (false).
  private static final boolean USE_SINGLE_IMAGE = false;

  @Override
  public View onCreateView(
          LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);

    // Turn off the plane discovery since we're only looking for images
    getPlaneDiscoveryController().hide();
    getPlaneDiscoveryController().setInstructionView(null);
    getArSceneView().getPlaneRenderer().setEnabled(false);
    return view;
  }

  @Override
  protected Config getSessionConfiguration(Session session) {
    Config config = super.getSessionConfiguration(session);
    if (!setupAugmentedImageDatabase(config, session)) {
      SnackbarHelper.getInstance()
          .showError(getActivity(), "Could not setup augmented image database");
    }
    return config;
  }

  private boolean setupAugmentedImageDatabase(Config config, Session session) {
    AugmentedImageDatabase augmentedImageDatabase;

    AssetManager assetManager = getContext() != null ? getContext().getAssets() : null;
    if (assetManager == null) {
      Log.e(TAG, "Context is null, cannot initialize image database.");
      return false;
    }

    // There are two ways to configure an AugmentedImageDatabase:
    // 1. Add Bitmap to DB directly
    // 2. Load a pre-built AugmentedImageDatabase
    // Option 2) has
    // * shorter setup time
    // * doesn't require images to be packaged in apk.

    // load a pre-existing augmented image database.
    try (InputStream is = getContext().getAssets().open(sImageDbPath)) {
      augmentedImageDatabase = AugmentedImageDatabase.deserialize(session, is);
    } catch (IOException e) {
      Log.e(TAG, "IO exception loading augmented image database.", e);
      return false;
    }

    config.setAugmentedImageDatabase(augmentedImageDatabase);
    return true;
  }

  /**
   * Set {@link AugmentedImageDatabase} based on the type of {@link Subject}.
   * @param subjectType The type of {@link Subject}.
   */
  public static void setImageDatabase(int subjectType) {
    switch (subjectType) {
      case Subject.SUBJECT_VOWEL_BENGALI:
        sImageDbPath = "vowels_bengali.imgdb";
        break;
      case Subject.SUBJECT_ALPHABET_BENGALI:
        sImageDbPath = "alphabets_bengali.imgdb";
        break;
      case Subject.SUBJECT_NUMBER_BENGALI:
        sImageDbPath = "numbers_bengali.imgdb";
        break;
      case Subject.SUBJECT_ALPHABET_ENGLISH:
        sImageDbPath = "alphabets.imgdb";
        break;
      case Subject.SUBJECT_NUMBER_ENGLISH:
        sImageDbPath = "numbers.imgdb";
        break;
      case Subject.SUBJECT_ANIMAL_ENGLISH:
        sImageDbPath = "animals.imgdb";
        break;
    }
  }
}
