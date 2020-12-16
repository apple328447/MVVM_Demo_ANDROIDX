package com.example.mvvm_demo_androidx.testView

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * ViewModel：接收View的指令並對Model請求資料，將取得的資料保存起來供View使用。
 *
 * 此時就是MVVM和MVP最不同的地方，ViewModel並不使用callback的方式來通知View，
 * 而是用Observer pattern的概念，由View來訂閱(subscribe)ViewModel中它要的資料，
 * 並在資料異動時才更新UI，因此，MVVM都會搭配如Data Binding等library來實現Observer pattern。
 * */


class RefreshViewModel(private val refreshRepository: RefreshRepository)  : ViewModel() {
    //這是存資料
    //用Data Binding中的Observable來讓View和ViewModel溝通。
    val mData: ObservableField<String> = ObservableField()
    val isLoading: ObservableBoolean = ObservableBoolean(false)
    val description: ObservableField<String> = ObservableField("隨機產生一組數字")

    fun refresh() {
        isLoading.set(true)
        mData.set("")
        refreshRepository.retrieveData(object : RefreshRepository.onDataReadyCallback {
            override fun onDataReady(data: String?, loading: Boolean?) {
                mData.set(data)
                isLoading.set(loading ?: false)
            }
        })
    }

    class Factory(var refreshRepository: RefreshRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RefreshViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return RefreshViewModel(refreshRepository) as T
            }
            throw IllegalAccessException("Unable to construct view model")
        }
    }
}