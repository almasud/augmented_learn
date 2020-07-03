package com.almasud.intro.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.almasud.intro.R;
import com.almasud.intro.model.util.EventMessage;
import com.almasud.intro.ui.activity.HomeActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.almasud.intro.BaseApplication.NOTIFICATION_CHANNEL_UNZIP;

/**
 * A {@link Service} class for file unzip.
 */
public class UnzipService extends Service {
    private static final String TAG = UnzipService.class.getSimpleName();
    public static final String ZIP_FILE = "ZIP_FILE";
    public static final String TARGET_DIRECTORY = "Target_Directory";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: The unzip service is created");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Get download information from intent
        final File zipFile = (File) intent.getSerializableExtra(ZIP_FILE);
        final File targetDirectory = (File) intent.getSerializableExtra(TARGET_DIRECTORY);
        // Notification ID is a unique int for each notification that must be define
        final int notificationId = (int) (System.currentTimeMillis() / 1000);

        // Create a thread to unzip the file
        Thread thread = new Thread(() -> {
            boolean successStatus = false;
            // Dispatch an EventMessage to it's subscribers
            EventBus.getDefault().post(
                    new EventMessage("Unzip starting", EventMessage.TYPE_NORMAL)
            );

            // Create a notify pending intent
            Intent notifyIntent = new Intent(this, HomeActivity.class);
            PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                    this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
            );
            String notificationContent = "Unzipping";

            // Create a notification builder before starting the download
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                    this, NOTIFICATION_CHANNEL_UNZIP
            );
            notificationBuilder.setContentIntent(notifyPendingIntent);
            notificationBuilder.setTicker("Start unzipping");
            notificationBuilder.setOngoing(true);
            notificationBuilder.setAutoCancel(false);
            notificationBuilder.setSmallIcon(R.drawable.ic_file_download_black);
            notificationBuilder.setContentTitle(notificationContent);
            notificationBuilder.setContentText("0%");
            notificationBuilder.setProgress(100, 0, false);

            // Notify by starting the foreground service.
            // Note that: Once a service is started it's not care about the number
            // of start request of the service until the service is stopped.
            startForeground(notificationId, notificationBuilder.build());

            // Try to unzip the zip file
            try (ZipInputStream zis = new ZipInputStream(
                    new BufferedInputStream(new FileInputStream(zipFile)))) {
                ZipEntry ze;
                byte[] buffer = new byte[1024];
                long total = 0, fileLength = zipFile.length();
                int count, tempPercent = 0;

                while ((ze = zis.getNextEntry()) != null) {
                    File file = new File(targetDirectory, ze.getName());
                    File dir = ze.isDirectory() ? file : file.getParentFile();

                    if (!dir.isDirectory() && !dir.mkdirs()) {
                        Log.e(TAG, "download: Couldn't crete the child directory!");

                        // Dispatch an EventMessage to it's subscribers
                        EventBus.getDefault().post(
                                new EventMessage("Couldn't crete the child directory!", EventMessage.TYPE_ERROR)
                        );
                    }

                    if (ze.isDirectory())
                        continue;

                    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                        while ((count = zis.read(buffer)) != -1) {
                            total += count;
                            fileOutputStream.write(buffer, 0, count);
                            int percent = (int) ((total * 100) / fileLength);

                            if (percent > tempPercent) {
                                tempPercent = percent;
                                notificationBuilder.setContentText(percent + "%");
                                notificationBuilder.setProgress(100, percent, false);

                                // Notify by starting the foreground service.
                                startForeground(notificationId, notificationBuilder.build());
                            }
                        }
                    }

                    // Time should be restored as well
                    long time = ze.getTime();
                    if (time > 0)
                        successStatus = file.setLastModified(time);
                }

                // File should be deleted as well
                if (zipFile.exists())
                    successStatus = zipFile.delete();
            } catch (FileNotFoundException e) {
                Log.e(TAG, "download: FileNotFoundException: " + e.getMessage());

                // Dispatch an EventMessage to it's subscribers
                EventBus.getDefault().post(
                        new EventMessage("File is not found!", EventMessage.TYPE_ERROR)
                );
            } catch (IOException e) {
                Log.e(TAG, "download: IOException: " + e.getMessage());

                // Dispatch an EventMessage to it's subscribers
                EventBus.getDefault().post(
                        new EventMessage("Unzip failed!", EventMessage.TYPE_ERROR)
                );
            }

            notificationBuilder.setSmallIcon(successStatus? R.drawable.ic_done_black: R.drawable.ic_error_black);
            notificationBuilder.setContentTitle(successStatus? "Done": "Failed");
            notificationBuilder.setContentText(successStatus? "Unzip completed": "Unzip is not completed");
            notificationBuilder.setOngoing(false);
            notificationBuilder.setAutoCancel(true);
            notificationBuilder.setProgress(0, 0, false);

            // Notify by starting the foreground service.
            startForeground(notificationId, notificationBuilder.build());

            // Dispatch an EventMessage to it's subscribers
            if (successStatus) {
                EventBus.getDefault().post(
                        new EventMessage("Unzip completed!", EventMessage.TYPE_ERROR)
                );
            }
            else {
                SystemClock.sleep(2000);
                EventBus.getDefault().post(
                        new EventMessage("Unzip is not completed!", EventMessage.TYPE_ERROR)
                );
            }

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
        Log.d(TAG, "onDestroy: The unzip service is destroyed.");
    }
}
