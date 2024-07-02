package com.example.islami.ui.activities.home.fragments.radio

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.islami.R
import com.example.islami.ui.activities.home.HomeActivity
import com.example.islami.ui.data_models.RadiosItem

class RadioService : Service() {

    var radioList = listOf<RadiosItem?>()

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        Log.e("RadioService", "service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("RadioService","service started")
        createNotification(R.drawable.radio_ic)
        return START_STICKY
    }

    private fun createPendingIntentToOpenHomeScreen(): PendingIntent {
        return PendingIntent.getActivity(
            this,
            100,
            Intent(this, HomeActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun createNotification(
        smallIconID: Int
    ) {
        val notification = NotificationCompat.Builder(this, "100")
            .setSmallIcon(smallIconID)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(
                RemoteViews(
                    this.packageName,
                    R.layout.custom_notification_layout
                )
            )
            .setContentIntent(createPendingIntentToOpenHomeScreen())
            .build()
        createNotificationChannel()

        startForeground(100, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "100",
                "RadioNotification",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            // Register the channel with the system.
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("RadioService", "service destroied")
    }
}