package com.example.mvvm_demo_androidx.ObservableField

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_demo_androidx.R
import com.example.mvvm_demo_androidx.databinding.ActivityRefreshViewBinding
import com.example.mvvm_demo_androidx.Repository.RefreshRepository
import com.example.mvvm_demo_androidx.refreshViewModel.RefreshViewModel
import java.lang.Exception

class ObservableFieldActivity : AppCompatActivity() {

    //1.建立ViewModel 名稱要跟Xml的 <data>裡面的name一樣
    private lateinit var refreshViewModel: RefreshViewModel

    //2.建立DataBinding
    private lateinit var refreshViewBinding: ActivityRefreshViewBinding

    //3.建立Repository
    private val refreshRepository = RefreshRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            //4.在onCreat讓ViewModel用Factory(方法寫在自己的ViewModle裡面)實體化
            refreshViewModel =
                ViewModelProvider(this, RefreshViewModel.Factory(refreshRepository)).get(
                    RefreshViewModel::class.java
                )
            //5.在onCreat初始化Binding要綁定的畫面，就不用setContentView(R.layout.xxx)了
            refreshViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_refresh_view)
            //6.在onCreat設定DataBinding綁定的ViewModle和生命週期
            refreshViewBinding.apply {
                refreshViewModel = this@ObservableFieldActivity.refreshViewModel
                lifecycleOwner = this@ObservableFieldActivity

                //7.這裡可以放Xml要用到的資料
                listData = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //TODO Bill 如果是綁在Fragment應該就是用下面的方式
    //在onCreateView
    //val binding:xxxxBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_info_center_detail_list,container,false)
    //然後把return super.onCreateView(inflater, container, savedInstanceState)去掉換成return binding.root放在最下面一行
}