package com.example.mvvm_demo_androidx.utils

import com.example.mvvm_demo_androidx.R
import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {
    /**
     * 時間(TimeInMillis) 轉換成 HH:mm:ss 格式
     * 若小時數為 0，則顯示 mm:ss
     *
     * @param time: TimeInMillis
     * @return :
     */
    fun longToHHmmss(time: Long): String? {
        return try {
            val hour = time / 1000 / 60 / 60
            val min = time / 1000 / 60 % 60
            val sec = time / 1000 % 60
            if (hour == 0L) {
                String.format("%02d:%02d", min, sec)
            } else String.format("%02d:%02d:%02d", hour, min, sec)
        } catch (e: Exception) {
            e.printStackTrace()
            "--:--"
        }
    }
    /**
     * 取得現在日期
     * */
    fun getNowDateTime():String?{
        var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(Date())
    }
    /**
     * return : 星期幾
     */
    fun setupDayOfWeek(todayMillis: Long?): Int {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = todayMillis ?:0

        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.SUNDAY -> R.string.sunday
            Calendar.MONDAY -> R.string.monday
            Calendar.TUESDAY -> R.string.tuesday
            Calendar.WEDNESDAY -> R.string.wednesday
            Calendar.THURSDAY -> R.string.thursday
            Calendar.FRIDAY -> R.string.friday
            else -> R.string.sunday
        }
    }

}