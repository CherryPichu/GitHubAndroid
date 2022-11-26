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