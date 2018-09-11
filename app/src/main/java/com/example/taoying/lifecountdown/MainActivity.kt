package com.example.taoying.lifecountdown

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.taoying.lifecountdown.widget.CustomDatePicker
import com.example.taoying.utils.CountDownTimerUtil
import com.example.taoying.utils.DateUtils.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private var customDatePicker: CustomDatePicker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //region 初始化CustomDatePicker的起始时间 start, end
        val calendar = Calendar.getInstance()
        var enddate = Date(System.currentTimeMillis())
        calendar.time = enddate
        calendar.add(Calendar.YEAR, 100)
        enddate = calendar.time

        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
        val start = sdf.format(Date(System.currentTimeMillis()))
        terminationDate.text = start
        val end = sdf.format(enddate)
        //currentTime.text = end
        //endregion

        /** 倒计时，一次1秒 */
        val timer = object : CountDownTimerUtil(CalcDiffLong(terminationDate.text.toString(), start), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // TODO Auto-generated method stub
                tv_CountDown.text = "还剩" + getDiffToString(millisUntilFinished)
            }

            override fun onFinish() {
                tv_CountDown.text = "倒计时完毕了"
            }
        }

        customDatePicker = CustomDatePicker(this, CustomDatePicker.ResultHandler { time ->
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
            val current = sdf.format(Date(System.currentTimeMillis()))
            if(terminationDate.text != time) {
                // 回调接口，获得选中的时间
                terminationDate.text = time + ":00"

                // 重置定时器的传入值
                timer.setMillisInFuture(CalcDiffLong(terminationDate.text.toString(), current))  //设置总时长 微秒级别
                //timer.setCountdownInterval(1000)    //设置间隔 毫秒级别

                // 开启定时器
                timer.start()
            }
        }, start, end) // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker!!.showSpecificTime(true) // 显示时和分
        customDatePicker!!.setIsLoop(true) // 允许循环滚动

        selectTime.setOnClickListener {
            customDatePicker!!.show(terminationDate.text.toString())
        }

        //region test
//        toast(date)
//        toast(DateUtils.getDateToString(1000)) //时间戳转为时间格式
//        toast(DateUtils.getStringToDate(date).toString()) //时间格式转为时间戳
//        toast(formatTime(DateUtils.getStringToDate(date)))
//        toast(CalcDiffStr("2018-09-11 00:00:00",date))
        //endregion
    }


    //region 利用反射动态地改变CountDownTimer类的私有字段
//    //利用反射动态地改变CountDownTimer类的私有字段mCountdownInterval
//    private fun changeCountdownInterval(time: Long) {
//        try {
//            // 反射父类CountDownTimer的mCountdownInterval字段，动态改变回调频率
//            val clazz = Class.forName("android.os.CountDownTimer")
//            val field = clazz.getDeclaredField("mCountdownInterval")
//            //从Toast对象中获得mTN变量
//            field.isAccessible = true
//            field.set(timethis, time)
//        } catch (e: Exception) {
//            Log.e("Ye", "反射类android.os.CountDownTimer.mCountdownInterval失败：$e")
//        }
//
//    }
//
//    //利用反射动态地改变CountDownTimer类的私有字段mMillisInFuture
//    private fun changeMillisInFuture(time: Long) {
//        try {
//            // 反射父类CountDownTimer的mMillisInFuture字段，动态改变定时总时间
//            val clazz = Class.forName("android.os.CountDownTimer")
//            val field = clazz.getDeclaredField("mMillisInFuture")
//            field.isAccessible = true
//            field.set(timethis, time)
//        } catch (e: Exception) {
//            Log.e("Ye", "反射类android.os.CountDownTimer.mMillisInFuture失败： $e")
//        }
//
//    }
    //endregion
}
