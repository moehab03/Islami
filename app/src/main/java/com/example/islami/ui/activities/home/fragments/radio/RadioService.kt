package com.example.islami.ui.activities.home.fragments.radio

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
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
import com.example.islami.ui.activities.home.HomeActivity
import com.example.islami.ui.data_models.RadioStation
import com.example.islami.ui.utils.Constant

class RadioService : Service() {
    private var mediaPlayer = MediaPlayer()
    private var currentChannel: Int = 0
    private lateinit var notificationRemoteView: RemoteViews
    private var radioStations: ArrayList<RadioStation>? = null
    private val REQUEST_CODE = 100
    private val PLAY = "play"
    private val NEXT = "next"
    private val PREV = "prev"

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        Log.w("RadioService", "service created")
        initRemoteView()
        createNotification()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.w("RadioService", "service started")

        if (radioStations == null) getList(intent!!)

        checkActions(intent)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("RadioService", "service destroied")
        mediaPlayer.reset()
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

    private fun checkActions(intent: Intent?) {
        when (intent?.action) {
            PLAY -> {
                Log.w("RadioService", "Play button pressed")
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    notificationRemoteView.setImageViewResource(
                        R.id.notificationPlayBtn,
                        R.drawable.ic_play
                    )
                } else {
                    mediaPlayer.start()
                    notificationRemoteView.setImageViewResource(
                        R.id.notificationPlayBtn,
                        R.drawable.ic_pause
                    )
                }
                updateNotification()
            }

            NEXT -> {
                Log.w("RadioService", "Next button pressed")
                currentChannel = if (currentChannel < (radioStations!!.size - 1))
                    currentChannel + 1
                else
                    0
                if (currentChannel != -1) changeRadioState()
            }

            PREV -> {
                Log.w("RadioService", "Prev button pressed")
                currentChannel = if (currentChannel > 0) {
                    currentChannel - 1
                } else
                    radioStations!!.size - 1
                if (currentChannel != -1) changeRadioState()
            }

            else -> {
                currentChannel = intent!!.getIntExtra(Constant.CURRENT_INDEX, -1)
                if (currentChannel != -1) changeRadioState()
                else mediaPlayer.pause()
            }
        }
    }

    private fun changeRadioState() {
        //Log.w("changeRadioState", "currentChannel = $currentChannel ")
        mediaPlayer.apply {
            reset()
            setDataSource(radioStations?.get(currentChannel)?.url ?: "")
            prepareAsync()
            setOnPreparedListener {
                start()
            }
        }
        updateNotification()
    }

    private fun initRemoteView() {
        notificationRemoteView = RemoteViews(packageName, R.layout.custom_notification_layout)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constant.NOTIFICATION_CHANNEL_ID,
                Constant.RADIO_NOTIFICATION_CHANNEL,
                NotificationManager.IMPORTANCE_NONE
            )
            channel.setSound(null, null)
            // Register the channel with the system.
            (this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    private fun createNotification() {
        val startIntent = Intent(this, HomeActivity::class.java)
        startIntent.putExtra(Constant.OPEN_RADIO, true)
        val startPendingIntent =
            PendingIntent.getActivity(this, REQUEST_CODE, startIntent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(this, Constant.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.radio_ic)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationRemoteView)
            .setContentIntent(startPendingIntent)
            .setSound(null)
            .build()
        createNotificationChannel()

        (this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            .notify(REQUEST_CODE, notification)

        notificationPlayButtonAction()
        notificationNextButtonAction()
        notificationPrevButtonAction()
    }

    private fun updateNotification() {
        val notification = NotificationCompat.Builder(this, Constant.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.radio_ic)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationRemoteView)
            .setSound(null)
            .build()
        (this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            .notify(REQUEST_CODE, notification)
    }

    private fun notificationPlayButtonAction() {
        val actionIntent = Intent(this, RadioService::class.java).apply {
            action = PLAY
        }
        val actionPendingIntent =
            PendingIntent.getService(
                this,
                REQUEST_CODE + 3,
                actionIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
        notificationRemoteView.setOnClickPendingIntent(
            R.id.notificationPlayBtn,
            actionPendingIntent
        )
    }

    private fun notificationNextButtonAction() {
        val actionIntent = Intent(this, RadioService::class.java).apply {
            action = NEXT
        }
        val actionPendingIntent =
            PendingIntent.getService(
                this,
                REQUEST_CODE + 2,
                actionIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
        notificationRemoteView.setOnClickPendingIntent(
            R.id.notificationNextBtn,
            actionPendingIntent
        )
    }

    private fun notificationPrevButtonAction() {
        val actionIntent = Intent(this, RadioService::class.java).apply {
            action = PREV
        }
        val actionPendingIntent =
            PendingIntent.getService(
                this,
                REQUEST_CODE + 1,
                actionIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
        notificationRemoteView.setOnClickPendingIntent(
            R.id.notificationPreveousBtn,
            actionPendingIntent
        )
    }

}