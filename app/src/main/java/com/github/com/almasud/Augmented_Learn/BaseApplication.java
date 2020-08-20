package com.github.com.almasud.Augmented_Learn;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.github.com.almasud.Augmented_Learn.model.entity.App;
import com.github.com.almasud.Augmented_Learn.model.entity.ArModel;
import com.github.com.almasud.Augmented_Learn.model.entity.Language;
import com.github.com.almasud.Augmented_Learn.model.entity.Subject;
import com.github.com.almasud.Augmented_Learn.model.entity.Voice;
import com.github.com.almasud.Augmented_Learn.model.util.EventMessage;
import com.github.com.almasud.Augmented_Learn.service.DownloadService;
import com.github.com.almasud.Augmented_Learn.service.UnzipService;
import com.github.com.almasud.Augmented_Learn.util.OnSingleAction;
import com.github.com.almasud.Augmented_Learn.util.AppPreference;
import com.github.com.almasud.Augmented_Learn.util.AppResource;
import com.google.ar.core.ArCoreApk;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

/**
 * A base {@link Application} class of the app.
 *
 * @author Abdullah Almasud
 */
public class BaseApplication extends Application implements LifecycleObserver {
    private static final String TAG = "BaseApplication";
    public static final String BUNDLE = "Bundle";
    public static final String SERVICE_NAME = "Service_Name";
    public static final String SERVICE_LEARN = "Learn";
    public static final String SERVICE_TEST = "Test";
    public static final String SERVICE_SCAN = "Scan";
    public static final int LEARN = 0;
    public static final int TEST = 1;
    public static final int SCAN = 2;
    private static final double MIN_OPEN_GL_VERSION = 3.0;
    public static final String NOTIFICATION_CHANNEL_DOWNLOADER = "Downloader_Channel";
    public static final String NOTIFICATION_CHANNEL_UNZIP = "Unzip_Channel";

    private static final BaseApplication INSTANCE = new BaseApplication();
    private static AppVisibilityListener sAppVisibilityListener;
    private static volatile TextToSpeech sTTS;
    private static MediaPlayer sMediaBengaliVowels, sMediaBengaliVowelsWithExtra,
            sMediaBengaliAlphabets, sMediaBengaliAlphabetsWithExtra, sMediaBengaliNumbers,
            sMediaEnglishAlphabets, sMediaEnglishAlphabetsWithExtra, sMediaEnglishNumbers,
            sMediaEnglishAnimals;

    @Override
    public void onCreate() {
        super.onCreate();

        // Add observer
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);

        // Create downloader and unzip notification channel
        createDownloadNotificationChannel();
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

        // Create media players for Vowels, Alphabets, Numbers and Animals
        sMediaBengaliVowels = new MediaPlayer();
        try {
            AssetFileDescriptor afd = getApplicationContext().getAssets().openFd(
                    AppResource.getAudioUriWithExtra(Subject.BENGALI_VOWEL)[0].getPath()
            );
            sMediaBengaliVowels.setDataSource(
                    afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength()
            );
            afd.close();
            sMediaBengaliVowels.prepareAsync();
        } catch (IOException e) {
            Log.d(TAG, "onEnterForeground: sMediaVowelsBengali media player exception: " + e.getMessage());
        }

        sMediaBengaliVowelsWithExtra = new MediaPlayer();
        try {
            AssetFileDescriptor afd = getApplicationContext().getAssets().openFd(
                    AppResource.getAudioUriWithExtra(Subject.BENGALI_VOWEL)[1].getPath()
            );
            sMediaBengaliVowelsWithExtra.setDataSource(
                    afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength()
            );
            afd.close();
            sMediaBengaliVowelsWithExtra.prepareAsync();
        } catch (IOException e) {
            Log.d(TAG, "onEnterForeground: sMediaVowelsBengaliWithExtra media player exception: " + e.getMessage());
        }

