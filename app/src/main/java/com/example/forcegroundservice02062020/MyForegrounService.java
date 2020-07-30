package com.example.forcegroundservice02062020;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyForegrounService extends Service {

    String CHANNEL_ID = "my_channel_01";
    Integer count = 0;
    NotificationCompat.Builder notification;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        notification = createNotification();
        startForeground(1, notification.build());
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NotificationCompat.Builder notify = createNotification();
                final NotificationManager notificationManager = (NotificationManager) getApplicationContext()
                        .getSystemService(getApplicationContext().NOTIFICATION_SERVICE);

                notificationManager.notify(1, notify.build());
//                if (count >= 10){
//                    stopSelf();
//                }else{
//
//                }

            }
        },1000);
        return START_REDELIVER_INTENT;
    }

    private NotificationCompat.Builder createNotification(){
        notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Dich vu dang hoat dong")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(count++ + "");
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            ((NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE))
                    .createNotificationChannel(channel);
        }
        return notification;
    }

}
