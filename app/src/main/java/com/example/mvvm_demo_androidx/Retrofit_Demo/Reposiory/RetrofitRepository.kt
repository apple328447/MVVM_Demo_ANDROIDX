package com.example.mvvm_demo_androidx.Retrofit_Demo.Reposiory

import android.util.Log
import com.example.mvvm_demo_androidx.Retrofit_Demo.RetrofitDemoApi
import com.example.mvvm_demo_androidx.Retrofit_Demo.InputOutput.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitRepository {
    fun getUserData() {
        try {
            val call: Call<List<UserData>> =
                RetrofitDemoApi.apiService.getAllUserData() // 這裡可以再更新LiveDATA
            call.enqueue(object : Callback<List<UserData>> {
                //同步/非同步(suspend) 是看這裡用哪個方法
                override fun onResponse(
                    call: Call<List<UserData>>,
                    response: Response<List<UserData>>
                ) {
                    Log.i("Bill===>", "${response.body()}")

                }

                override fun onFailure(call: Call<List<UserData>>, t: Throwable) {
                    Log.e("Bill", "$t")
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getResponse() {
        try {
            val result = RetrofitDemoApi.apiService.getAllUserData2()
            Log.i("Bill===>getResponse", "${result.body()?.get(0)}")

            /**這要特別注意，2.5.0以下，版本太舊，用response<T> 會報錯誤:
            No Retrofit annotation found. (parameter #1)for method APIService.getAllUserData2*/
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
