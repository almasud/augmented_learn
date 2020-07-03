package com.almasud.intro;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.almasud.intro.model.entity.ArModel;
import com.almasud.intro.service.DownloadService;
import com.almasud.intro.service.UnzipService;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import io.reactivex.Completable;

/**
 * A base {@link Application} class of the app.
 *
 * @author Abdullah Almasud
 */
public class BaseApplication extends Application implements LifecycleObserver {
    public static final String ACTIVITY_NAME = "Activity_Name";
    public static final String ACTIVITY_LEARN = "Activity_Learn";
    public static final String ACTIVITY_TEST = "Activity_Test";
    public static final String ACTIVITY_SCAN = "Activity_Scan";
    public static final String BUNDLE = "Bundle";
    public static final int LEARN = 0;
    public static final int TEST = 1;
    public static final int SCAN = 2;
    public static final String ITEM_LIST = "Item_List";
    public static final String SELECTED_ITEM = "Selected_Item";
    public static final String MODEL_TYPE = "Model_Type";
    public static final String MODEL_ALPHABET = "Model_Alphabet";
    public static final String MODEL_NUMBER = "Model_Number";
    public static final String MODEL_ANIMAL = "Model_Animal";
    public static final int ALPHABET = 0;
    public static final int NUMBER = 1;
    public static final int ANIMAL = 2;
    private static final double MIN_OPEN_GL_VERSION = 3.0;
    private static final String DIRECTORY_MODELS = "models";
    public static final String DIRECTORY_ALPHABETS = "alphabets";
    public static final String DIRECTORY_NUMBERS = "numbers";
    public static final String DIRECTORY_ANIMALS = "animals";
    public static final String NOTIFICATION_CHANNEL_DOWNLOADER = "Downloader_Channel";
    public static final String NOTIFICATION_CHANNEL_UNZIP = "Downloader_Unzip";
    public static final String DOWNLOAD_URL_ALPHABETS = "http://almasud.000webhost.com/alphabets.zip";
    public static final String DOWNLOAD_URL_NUMBERS = "http://almasud.000webhost.com/numbers.zip";
    public static final String DOWNLOAD_URL_ANIMALS = "http://almasud.000webhost.com/animals.zip";

    private static final String TAG = BaseApplication.class.getSimpleName();
    private static final BaseApplication INSTANCE = new BaseApplication();
    private static AppVisibilityListener sAppVisibilityListener;
    private static volatile TextToSpeech sTTS;

    @Override
    public void onCreate() {
        super.onCreate();

        // Add observer
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        // Create downloader and unzip notification channel
        createDownloaderNotificationChannel();
        createUnzipNotificationChannel();
    }

    /**
     * Used to get the instance of {@link BaseApplication}.
     * @return The instance of {@link BaseApplication}.
     */
    public static BaseApplication getInstance() {
        return INSTANCE;
    }

    /**
     * A listener for the visibility of the {@link Application}.
     */
    public interface AppVisibilityListener {
        /**
         * Used to determine the visibility of the {@link Application}.
         * @param isBackground true if the {@link Application} is in background otherwise false.
         */
        void onAppVisibility(boolean isBackground);
    }

