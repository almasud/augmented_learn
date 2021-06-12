package com.github.almasud.augmented_learn.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.almasud.augmented_learn.model.entity.App;
import com.google.gson.Gson;

/**
 * A manager class for {@link SharedPreferences}.
 *
 * @author Abdullah Almasud
 */
public final class AppPreference {
    private static final String PREFERENCE_NAME = "com.github.almasud.Augmented_Learn";
    private SharedPreferences mPreferences;
    private static final String IS_DOWNLOAD_PROGRESS = "download_progress";
    private static final String APP_INFO = "app_info";

    private AppPreference() {}

    public static void setDownloadStatus(Context context, boolean isProgress) {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
                .edit().putBoolean(IS_DOWNLOAD_PROGRESS, isProgress).apply();
    }

    public static boolean isDownloadProgress(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getBoolean(IS_DOWNLOAD_PROGRESS, false);
    }

    public static void setAppInfo(Context context, App app) {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
                .edit().putString(APP_INFO, new Gson().toJson(app)).apply();
    }

    public static App getAppInfo(Context context) {
        String jsonString = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getString(APP_INFO, null);

        if (jsonString == null || jsonString.length() == 0) {
            return null;
        }
        return new Gson().fromJson(jsonString, App.class);
    }
}
