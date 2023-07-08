package com.github.almasud.augmented_learn.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.webkit.URLUtil;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.github.almasud.augmented_learn.BaseApplication;
import com.github.almasud.augmented_learn.BuildConfig;
import com.github.almasud.augmented_learn.R;
import com.github.almasud.augmented_learn.model.util.EventMessage;
import com.github.almasud.augmented_learn.view.activity.SubjectChooseActivity;
import com.github.almasud.augmented_learn.util.AppPreference;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * A {@link Service} class for file download.
 */
public class DownloadService extends Service {
    private static final String TAG = "DownloadService";
    public static final String DOWNLOAD_URL = "Download_Link";
    public static final String TARGET_DIRECTORY = "Target_Directory";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: The download service is created");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Get download information from intent
        final String downloadURL = intent.getStringExtra(DOWNLOAD_URL);
        final File targetDirectory = (File) intent.getSerializableExtra(TARGET_DIRECTORY);
        // Notification ID is a unique int for each notification that must be define
        final int notificationId = (int) (System.currentTimeMillis() / 1000);

        // Create a thread to download the file
        Thread thread = new Thread(() -> {
            boolean successStatus = true;
            // Set the download status true in preferences
            AppPreference.setDownloadStatus(this, true);
            // Dispatch an EventMessage to it's subscribers
            EventBus.getDefault().post(
                    new EventMessage("Download starting", EventMessage.TYPE_NORMAL)
            );

            // Create a notify pending intent
            Intent notifyIntent = new Intent(this, SubjectChooseActivity.class);
            PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                    this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            // Create a notification builder before starting the download
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                    this, BaseApplication.NOTIFICATION_CHANNEL_DOWNLOADER
            );
            notificationBuilder.setContentIntent(notifyPendingIntent);
            notificationBuilder.setTicker("Start downloading from server");
            notificationBuilder.setOngoing(true);
            notificationBuilder.setAutoCancel(false);
            notificationBuilder.setOnlyAlertOnce(true);
            notificationBuilder.setSmallIcon(R.drawable.installing_update_animation);
            notificationBuilder.setContentTitle("Downloading");
            notificationBuilder.setContentText("0%");
            notificationBuilder.setProgress(100, 0, false);

            // Notify by starting the foreground service.
            // Note that: Once a service is started it's not care about the number
            // of start request of the service until the service is stopped.
            startForeground(notificationId, notificationBuilder.build());

            // Download the file from server
            String fileName = URLUtil.guessFileName(downloadURL, null, null);
            File tempFile = new File(targetDirectory, fileName + ".tmp");
            File file = new File(targetDirectory, fileName);

            // Create the directory and file if the file is not exists
            if (!tempFile.exists()) {
                File dir = tempFile.getParentFile();

                // Create the directory if not exists
                if (!dir.exists())
                    if (!(successStatus = dir.mkdirs())) {
                        Log.e(TAG, "onStartCommand: Couldn't crete the download directory!");

                        // Dispatch an EventMessage to it's subscribers
                        EventBus.getDefault().post(
                                new EventMessage("Couldn't crete the download directory!", EventMessage.TYPE_ERROR)
                        );
                    }

                // Create the file if not exists
                try {
                    successStatus = tempFile.createNewFile();
                } catch (IOException e) {
                    Log.e(TAG, "onStartCommand: Couldn't crete the download file.");

                    // Dispatch an EventMessage to it's subscribers
                    EventBus.getDefault().post(
                            new EventMessage("Couldn't crete the download file!", EventMessage.TYPE_ERROR)
                    );
                }
            }

            // Try to start download the file
            try {
                URL url = new URL(downloadURL);
                URLConnection connection = url.openConnection();
                int fileLength = connection.getContentLength();
                // Read file with 8k buffer
                InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
                // Output stream to write file
                OutputStream outputStream = new FileOutputStream(tempFile);

                byte[] buffer = new byte[1024];
                long total = 0;
                int count, tempPercent = 0;

                while ((count = inputStream.read(buffer)) != -1) {
                    total += count;
                    outputStream.write(buffer, 0, count);
                    int percent = (int) ((total * 100) / fileLength);

                    if (percent > tempPercent) {
                        tempPercent = percent;
                        notificationBuilder.setContentTitle(fileName);
                        notificationBuilder.setContentText(percent + "%");
                        notificationBuilder.setProgress(100, percent, false);
                        // Notify by starting the foreground service.
                        startForeground(notificationId, notificationBuilder.build());
                    }
                }
                // Flushing output
                outputStream.flush();
                // Closing streams
                outputStream.close();
                inputStream.close();

                // Rename the downloaded file to cut the .tmp and set the successStatus
                successStatus = tempFile.renameTo(file);
            } catch (FileNotFoundException e) {
                successStatus = false;
                Log.e(TAG, "onStartCommand: FileNotFoundException: " + e.getMessage());

                // Dispatch an EventMessage to it's subscribers
                EventBus.getDefault().post(
                        new EventMessage("File is not found!", EventMessage.TYPE_ERROR)
                );
            } catch (MalformedURLException e) {
                successStatus = false;
                Log.e(TAG, "onStartCommand: MalformedURLException: " + e.getMessage());

                // Dispatch an EventMessage to it's subscribers
                EventBus.getDefault().post(
                        new EventMessage("The download link is not valid!", EventMessage.TYPE_ERROR)
                );
            } catch (IOException e) {
                successStatus = false;
                Log.e(TAG, "onStartCommand: IOException: " + e.getMessage());

                // Dispatch an EventMessage to it's subscribers
                EventBus.getDefault().post(
                        new EventMessage("Download failed!", EventMessage.TYPE_ERROR)
                );
            } finally {
                notificationBuilder.setSmallIcon(successStatus ? R.drawable.ic_done : R.drawable.ic_error);
                notificationBuilder.setContentTitle(successStatus ? "Done" : "Failed");
                notificationBuilder.setContentText(successStatus ? "Download completed" : "Download is not completed");
                notificationBuilder.setOngoing(false);
                notificationBuilder.setAutoCancel(true);
                notificationBuilder.setProgress(0, 0, false);
                // Notify by starting the foreground service.
                startForeground(notificationId, notificationBuilder.build());

                // Set the download status false in preferences
                AppPreference.setDownloadStatus(this, false);
            }

            // Dispatch an EventMessage to it's subscribers
            if (successStatus) {
                EventBus.getDefault().post(
                        new EventMessage("Download completed!", EventMessage.TYPE_ERROR)
                );
            } else {
                SystemClock.sleep(2000);
                EventBus.getDefault().post(
                        new EventMessage("Download is not completed!", EventMessage.TYPE_ERROR)
                );
            }

            // If the downloaded file is zip start unzip foreground service.
            if (successStatus && file.getName().endsWith(".zip")) {
                Log.d(TAG, "onStartCommand: The downloaded file is a zip file.");

                Intent unzipServiceIntent = new Intent(getApplicationContext(), UnzipService.class);
                unzipServiceIntent.putExtra(UnzipService.ZIP_FILE, file);
                unzipServiceIntent.putExtra(UnzipService.TARGET_DIRECTORY, targetDirectory);
                ContextCompat.startForegroundService(getApplicationContext(), unzipServiceIntent);
            }

            // If the downloaded file is apk then open it in installation package manager.
//            if (successStatus && file.getName().endsWith(".apk")) {
//                Log.d(TAG, "onStartCommand: The downloaded file is an apk file.");
//
//                final String fileBasePath = "file://";
//                final String providerPath = ".provider";
//                final String appInstallPath = "\"application/vnd.android.package-archive\"";
//                final String destination = targetDirectory + "/" + file.getName();
//                final Uri uri = Uri.parse(fileBasePath + destination);
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    Uri contentUri = FileProvider.getUriForFile(
//                            this,
//                            BuildConfig.APPLICATION_ID + providerPath,
//                            new File(destination)
//                    );
//                    Intent install = new Intent(Intent.ACTION_VIEW);
//                    install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    install.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    install.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
//                    install.setData(contentUri);
//                    startActivity(install);
//                } else {
//                    Intent install = new Intent(Intent.ACTION_VIEW);
//                    install.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    install.setDataAndType(uri, appInstallPath);
//                    startActivity(install);
//                }
//            }

            // After completing the task stop the foreground service with keep the notification
            // and after a while system will automatically kill the service.
            stopForeground(false);
        });

        // Start the thread
        thread.start();

        // Since the service serve as a foreground service, so no need to restart request
        // because foreground service is killed rare by the system.
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: The download service is destroyed.");
    }
}
