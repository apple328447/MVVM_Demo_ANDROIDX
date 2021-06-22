package com.example.mvvm_demo_androidx.Retrofit_Demo.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mvvm_demo_androidx.RecyclerViewDemo.MoodListRepository
import com.example.mvvm_demo_androidx.RecyclerViewDemo.MoodListViewModel
import com.example.mvvm_demo_androidx.Retrofit_Demo.Reposiory.RetrofitRepository
import com.example.mvvm_demo_androidx.Retrofit_Demo.RetrofitDemoApi
import kotlinx.coroutines.launch

class RetrofitViewModel(
    private val retrofitRepository: RetrofitRepository
) : ViewModel() {

    //Get
    fun getAllUserData() {
        viewModelScope.launch {
            try {
                retrofitRepository.getUserData()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    //測試用Response的方式
    fun getResponse(){
        viewModelScope.launch {
            try {
                retrofitRepository.getResponse()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    //用來實體化ViewModel，但之後可以直接使用Koin套件解決
    class Factory(var retrofitRepository: RetrofitRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RetrofitViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return RetrofitViewModel(retrofitRepository) as T
            }
            throw IllegalAccessException("Unable to construct view model")
        }
    }

}