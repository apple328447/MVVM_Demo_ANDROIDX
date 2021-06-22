package com.example.mvvm_demo_androidx.Retrofit_Demo

object RetrofitDemoApi {
    val apiService: APIService
        get() = RetrofitManager.client.create(APIService::class.java)
}