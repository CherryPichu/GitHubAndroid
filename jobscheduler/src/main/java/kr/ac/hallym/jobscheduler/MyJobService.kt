package kr.ac.hallym.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class MyJobService :JobService() {
   override fun onCreate(){
       Log.d("namjung", "Scheduled onCreate")
   }

    override fun onDestroy() {
        Log.d("namjung", "Scheduled onDestory")
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d("namjung", "Start Job")
        val notify = Notification(this@MyJobService)
        notify.notification()
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d("namjung", "Stop Job")
        return false // false : 작업이 완벽하게 종료되었음을 의미한다.
        // true : 작입이 아직 끝나지 않았음을 의미한다.
    }


}