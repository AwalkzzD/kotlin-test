package com.example.myapplication

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.myapplication.databinding.ActivityRatingBinding

class RatingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRatingBinding
    private val CHANNEL_ID = "custom_notifications"
    private val CHANNEL_NAME = "testNotifications"
    private val NOTIF_ID = 0

    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotifChannel()
        val notif = NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("Rating Activity")
            .setContentText("Thanks for rating us!").setSmallIcon(R.drawable.avatar)
            .setPriority(NotificationCompat.PRIORITY_HIGH).build()
        val notifManger = NotificationManagerCompat.from(this)

        binding.appRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            when (binding.appRating.rating) {
                1F -> setRatingMsg("Needs Improvement")
                2F -> setRatingMsg("Good")
                3F -> setRatingMsg("Great")
                4F -> setRatingMsg("Fantastic")
                5F -> setRatingMsg("Excellent")
            }
        }

        binding.rateApp.setOnClickListener {
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse("market://search?q=calculator&c=apps")
                    )
                )
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("vnd.youtube:watch?v=MTpCQTx0kqc&ab_channel=4KVideoNature-FocusMusic")
                    )
                )
            }
//            Toast.makeText(this, "Thanks for rating this app!", Toast.LENGTH_SHORT).show()
            if (ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                notifManger.notify(NOTIF_ID, notif)
            } else {
                Toast.makeText(this, "Notification Persmission not granted", Toast.LENGTH_LONG)
                    .show()
            }

        }
    }

    private fun setRatingMsg(ratingMsg: String) {
        binding.ratingMsg.text = ratingMsg
    }

    private fun createNotifChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.BLUE
                enableLights(true)
            }
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}

