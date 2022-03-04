package com.example.letmeapp.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.navigation.NavDeepLinkBuilder;

import com.example.letmeapp.LetMeApplication;
import com.example.letmeapp.R;

import java.util.Random;

public class RequestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Receiver", "request recibido");
        /*
        Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);*/
        showNotification(context);
    }

    private void showNotification(Context context) {
        PendingIntent pendingIntent = new NavDeepLinkBuilder(context)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.requestFragment)
                .createPendingIntent();
        Notification.Builder builder = new Notification.Builder(context, LetMeApplication.IDCHANNEL)
                .setSmallIcon(R.drawable.letmeapp)
                .setContentTitle("Request received")
                .setContentText("New Request have been received")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(1000), builder.build());

    }
}
