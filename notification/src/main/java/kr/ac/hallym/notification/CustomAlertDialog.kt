package kr.ac.hallym.notification

import android.app.DatePickerDialog
import android.app.ProgressDialog.show
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kr.ac.hallym.notification.MainActivity

/**
 *
 * @param this
 *
 */
class CustomAlertDialog(main : Context)  {
    private var context: Context = main;


    // onClickLinster 에 넣을 값  Object : DialogInterface.OnClickListener{ override fun onClick(p0 : DialogInterface, p1 : Int){} }
    fun alertDialogText(){
        AlertDialog.Builder(context).run{ // context는 Main에서 this
            setTitle("title")
            setIcon( android.R.drawable.ic_dialog_info )
            setMessage("message")
            setPositiveButton("ok", null)
            setNegativeButton("cancel", null)
            setNegativeButton("more", null)
            show()
        }
    }
    fun datePickerDialog(){
        DatePickerDialog(context, object:DatePickerDialog.OnDateSetListener {
            override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3:Int) {
                Log.d("namjung","year : $p1, month : {$p2 + 1}")
            }
        }, 2022, 9, 24).show()
    }
    fun timePickerDialog(){
        TimePickerDialog(context, object:TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, p1: Int, p2: Int) {
                Log.d("namjung","time : $p1, minute : $p2")
            }
        }, 13, 0, true).show()
    }

    fun alertDialogSelect(){
        val Items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
        android.app.AlertDialog.Builder(context).run{
            setTitle("items test")
            setIcon(android.R.drawable.ic_dialog_info)
            setItems(Items, object:DialogInterface.OnClickListener{
                override fun onClick(p0:DialogInterface?, p1 : Int){
                    Log.d("namjung", "선택한 과일 : ${Items[p1]}")
                }
            })
            setPositiveButton("닫기", null)
            show()
        }
    }


}