    /**
     * Used to set whether the app is in background or not.
     */
    private void setAppInBackground(boolean isBackground) {
        if (sAppVisibilityListener != null)
            sAppVisibilityListener.onAppVisibility(isBackground);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onEnterForeground() {
        Log.d(TAG, "onEnterForeground: The app is in foreground.");
        setAppInBackground(false);

        // Load the TTS engine
        loadTTSEngine(getApplicationContext());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnterBackground() {
        Log.d(TAG, "onEnterBackground: The app is in background.");
        setAppInBackground(true);

        // Unload the TTS engine
        unloadTTSEngine();
    }

    /**
     * Used to set an {@link AppVisibilityListener}.
     * @param listener An {@link AppVisibilityListener}.
     */
    public void setOnAppVisibilityListener(AppVisibilityListener listener) {
        sAppVisibilityListener = listener;
    }

    /**
     * Start an activity with a new task.
     * @param activity The activity start from.
     * @param destination The activity to be started.
     * @param bundle The bundle to be send with the {@link Intent}.
     */
    public void startNewActivity(@NonNull Activity activity,
                                 @NonNull Class destination, @Nullable Bundle bundle) {
        Intent intent = new Intent(activity, destination);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (bundle != null)
            intent.putExtra(BUNDLE, bundle);
        activity.startActivity(intent);
    }

    /**
     * Check whether the sceneform is supported for this device or not. Show a dialog message
     * if not supported. Sceneform requires Android N as well as OpenGL ES 3.0 or above capabilities.
     * @param activity A {@link Activity} of the application.
     * @return true if sceneform is supported for this device otherwise false.
     */
    public static boolean isSupportedSceneformOrShowDialog(@NonNull Activity activity) {
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        Log.i(TAG, "OpenGL ES Version: " + openGlVersionString);

        if ((Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
                || (Double.parseDouble(openGlVersionString) < MIN_OPEN_GL_VERSION)) {

            // Create an alert dialog to show a dialog message
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
            dialogBuilder.setCancelable(false);
            dialogBuilder.setTitle(activity.getResources().getString(R.string.opps));
            dialogBuilder.setIcon(R.drawable.ic_sentiment_dissatisfied_black);
            dialogBuilder.setMessage(activity.getResources().getString(R.string.not_supported_sceneform));
            dialogBuilder.setPositiveButton(activity.getResources().getString(R.string.okay_understand), (dialog, which) ->
                    Toast.makeText(activity, activity.getResources().getString(R.string.hope_understand), Toast.LENGTH_SHORT).show());

            // To avoid the block of UI (main) thread execute the task within a new thread.
            new Handler().post(() -> {
                AlertDialog dialog = dialogBuilder.create();
                dialog.show();

                // This line always placed after the dialog.show() otherwise get a Null Pinter Exception.
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
            });
        }

        return !((Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
                || (Double.parseDouble(openGlVersionString) < MIN_OPEN_GL_VERSION));
    }

    /**
     * Check whether the TTS {@link TextToSpeech} engine is loaded or not.
     * @return true if the {@link TextToSpeech} is loaded otherwise false.
     */
    public static boolean isTTSEngineLoaded() {
        return sTTS != null;
    }

    /**
     * Load a TTS ({@link TextToSpeech}) engine (TTS engine initialization takes some times).
     * @param context The {@link Context} of the application.
     */
    private static void loadTTSEngine(@NonNull Context context) {
        synchronized (BaseApplication.class) {
            try {
                sTTS = new TextToSpeech(context, status -> {
                    if (status == TextToSpeech.SUCCESS) {
                        int ttsLang = sTTS.setLanguage(new Locale("EN_US"));
                        if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                                || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Log.e(TAG, "Language is not supported.");
                            Toast.makeText(context, "TTS language is not supported to your device.", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.i(TAG, "Language is supported.");
                        }
                        Log.i(TAG, "TTS initialization success.");

                    } else {
                        Log.e(TAG, "TTS initialization failed.");
                        Toast.makeText(context, "Couldn't setup TTS engine to your device.", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                Log.e(TAG, "loadTTSEngine: Exception occurred while trying to load TTS engine");
            }
        }
    }

    private static void unloadTTSEngine() {
        // Close the Text To Speech
        if (sTTS != null) {
            sTTS.stop();
            sTTS.shutdown();
            Log.i(TAG, "TTS shutdown successful.");
        }
    }

    /**
     * The custom speak() method of {@link TextToSpeech}.
     * @param text A {@link String} to be spoken.
     */
    public static void speak(@NonNull String text) {
        if (isTTSEngineLoaded()) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                if (sTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null, null) == TextToSpeech.ERROR)
                    Log.e(TAG, "Error in converting text to speech.");
            } else {
                if (sTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null) == TextToSpeech.ERROR)
                    Log.e(TAG, "Error in converting text to speech.");
            }
        } else {
            Log.e(TAG, "speak: The TTS engine is not loaded yet!");
        }
    }

    /**
     * Generate an {@link Integer} array of a given number of unique {@link Random}
     * number from a given bound number.
     * @param boundNumber The number to be bounded for generating {@link Random} number.
     * @param totalNumber The number of array size to generated.
     * @return An {@link Integer} array of unique {@link Random} numbers.
     */
    public static int[] getUniqueRandNumbers(int boundNumber, int totalNumber) {
        // Random set to hold unique random number.
        Set<Integer> randSet = new HashSet<>(totalNumber);
        // Add resultSize of random numbers to set
        while (randSet.size() < totalNumber)
            while (!randSet.add(new Random().nextInt(boundNumber)));
        // Convert the randSet into an integer array to return
        int[] randArray = new int[totalNumber];
        int i = 0;
        for (Integer integer : randSet)
            randArray[i++] = integer;
        return randArray;
    }

    /**
     * check whether the external storage is available for write (and read) or not.
     * @return true if the external storage is writable otherwise false.
     */
    public static boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * @return A {@link File} directory of {@link ArModel}s.
     */
    public static File getExternalFileModelsDir(@NonNull Context context, @NonNull String directory) {
        return context.getExternalFilesDir(
                File.separator + DIRECTORY_MODELS + File.separator + directory
        );
    }

    private void createDownloaderNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_DOWNLOADER, getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_LOW
            );
            notificationChannel.setLightColor(Color.GREEN);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null)
                notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void createUnzipNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_UNZIP, getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_LOW
            );
            notificationChannel.setLightColor(Color.GREEN);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null)
                notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    /**
     * Unzip a given zip {@link File} into a given target directory.
     * @param zipFile A zip {@link File} to be extracted.
     * @param targetDirectory A target directory {@link File} where extracted files to be placed.
     */
    public static void unzip(
            @NonNull Context context, @NonNull File zipFile, @NonNull File targetDirectory) {
        // Start unzip service to unzip the file
        Intent unzipServiceIntent = new Intent(context, UnzipService.class);
        unzipServiceIntent.putExtra(UnzipService.ZIP_FILE, zipFile);
        unzipServiceIntent.putExtra(UnzipService.TARGET_DIRECTORY, targetDirectory);

        // Start the service as foreground
        ContextCompat.startForegroundService(context, unzipServiceIntent);
    }

    /**
     * Download a {@link File} from a given {@link URL} and unzip if downloaded file is zip
     * to a given target {@link File} directory.
     * @param context The application {@link Context}.
     * @param downloadURL An {@link URL} to be download from.
     * @param targetDirectory A {@link File} directory to be placed the downloaded {@link File}.
     */
    public static void download(
            @NonNull Context context, @NonNull final String downloadURL, @NonNull File targetDirectory) {
        // Start download service to download the file
        Intent downloadServiceIntent = new Intent(context, DownloadService.class);
        downloadServiceIntent.putExtra(DownloadService.DOWNLOAD_URL, downloadURL);
        downloadServiceIntent.putExtra(DownloadService.TARGET_DIRECTORY, targetDirectory);

        // Start the service as foreground
        ContextCompat.startForegroundService(context, downloadServiceIntent);
    }
}
