package com.example.mvvm_demo_androidx.ViewModel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_demo_androidx.Repository.RefreshRepository
import com.example.mvvm_demo_androidx.ui.RefreshViewFromState

/**
 * ViewModel：接收View的指令並對Model請求資料，將取得的資料保存起來供View使用。
 *
 * 此時就是MVVM和MVP最不同的地方，ViewModel並不使用callback的方式來通知View，
 * 而是用Observer pattern的概念，由View來訂閱(subscribe)ViewModel中它要的資料，
 * 並在資料異動時才更新UI，因此，MVVM都會搭配如Data Binding等library來實現Observer pattern。
 * */


class RefreshViewModel(private val refreshRepository: RefreshRepository) : ViewModel() {
    //這是存資料
    //用Data Binding中的Observable來讓View和ViewModel溝通。
    val mDataNumber: ObservableField<String> = ObservableField()
    val isLoading: ObservableBoolean = ObservableBoolean(false)
    val description: ObservableField<String> = ObservableField("隨機產生一組數字")

    //DataBinding+LiveData 把上面的資料都彙整成一個RefreshViewFromState
    val refreshViewFormState = MutableLiveData<RefreshViewFromState>()

    //這種寫法可以決定要更新哪幾個參數
    var mRefreshViewFromState = RefreshViewFromState(
        mDataNumber = refreshViewFormState.value?.mDataNumber,
        isLoading = true,
        description = refreshViewFormState.value?.description
    )

    //這是ObservableField修改UI的方式
    fun refresh() {
        isLoading.set(true)
        mDataNumber.set("")
        refreshRepository.retrieveData(object : RefreshRepository.onDataReadyCallback {
            override fun onDataReady(data: String?, loading: Boolean?) {
                description.set("這是使用ObservableField的DataBinding")
                mDataNumber.set(data)
                isLoading.set(loading ?: false)
            }
        })
    }

    //這個是使用LiveData更新FormState再更新UI (如果ObservableField和LiveData兩個一起用感覺好像繞了一圈?)
    fun refreshFormStateClass() {
        Log.v("Bill", "ViewModel===>refreshFormStateClass")
        //
        mRefreshViewFromState.isLoading = true
        refreshViewFormState.value = mRefreshViewFromState
        refreshRepository.retrieveData(object : RefreshRepository.onDataReadyCallback {
            override fun onDataReady(data: String?, loading: Boolean?) {
                mRefreshViewFromState.isLoading = false
                mRefreshViewFromState.description = "這個是使用LiveData更新FormState再更新UI"
                mRefreshViewFromState.mDataNumber = "$data"
                refreshViewFormState.value = mRefreshViewFromState
            }
        })
    }

    //用來實體化ViewModel，但之後可以直接使用Koin套件解決
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