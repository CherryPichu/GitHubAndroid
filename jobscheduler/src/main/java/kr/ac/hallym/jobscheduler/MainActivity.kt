package kr.ac.hallym.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.ac.hallym.jobscheduler.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    companion object {
        private val TAG = "MainActivity"
        val JOB_ID_A = 100
        val JOB_ID_B = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val js = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val serviceComponent = ComponentName(this, MyJobService::class.java)

        binding.btnJob1.setOnClickListener {
            val js = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            val serviceComponent = ComponentName(this, MyJobService::class.java)
            val jobInfo = JobInfo.Builder(JOB_ID_A, serviceComponent)
                .setMinimumLatency( TimeUnit.MINUTES.toMillis(1) )
                .setOverrideDeadline( TimeUnit.MINUTES.toMillis(3) )
                .build()
            js.schedule(jobInfo)
            Log.d(TAG, "Scheduled JobA")
        }

        binding.btnJob2.setOnClickListener {
            val js = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            val serviceComponent = ComponentName(this, MyJobService::class.java)
            val jobInfo = JobInfo.Builder(JOB_ID_B, serviceComponent)
                .setRequiresDeviceIdle(true)
                .setRequiresCharging(true)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15))
                .build()
            js.schedule(jobInfo)
            Log.d(TAG, "Scheduled JobB")
        }

    }
}