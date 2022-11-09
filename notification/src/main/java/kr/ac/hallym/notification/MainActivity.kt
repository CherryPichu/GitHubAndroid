package kr.ac.hallym.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION_CODES.N
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat
import kr.ac.hallym.notification.databinding.ActivityMainBinding

open class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding;
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        val customdialog = CustomAlertDialog(this)
        binding.buttonAlert.setOnClickListener {
            customdialog.alertDialogText();
        }
        binding.buttonDatePickerDialog.setOnClickListener {
            customdialog.datePickerDialog()
        }
        binding.buttonTimePickerDialog.setOnClickListener {
            customdialog.timePickerDialog()
        }
        binding.buttonAlertDialogSelect.setOnClickListener {
            customdialog.alertDialogSelect()
        }
        binding.buttonShowToast.setOnClickListener {
            showToast()
        }
        val notify = Notification(this@MainActivity)
        binding.buttonNotification.setOnClickListener {
            notify.notification()
        }



    }

//    fun notification(){ // Notification에 구현가능.
//
//        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        val builder : NotificationCompat.Builder
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            val channelId = "one-channel"
//            val channelName = "My channel One"
//            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
//                description = "My Channel One Description"
//                setShowBadge(true)
//                val uri : Uri =
//                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//                val audioAttributes = AudioAttributes.Builder()
//                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                    .setUsage(AudioAttributes.USAGE_ALARM)
//                    .build()
//                setSound(uri, audioAttributes)
//                enableVibration(true)
//
//            }
//
//            manager.createNotificationChannel(channel)
//
//            builder = NotificationCompat.Builder(this, channelId)
//        }else {
//            builder = NotificationCompat.Builder(this)
//        }
//        builder.run{
//            setSmallIcon(R.drawable.small)
//            setWhen(System.currentTimeMillis())
//            setContentTitle("허남정")
//            setContentText("안녕하세요")
//            setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.big))
//        }
//
//        val KEY_TEXT_REPLY = "key_text_reply"
//        val replyLabel = "답장"
//        var remoteInput : RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run{
//            setLabel(replyLabel)
//            build()
//        }
//
//        val replyIntent = Intent(this, ReplyReceiver::class.java)
//        val replyPandingIntent = PendingIntent.getBroadcast(this, 30,
//            replyIntent, PendingIntent.FLAG_MUTABLE)
//
//        builder.addAction(
//            NotificationCompat.Action.Builder(
//                R.drawable.send, "답장", replyPandingIntent).addRemoteInput(remoteInput).build()
//        )
//
//        manager.notify(11, builder.build())
//    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun showToast() {
        val toast = Toast.makeText(this, "종료하려면 한 번 더 누르세요.", Toast.LENGTH_SHORT)
        toast.addCallback(
            object : Toast.Callback(){
                override fun onToastHidden(){
                    super.onToastHidden()
                    Log.d("namjung", "toast hidden")
                }

                override fun onToastShown() {
                    super.onToastShown()
                    Log.d("namjung", "toast shown")
                }
            }
        )
        toast.show()
    }

}