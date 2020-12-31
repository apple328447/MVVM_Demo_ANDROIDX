package com.example.mvvm_demo_androidx.RecyclerViewDemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_demo_androidx.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_live_data.*
import kotlinx.android.synthetic.main.activity_recycler_view_demo.*
import java.lang.Exception

class MoodListActivity : AppCompatActivity() {

    //1.建立ViewModel
    private lateinit var moodListViewModel: MoodListViewModel

    //2.建立Repository
    private val moodListRepository = MoodListRepository()

    //RecyclerView 的 Adapter
    var adapter = MoodListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_demo)
        try {
            //3.在onCreat讓ViewModel用Factory(方法寫在自己的ViewModle裡面)實體化
            moodListViewModel =
                ViewModelProvider(this, MoodListViewModel.Factory(moodListRepository)).get(
                    MoodListViewModel::class.java
                )
            initLiveData()
            initButton()
            initRecyclerView()

        } catch (e: Exception) {
            e.printStackTrace()
        }
        initLiveData()
    }

    private fun initLiveData() {
        // observe是發現ViewModel裡面的資料有更動時要做的事情
        moodListViewModel.mRvData.observe(this, Observer {
            val moodListData = it ?: return@Observer //這裡返回的是
            Log.v("Bill", "LiveData===>observe:   ${Gson().toJson(moodListData)}")
            adapter.data = moodListData
        })
    }

    private fun initButton() {
        btn_add.setOnClickListener {
            moodListViewModel.AddMoodData()
        }
        btn_clear.setOnClickListener {
            moodListViewModel.ClearMoodList()
        }
    }

    private fun initRecyclerView() {
        rv_mood_list.layoutManager = LinearLayoutManager(this@MoodListActivity, LinearLayoutManager.VERTICAL, false)//LinearLayout
        rv_mood_list.adapter = adapter
    }
}