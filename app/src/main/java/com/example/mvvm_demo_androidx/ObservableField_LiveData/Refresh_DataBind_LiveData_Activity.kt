package com.example.mvvm_demo_androidx.ObservableField_LiveData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_demo_androidx.R
import com.example.mvvm_demo_androidx.databinding.ActivityRefreshDataBindLiveDataBinding
import com.example.mvvm_demo_androidx.testView.RefreshRepository
import com.example.mvvm_demo_androidx.testView.RefreshViewModel
import com.google.gson.Gson
import java.lang.Exception
//import kotlinx.android.synthetic.main.fragment_bet_record_search.*

class Refresh_DataBind_LiveData_Activity : AppCompatActivity() {

    //1.建立ViewModel 名稱要跟Xml的 <data>裡面的name一樣
    private lateinit var refreshViewModel: RefreshViewModel

    //2.建立DataBinding
    private lateinit var refreshViewBinding: ActivityRefreshDataBindLiveDataBinding

    //3.建立Repository
    private val refreshRepository = RefreshRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh__data_bind__live_data_)

        try {
            //4.在onCreat讓ViewModel用Factory(方法寫在自己的ViewModle裡面)實體化
            refreshViewModel =
                ViewModelProvider(this, RefreshViewModel.Factory(refreshRepository)).get(
                    RefreshViewModel::class.java
                )
            //5.在onCreat初始化Binding要綁定的畫面，就不用setContentView(R.layout.xxx)了
            refreshViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_refresh__data_bind__live_data_)
            //6.在onCreat設定DataBinding綁定的ViewModle和生命週期
            refreshViewBinding.apply {
                refreshViewModel = this@Refresh_DataBind_LiveData_Activity.refreshViewModel
                lifecycleOwner = this@Refresh_DataBind_LiveData_Activity
            }
            //7.LiveData
            initLiveData()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initLiveData(){
      // observe是發現ViewModel裡面的資料有更動時要做的事情
        refreshViewModel.refreshViewFormState.observe(this, Observer{
            val viewState = it ?: return@Observer
            Log.v("Bill","LiveData===>observe:   ${Gson().toJson(viewState)}")
        })
    }
}