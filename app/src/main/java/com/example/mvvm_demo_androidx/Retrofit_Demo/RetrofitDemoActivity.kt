package com.example.mvvm_demo_androidx.Retrofit_Demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_demo_androidx.R
import com.example.mvvm_demo_androidx.Retrofit_Demo.InputOutput.UserData
import com.example.mvvm_demo_androidx.Retrofit_Demo.Reposiory.RetrofitRepository
import com.example.mvvm_demo_androidx.Retrofit_Demo.ViewModel.RetrofitViewModel
import kotlinx.android.synthetic.main.activity_retrofit_demo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
/**
 * Retrofit 使用範例 2021/06/15 Bill 筆記整理
 *
 * Retrofit的使用方式:
 *  1.先建立 Service(interface)，設應好 Result 還有 input 的 class
 *  2.建立 RetrofitManager -> 這個主要就是設定 Retrofit(之後其他Service會一起共用)的基底，包含一些 OkhttpClientBuilder 可以去設定一些 Timeout的參數。
 *  3.建立靜態檔案(Object)專門放各種會用到的Service，等於說一開始專案就先實體化這些 API，並且把 RetrofitManager的設定都匯入其中，並且 create。
 *    (不知道會不會很佔內存，但至少目前公司專案這樣用還沒發生問題。)
 *  4.
 *
 *
 *
 *
 * */

class RetrofitDemoActivity : AppCompatActivity() {

    //1.建立ViewModel
    private lateinit var retrofitViewModel: RetrofitViewModel

    //2.建立Repository
    private val retrofitRepository = RetrofitRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_demo)

        //3.在onCreat讓ViewModel用Factory(方法寫在自己的ViewModle裡面)實體化
        retrofitViewModel =
            ViewModelProvider(this, RetrofitViewModel.Factory(retrofitRepository)).get(
                RetrofitViewModel::class.java
            )

        initButtonEvent()


    }

    private fun initButtonEvent() {

        btn.setOnClickListener {
            try {
                retrofitViewModel.getAllUserData()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        btn2.setOnClickListener {
            try {
                val apiService: APIService =
                    RetrofitManager.client.create(APIService::class.java)
                apiService.getAllUserData().enqueue(object : Callback<List<UserData>> {
                    override fun onResponse(
                        call: Call<List<UserData>>,
                        response: Response<List<UserData>>
                    ) {
                        Log.i("Bill", "${response.body()?.get(0)?.title}")
                    }

                    override fun onFailure(call: Call<List<UserData>>, t: Throwable) {
                        Log.e("Bill", "$t")
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        btn3.setOnClickListener {
            try {
                retrofitViewModel.getResponse()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

}