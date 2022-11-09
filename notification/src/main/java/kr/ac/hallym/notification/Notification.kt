package kr.ac.hallym.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

import kr.ac.hallym.notification.databinding.ActivityMainBinding

class Notification( val context : Context  ) {
    private val applicationContext: Context = context.applicationContext



    fun notification(){
//        applicationContext.context

        val manager = context.getSystemService( NOTIFICATION_SERVICE ) as NotificationManager
        val builder : NotificationCompat.Builder

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelId = "one-channel"
            val channelName = "My channel One"
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "My Channel One Description"
                setShowBadge(true)
                val uri : Uri =
                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)

            }

            manager.createNotificationChannel(channel)

            builder = NotificationCompat.Builder(context, channelId)
        }else {
            builder = NotificationCompat.Builder(context)
        }
        builder.run{
            setSmallIcon(R.drawable.small)
            setWhen(System.currentTimeMillis())
            setContentTitle("허남정")
            setContentText("안녕하세요")
            setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.big))
        }

        val KEY_TEXT_REPLY = "key_text_reply"
        val replyLabel = "답장"
        var remoteInput : RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run{
            setLabel(replyLabel)
            build()
        }

        val replyIntent = Intent(context, ReplyReceiver::class.java)
        val replyPandingIntent = PendingIntent.getBroadcast(context, 30,
            replyIntent, PendingIntent.FLAG_MUTABLE)

        builder.addAction(
            NotificationCompat.Action.Builder(
                R.drawable.send, "답장", replyPandingIntent).addRemoteInput(remoteInput).build()
        )

        manager.notify(11, builder.build())
    }
}