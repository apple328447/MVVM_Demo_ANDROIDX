package com.example.mvvm_demo_androidx.testView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_demo_androidx.R
import com.example.mvvm_demo_androidx.databinding.ActivityRefreshViewBinding
import org.koin.core.definition.Kind
import java.lang.Exception

class RefreshViewActivity : AppCompatActivity() {

    //1.建立ViewModel 名稱要跟Xml的 <data>裡面的name一樣
    private lateinit var refreshViewModel: RefreshViewModel

    //2.建立DataBinding
    private lateinit var refreshViewBinding: ActivityRefreshViewBinding

    //3.建立Repository
    private val refreshRepository = RefreshRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_refresh_view)

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
                refreshViewModel = this@RefreshViewActivity.refreshViewModel
                lifecycleOwner = this@RefreshViewActivity
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}