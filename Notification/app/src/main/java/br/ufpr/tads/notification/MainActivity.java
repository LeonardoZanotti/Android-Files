package br.ufpr.tads.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendNotification(View view) {
        int id = 1;
        int icon = android.R.drawable.ic_dialog_info;

        Intent intent = new Intent(this, Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, id, intent, 0);

        Notification n = new Notification.Builder(this)
                .setContentTitle("Some notification title")
                .setContentText("Some notification text")
                .setSmallIcon(icon)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(icon, "Call", pendingIntent)
                .addAction(icon, "More", pendingIntent)
                .addAction(icon, "And more", pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(id, n);
    }
}