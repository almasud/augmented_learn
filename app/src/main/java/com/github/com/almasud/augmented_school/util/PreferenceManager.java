package com.github.com.almasud.augmented_school.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * A manager class for {@link SharedPreferences}.
 *
 * @author Abdullah Almasud
 */
public final class PreferenceManager {
    private static final String PREFERENCE_NAME = "com.almasud.intro";
    private SharedPreferences mPreferences;
    private static final String IS_DOWNLOAD_PROGRESS = "download_progress";

    public PreferenceManager(Context context) {
        mPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void setDownloadStatus(boolean isProgress) {
        mPreferences.edit().putBoolean(IS_DOWNLOAD_PROGRESS, isProgress).apply();
    }

    public boolean isDownloadProgress() {
        return mPreferences.getBoolean(IS_DOWNLOAD_PROGRESS, false);
    }
}
