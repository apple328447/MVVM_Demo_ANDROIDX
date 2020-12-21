package com.example.mvvm_demo_androidx.LiveData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_demo_androidx.R
import com.example.mvvm_demo_androidx.testView.RefreshRepository
import com.example.mvvm_demo_androidx.testView.RefreshViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_live_data.*
import java.lang.Exception

class LiveDataActivity : AppCompatActivity() {

    //1.建立ViewModel 名稱要跟Xml的 <data>裡面的name一樣
    private lateinit var refreshViewModel: RefreshViewModel

    //3.建立Repository
    private val refreshRepository = RefreshRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        try {
            //4.在onCreat讓ViewModel用Factory(方法寫在自己的ViewModle裡面)實體化
            refreshViewModel =
                ViewModelProvider(this, RefreshViewModel.Factory(refreshRepository)).get(
                    RefreshViewModel::class.java
                )
            //7.LiveData
            initLiveData()

            initButton()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initLiveData() {
        // observe是發現ViewModel裡面的資料有更動時要做的事情
        refreshViewModel.refreshViewFormState.observe(this, Observer {
            val viewState = it ?: return@Observer //這裡返回的是
            Log.v("Bill", "LiveData===>observe:   ${Gson().toJson(viewState)}")

            if (viewState.mDataNumber != null) {
                txtNumber.text = viewState.mDataNumber
            }
            if (viewState.description != null) {
                txtDescription.text = viewState.description
            }
            when (viewState.isLoading) {
                true -> {
                    progressBar.visibility = View.VISIBLE
                    btnRefresh.isEnabled = false
                }
                false -> {
                    progressBar.visibility = View.GONE
                    btnRefresh.isEnabled = true
                }
            }
        })
    }

    fun initButton() {
        btnRefresh.setOnClickListener {
            refreshViewModel.refreshFormStateClass()
        }
    }

}