        sMediaBengaliAlphabets = new MediaPlayer();
        try {
            AssetFileDescriptor afd = getApplicationContext().getAssets().openFd(
                    AppResource.getAudioUriWithExtra(Subject.BENGALI_ALPHABET)[0].getPath()
            );
            sMediaBengaliAlphabets.setDataSource(
                    afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength()
            );
            afd.close();
            sMediaBengaliAlphabets.prepareAsync();
        } catch (IOException e) {
            Log.d(TAG, "onEnterForeground: sMediaAlphabetsBengali media player exception: " + e.getMessage());
        }

        sMediaBengaliAlphabetsWithExtra = new MediaPlayer();
        try {
            AssetFileDescriptor afd = getApplicationContext().getAssets().openFd(
                    AppResource.getAudioUriWithExtra(Subject.BENGALI_ALPHABET)[1].getPath()
            );
            sMediaBengaliAlphabetsWithExtra.setDataSource(
                    afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength()
            );
            afd.close();
            sMediaBengaliAlphabetsWithExtra.prepareAsync();
        } catch (IOException e) {
            Log.d(TAG, "onEnterForeground: sMediaAlphabetsBengaliWithExtra media player exception: " + e.getMessage());
        }

        sMediaBengaliNumbers = new MediaPlayer();
        try {
            AssetFileDescriptor afd = getApplicationContext().getAssets().openFd(
                    AppResource.getAudioUriWithExtra(Subject.BENGALI_NUMBER)[0].getPath()
            );
            sMediaBengaliNumbers.setDataSource(
                    afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength()
            );
            afd.close();
            sMediaBengaliNumbers.prepareAsync();
        } catch (IOException e) {
            Log.d(TAG, "onEnterForeground: sMediaNumbersBengali media player exception: " + e.getMessage());
        }

        sMediaEnglishAlphabets = new MediaPlayer();
        try {
            AssetFileDescriptor afd = getApplicationContext().getAssets().openFd(
                    AppResource.getAudioUriWithExtra(Subject.ENGLISH_ALPHABET)[0].getPath()
            );
            sMediaEnglishAlphabets.setDataSource(
                    afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength()
            );
            afd.close();
            sMediaEnglishAlphabets.prepareAsync();
        } catch (IOException e) {
            Log.d(TAG, "onEnterForeground: sMediaAlphabetsEnglish media player exception: " + e.getMessage());
        }

        sMediaEnglishAlphabetsWithExtra = new MediaPlayer();
        try {
            AssetFileDescriptor afd = getApplicationContext().getAssets().openFd(
                    AppResource.getAudioUriWithExtra(Subject.ENGLISH_ALPHABET)[1].getPath()
            );
            sMediaEnglishAlphabetsWithExtra.setDataSource(
                    afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength()
            );
            afd.close();
            sMediaEnglishAlphabetsWithExtra.prepareAsync();
        } catch (IOException e) {
            Log.d(TAG, "onEnterForeground: sMediaAlphabetsEnglishWithExtra media player exception: " + e.getMessage());
        }

        sMediaEnglishNumbers = new MediaPlayer();
        try {
            AssetFileDescriptor afd = getApplicationContext().getAssets().openFd(
                    AppResource.getAudioUriWithExtra(Subject.ENGLISH_NUMBER)[0].getPath()
            );
            sMediaEnglishNumbers.setDataSource(
                    afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength()
            );
            afd.close();
            sMediaEnglishNumbers.prepareAsync();
        } catch (IOException e) {
            Log.d(TAG, "onEnterForeground: sMediaNumbersEnglish media player exception: " + e.getMessage());
        }

