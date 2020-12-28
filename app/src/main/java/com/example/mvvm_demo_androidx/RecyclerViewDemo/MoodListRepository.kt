package com.example.mvvm_demo_androidx.RecyclerViewDemo

import com.example.mvvm_demo_androidx.database.ListData
import com.example.mvvm_demo_androidx.utils.TimeUtils

class MoodListRepository {

    fun addOneMood(): ListData {
        var listData = ListData()
        listData.moodIndex = (Math.random() * 10).toInt()
        listData.time = TimeUtils().getNowDateTime()?:""
        return listData
    }
}