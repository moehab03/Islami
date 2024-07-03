package com.example.islami.ui.activities.home.fragments.radio

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.islami.R
import com.example.islami.ui.data_models.RadioStation
import com.example.islami.ui.utils.Constant

class RadioService : Service() {
    private var mediaPlayer = MediaPlayer()
    private var currentChannel: Int = 0
    private lateinit var notificationRemoteView: RemoteViews
    private var radioStations: ArrayList<RadioStation>? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        Log.w("RadioService", "service created")
        initRemoteView()
        createNotification()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.w("RadioService", "service started")
        intent.let {
            if(radioStations == null)
                getList(intent!!)

            changeRadioState(intent!!)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("RadioService", "service destroied")
    }

    private fun getList(intent: Intent) {
        radioStations = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU)
            intent.getParcelableArrayListExtra(
                Constant.RADIOS_STATION, RadioStation::class.java
            )
        else
            intent.getParcelableArrayListExtra(Constant.RADIOS_STATION)

        //Log.w("getList","List = $radioStations")
    }

    private fun changeRadioState(intent: Intent) {
        currentChannel = intent.getIntExtra(Constant.CURRENT_INDEX, 0)

        //Log.w("changeRadioState", "currentChannel = $currentChannel ")

        if (currentChannel >= 0) {
            mediaPlayer.apply {
                reset()
                setDataSource(radioStations?.get(currentChannel)?.url ?: "")
                prepareAsync()
                setOnPreparedListener {
                    start()
                }
            }
        } else
            mediaPlayer.pause()
    }

    private fun initRemoteView() {
        notificationRemoteView = RemoteViews(this.packageName, R.layout.custom_notification_layout)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constant.NOTIFICATION_CHANNEL_ID,
                Constant.RADIO_NOTIFICATION_CHANNEL,
                NotificationManager.IMPORTANCE_NONE
            )
            // Register the channel with the system.
            (this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    private fun createNotification() {
        val notification = NotificationCompat.Builder(this, Constant.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.radio_ic)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationRemoteView)
            .build()
        createNotificationChannel()

        (this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            .notify(100, notification)
    }

}