        sMediaEnglishAnimals = new MediaPlayer();
        try {
            AssetFileDescriptor afd = getApplicationContext().getAssets().openFd(
                    AppResource.getAudioUriWithExtra(Subject.ENGLISH_ANIMAL)[0].getPath()
            );
            sMediaEnglishAnimals.setDataSource(
                    afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength()
            );
            afd.close();
            sMediaEnglishAnimals.prepareAsync();
        } catch (IOException e) {
            Log.d(TAG, "onEnterForeground: sMediaAnimalsEnglish media player exception: " + e.getMessage());
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnterBackground() {
        Log.d(TAG, "onEnterBackground: The app is in background.");
        setAppInBackground(true);

        // Stop the media players if playing
        if (sMediaBengaliVowels.isPlaying())
            sMediaBengaliVowels.stop();
        if (sMediaBengaliVowelsWithExtra.isPlaying())
            sMediaBengaliVowelsWithExtra.stop();

        if (sMediaBengaliAlphabets.isPlaying())
            sMediaBengaliAlphabets.stop();
        if (sMediaBengaliAlphabetsWithExtra.isPlaying())
            sMediaBengaliAlphabetsWithExtra.stop();

        if (sMediaBengaliNumbers.isPlaying())
            sMediaBengaliNumbers.stop();

        if (sMediaEnglishAlphabets.isPlaying())
            sMediaEnglishAlphabets.stop();
        if (sMediaEnglishAlphabetsWithExtra.isPlaying())
            sMediaEnglishAlphabetsWithExtra.stop();

        if (sMediaEnglishNumbers.isPlaying())
            sMediaEnglishNumbers.stop();

        if (sMediaEnglishAnimals.isPlaying())
            sMediaEnglishAnimals.stop();

        // Release the medial players
        sMediaBengaliVowels.release();
        sMediaBengaliVowelsWithExtra.release();

        sMediaBengaliAlphabets.release();
        sMediaBengaliAlphabetsWithExtra.release();

        sMediaBengaliNumbers.release();

        sMediaEnglishAlphabets.release();
        sMediaEnglishAlphabetsWithExtra.release();

        sMediaEnglishNumbers.release();

        sMediaEnglishAnimals.release();
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
     * @param activity An instance of the {@link Activity} where start from.
     * @param destination A {@link Class} of an {@link Activity} to be started.
     * @param bundle The bundle to be send with the {@link Intent}.
     */
    public void startNewActivity(
            @NonNull Activity activity, @NonNull Class destination, @Nullable Bundle bundle) {
        Intent intent = new Intent(activity, destination);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (bundle != null)
            intent.putExtra(BUNDLE, bundle);
        activity.startActivity(intent);
    }

    /**
     * Set an {@link AlertDialog} with only a positive action button.
     * @param activity An instance of the {@link Activity} where the function to be called.
     * @param customView A custom {@link View} of {@link AlertDialog}.
     * @param title A {@link String} for {@link AlertDialog} title.
     * @param iconRes A {@link DrawableRes} for {@link AlertDialog} icon.
     * @param message A {@link String} for {@link AlertDialog} message.
     * @param positiveButtonAction An {@link OnSingleAction} for positive action button.
     * @param positiveButtonText A {@link String} for positive action button.
     */
    public static void setAlertDialog(
            Activity activity, View customView, String title, int iconRes, String message,
            OnSingleAction positiveButtonAction, String positiveButtonText) {

        // Set only required parameter to the main method
        setAlertDialog(
                activity, customView, title, iconRes, message,
                positiveButtonAction, positiveButtonText,
                null, null,
                null, null
        );
    }

    /**
     * Set an {@link AlertDialog} with positive and negative action button.
     * @param activity An instance of the {@link Activity} where the function to be called.
     * @param customView A custom {@link View} of {@link AlertDialog}.
     * @param title A {@link String} for {@link AlertDialog} title.
     * @param iconRes A {@link DrawableRes} for {@link AlertDialog} icon.
     * @param message A {@link String} for {@link AlertDialog} message.
     * @param positiveButtonAction An {@link OnSingleAction} for positive action button.
     * @param positiveButtonText A {@link String} for positive action button.
     * @param negativeButtonAction An {@link OnSingleAction} for negative action button.
     * @param negativeButtonText A {@link String} for negative action button.
     */
    public static void setAlertDialog(
            Activity activity, View customView, String title, int iconRes, String message,
            OnSingleAction positiveButtonAction, String positiveButtonText,
            OnSingleAction negativeButtonAction, String negativeButtonText) {

        // Set only required parameter to the main method
        setAlertDialog(
                activity, customView, title, iconRes, message,
                positiveButtonAction, positiveButtonText,
                negativeButtonAction, negativeButtonText,
                null, null
        );
    }

    /**
     * Set an {@link AlertDialog} with a custom {@link View} and positive, negative and neutral action button.
     * @param activity An instance of the {@link Activity} where the function to be called.
     * @param customView A custom {@link View} of {@link AlertDialog}.
     * @param title A {@link String} for {@link AlertDialog} title.
     * @param iconRes A {@link DrawableRes} for {@link AlertDialog} icon.
     * @param message A {@link String} for {@link AlertDialog} message.
     * @param positiveButtonAction An {@link OnSingleAction} for positive action button.
     * @param positiveButtonText A {@link String} for positive action button.
     * @param negativeButtonAction An {@link OnSingleAction} for negative action button.
     * @param negativeButtonText A {@link String} for negative action button.
     * @param neutralButtonAction An {@link OnSingleAction} for neutral action button.
     * @param neutralButtonText A {@link String} for neutral action button.
     */
    public static void setAlertDialog(
            Activity activity, View customView, String title, int iconRes, String message,
            OnSingleAction positiveButtonAction, String positiveButtonText,
            OnSingleAction negativeButtonAction, String negativeButtonText,
            OnSingleAction neutralButtonAction, String neutralButtonText) {

        // Create an alert dialog to show a dialog message
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        dialogBuilder.setCancelable(false);
        // Set a custom view
        if (customView != null)
            dialogBuilder.setView(customView);
        // Set a title
        if (title != null)
            dialogBuilder.setTitle(title);
        // Set an image resource
        if (iconRes != -1)
            dialogBuilder.setIcon(iconRes);
        // Set a message
        if (message != null)
            dialogBuilder.setMessage(message);

        // Set an action for positive button
        if (positiveButtonAction != null) {
            dialogBuilder.setPositiveButton(
                    (positiveButtonText != null)? positiveButtonText
                            : activity.getResources().getString(R.string.action_yes),
                    (dialog, which) -> positiveButtonAction.onAction()
            );
        }

        // Set an action for negative button
        if (negativeButtonAction != null) {
            dialogBuilder.setNegativeButton(
                    (negativeButtonText != null)? negativeButtonText
                            : activity.getResources().getString(R.string.action_no),
                    (dialog, which) -> negativeButtonAction.onAction()
            );
        }

        // Set an action for neutral button
        if (neutralButtonAction != null) {
            dialogBuilder.setNeutralButton(
                    (neutralButtonText != null)? neutralButtonText
                            : activity.getResources().getString(R.string.action_not_sure),
                    (dialog, which) -> neutralButtonAction.onAction()
            );
        }

        // To avoid the block of UI (main) thread execute the task within a new thread.
        new Handler().post(() -> {
            AlertDialog dialog = dialogBuilder.create();
            dialog.show();

            // This line always placed after the dialog.show() otherwise get a Null Pinter Exception.
            // Set all caps false to al alert dialog buttons
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(false);
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setAllCaps(false);
            dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setAllCaps(false);

            // Set custom font type face for alert dialog views
            BaseApplication.changeTextViewFont(
                    activity, Language.ENGLISH, Typeface.NORMAL,
                    dialog.findViewById(R.id.alertTitle), dialog.findViewById(android.R.id.message),
                    dialog.findViewById(android.R.id.button1), dialog.findViewById(android.R.id.button2),
                    dialog.findViewById(android.R.id.button3)
            );
        });
    }

    /**
     * Check whether the AR is supported for this device or not. Show a dialog message
     * if not supported.
     * @param activity An instance of the {@link Activity} where the function to be called.
     * @return true if AR is supported for this device otherwise false.
     */
    public static boolean isSupportedAROrShowDialog(@NonNull Activity activity) {
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        Log.i(TAG, "OpenGL ES Version: " + openGlVersionString);

        // Check for Sceneform support
        if ((Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
                || (Double.parseDouble(openGlVersionString) < MIN_OPEN_GL_VERSION)) {

            // Set an alert dialog to inform
            setAlertDialog(
                    activity, null, activity.getResources().getString(R.string.opps),
                    R.drawable.ic_sentiment_dissatisfied_black,
                    activity.getResources().getString(R.string.not_supported_sceneform),
                    () -> Toast.makeText(
                            activity,
                            activity.getResources().getString(R.string.hope_understand),
                            Toast.LENGTH_SHORT
                    ).show(), activity.getResources().getString(R.string.okay_understand)
            );

        } // Check for ArCore support
        else if (!ArCoreApk.getInstance().checkAvailability(activity).isSupported()) {

            // Set an alert dialog
            setAlertDialog(
                    activity, null, activity.getResources().getString(R.string.action_choose),
                    R.drawable.ic_help,
                    activity.getResources().getString(R.string.not_supported_ar_core),
                    () -> {
                        final String appPackageName = "com.google.ar.core";
                        try {
                            activity.startActivity(
                                    new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("market://details?id=" + appPackageName)
                                    )
                            );
                        } catch (ActivityNotFoundException ex) {
                            activity.startActivity(
                                    new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)
                                    )
                            );
                        }
                    }, null, () -> {}, null);
        }

        return !((Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
                || (Double.parseDouble(openGlVersionString) < MIN_OPEN_GL_VERSION)
                || (!ArCoreApk.getInstance().checkAvailability(activity).isSupported()));
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
     * Play a {@link Voice} from a given {@link ArModel}.
     * @param arModel An instance of {@link ArModel} to be played.
     * @param isExtraPlay true if the {@link Voice} to be played form extra of
     * {@link ArModel} otherwise false.
     */
    public static void playVoice(ArModel arModel, boolean isExtraPlay) {
        int start = isExtraPlay?
                arModel.getVoice().getExtraStart(): arModel.getVoice().getStart();
        int end = isExtraPlay?
                arModel.getVoice().getExtraEnd(): arModel.getVoice().getEnd();

        // Check whether the end time is grater than start or not
        if (end > start) {
            new Thread(() -> {
                // Determine which media player need to play
                MediaPlayer[] mediaPlayer = new MediaPlayer[1];
                if (!isExtraPlay) {
                    switch (arModel.getVoice().getId()) {
                        case Voice.VOICE_BENGALI_VOWELS:
                            mediaPlayer[0] = sMediaBengaliVowels;
                            Log.d(TAG, "playVoice: playing vowels bengali");
                            break;
                        case Voice.VOICE_BENGALI_ALPHABETS:
                            mediaPlayer[0] = sMediaBengaliAlphabets;
                            Log.d(TAG, "playVoice: playing alphabets bengali");
                            break;
                        case Voice.VOICE_BENGALI_NUMBERS:
                            mediaPlayer[0] = sMediaBengaliNumbers;
                            Log.d(TAG, "playVoice: playing numbers bengali");
                            break;
                        case Voice.VOICE_ENGLISH_ALPHABETS:
                            mediaPlayer[0] = sMediaEnglishAlphabets;
                            Log.d(TAG, "playVoice: playing alphabets english");
                            break;
                        case Voice.VOICE_ENGLISH_NUMBERS:
                            mediaPlayer[0] = sMediaEnglishNumbers;
                            Log.d(TAG, "playVoice: playing numbers english");
                            break;
                        case Voice.VOICE_ENGLISH_ANIMALS:
                            mediaPlayer[0] = sMediaEnglishAnimals;
                            Log.d(TAG, "playVoice: playing animals english");
                            break;
                    }
                 } else {
                    switch (arModel.getVoice().getId()) {
                        case Voice.VOICE_BENGALI_VOWELS:
                            mediaPlayer[0] = sMediaBengaliVowelsWithExtra;
                            Log.d(TAG, "playVoice: playing vowels bengali with extra");
                            break;
                        case Voice.VOICE_BENGALI_ALPHABETS:
                            mediaPlayer[0] = sMediaBengaliAlphabetsWithExtra;
                            Log.d(TAG, "playVoice: playing alphabets bengali with extra");
                            break;
                        case Voice.VOICE_ENGLISH_ALPHABETS:
                            mediaPlayer[0] = sMediaEnglishAlphabetsWithExtra;
                            Log.d(TAG, "playVoice: playing vowels bengali with extra");
                            break;
                    }
                }

                // Start and pause the media player according to start and end time
                if (mediaPlayer[0] != null) {
                    if (!mediaPlayer[0].isPlaying()) {
                        mediaPlayer[0].seekTo(start);
                        mediaPlayer[0].start();
                        try {
                            Thread.sleep((end - start));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer[0].pause();
                    }
                }
            }).start();
        } else {
            Log.e(TAG, "playVoice: end must be grater than start time");
        }
    }

    /**
     * Load a TTS ({@link TextToSpeech}) engine (TTS engine initialization takes some times) with thread safe.
     * @param context The {@link Context} of {@link Application}.
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
     * Check whether the TTS {@link TextToSpeech} engine is loaded or not.
     * @return true if the {@link TextToSpeech} is loaded otherwise false.
     */
    public static boolean isTTSEngineLoaded() {
        return sTTS != null;
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
     * check whether the external storage is available for write (and read) or not.
     * @return true if the external storage is writable otherwise false.
     */
    public static boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    private void createDownloadNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_DOWNLOADER, getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationChannel.setLightColor(Color.GREEN);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
                Log.d(TAG, "createDownloaderNotificationChannel: Notification channel created.");
            }
        }
    }

    private void createUnzipNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_UNZIP, getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationChannel.setLightColor(Color.GREEN);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
                Log.d(TAG, "createUnzipNotificationChannel: Notification channel created.");
            }
        }
    }

    /**
     * Download a {@link File} from a given {@link URL} and unzip if downloaded file is zip
     * to a given target {@link File} directory.
     * @param activity The {@link Activity}.
     * @param downloadURL An {@link URL} to be download from.
     * @param targetDirectory A {@link File} directory to be placed the downloaded {@link File}.
     */
    public static void download(
            @NonNull Activity activity, @NonNull final String downloadURL,
            @NonNull File targetDirectory) {
        Log.d(TAG, "download: The download url: "+ downloadURL);
        Log.d(TAG, "download: The target directory: "+ targetDirectory.getAbsolutePath());

        // Check the download is already in progress or not
        if (AppPreference.isDownloadProgress(activity)) {
            BaseApplication.setAlertDialog(
                    activity, null, activity.getResources().getString(R.string.opps),
                    R.drawable.ic_sentiment_dissatisfied_black, activity.getResources().getString(R.string.downloading_another_file),
                    () -> Toast.makeText(
                            activity, activity.getResources().getString(R.string.hope_understand), Toast.LENGTH_SHORT
                    ).show(),
                    activity.getResources().getString(R.string.okay_understand)
            );
        } else {
            if (isInternetAvailable(activity)) {
                // Start download service to download the file
                Intent downloadServiceIntent = new Intent(activity, DownloadService.class);
                downloadServiceIntent.putExtra(DownloadService.DOWNLOAD_URL, downloadURL);
                downloadServiceIntent.putExtra(DownloadService.TARGET_DIRECTORY, targetDirectory);

                // Start the service as foreground
                ContextCompat.startForegroundService(activity, downloadServiceIntent);
            } else {
                // Dispatch an EventMessage to it's subscribers
                EventBus.getDefault().post(
                        new EventMessage(activity.getResources().getString(R.string.internet_not_available), EventMessage.TYPE_ERROR)
                );
            }
        }
    }

    /**
     * Unzip a given zip {@link File} into a given target directory.
     * @param context The context of {@link Application}.
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
     * Change the {@link Typeface} of {@link TextView} (s) according to the {@link Language}.
     * @param context The context of {@link Application}.
     * @param language A type of {@link Language}.
     * @param style A style of the {@link Typeface}.
     * @param textViews {@link TextView} (s) to be set the {@link Typeface}.
     */
    public static void changeTextViewFont(
            @NonNull Context context, int language, int style, @NonNull TextView ...textViews) {
        // Create type face from asset
        Typeface typeface = Typeface.createFromAsset(
                context.getAssets(), AppResource.getFontUri(language).getPath()
        );
        // Set typeface of the text view (s)
        for (TextView textView: textViews) {
            textView.setTypeface(typeface, style);
        }
    }

    /**
     * Change the {@link Typeface} of {@link Toolbar} title and subtitle
     * according to the {@link Language}.
     * @param activity An instance of {@link Activity}.
     * @param language A type of {@link Language}.
     * @param style A style of the {@link Typeface}.
     * @param toolbar A {@link Toolbar} that title to be changed.
     */
    public static void changeToolbarTitleFont(
            @NonNull Activity activity, int language, int style, @NonNull Toolbar toolbar) {

        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                // For toolbar title
                if (textView.getText().equals(toolbar.getTitle())) {
                    textView.setTextSize(16f);
                    changeTextViewFont(activity, Language.ENGLISH, style, textView);
                }
                // For toolbar subtitle
                if (textView.getText().equals(toolbar.getSubtitle())) {
                    if (language != Language.ENGLISH)
                        changeTextViewFont(activity, language, style, textView);
                }
            }
        }
    }

    /**
     * Determines whether the internet connection is available or not.
     * @param activity An instance of {@link Activity}.
     * @return True if internet connection is available otherwise false.
     */
    public static boolean isInternetAvailable(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    /**
     * Show an update available dialog and install the {@link App} by using the {@link DownloadService}.
     * @param activity An instance of {@link Activity}.
     * @param app An instance of {@link App}.
     */
    public static void showUpdateAvailable(Activity activity, App app) {
        BaseApplication.setAlertDialog(
                activity, null, activity.getResources().getString(R.string.update_available),
                R.drawable.ic_update, String.format(
                        activity.getResources().getString(R.string.update_available_message) + "\n"
                                + " %s\n"
                                + activity.getResources().getString(R.string.current_version) + " %s\n"
                                + activity.getResources().getString(R.string.available_version) + " %s",
                        app.getVersionDescription(), BuildConfig.VERSION_NAME,
                        app.getVersionName()
                ), () -> {
                    File appDirectory = activity.getExternalFilesDir(
                            File.separator + App.DIRECTORY_APP
                    );
                    // Create a directory if is not exist
                    if (!appDirectory.exists())
                        appDirectory.mkdirs();

                    if (appDirectory.isDirectory()) {
                        // Check whether the directory contains any item or not
                        if (appDirectory.listFiles().length >= 1) {
                            Log.d(TAG, "onOptionsItemSelected: " + appDirectory.getAbsolutePath() + " contains old file (s).");
                            // Delete the existing files from the directory
                            for (File file: appDirectory.listFiles()) {
                                file.delete();
                                Log.d(TAG, "onOptionsItemSelected: " + file.getName() + " is deleted.");
                            }
                        }

                        // Start download & install the updated app.
                        BaseApplication.download(
                                activity, app.getDownloadURL(), appDirectory
                        );
                    }

                }, activity.getResources().getString(R.string.action_update_now),
                () -> {}, activity.getResources().getString(R.string.action_update_later),
                () -> {
                    try {
                        activity.startActivity(
                                new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID)
                                )
                        );
                    } catch (ActivityNotFoundException ex) {
                        activity.startActivity(
                                new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)
                                )
                        );
                    }
                }, activity.getResources().getString(R.string.action_get_from_google_play)
        );
    }
}
