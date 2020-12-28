package com.example.mvvm_demo_androidx.RecyclerViewDemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mvvm_demo_androidx.Repository.RefreshRepository
import com.example.mvvm_demo_androidx.database.ListData
import kotlinx.coroutines.launch

class MoodListViewModel(private val repository: MoodListRepository) : ViewModel() {

    var data = MutableLiveData<MutableList<ListData>>()

    var recyclerViewData: MutableList<ListData>? = null


    fun AddMoodData() {
        viewModelScope.launch {
            var newData = repository.addOneMood()
            var oldData = data.value ?: mutableListOf()
            oldData?.add(newData)
            data.value = oldData
        }
    }

    fun ClearMoodList() {
        data.value = mutableListOf()
    }

    //用來實體化ViewModel，但之後可以直接使用Koin套件解決
    class Factory(var moodListRepository: MoodListRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MoodListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MoodListViewModel(moodListRepository) as T
            }
            throw IllegalAccessException("Unable to construct view model")
        }
    }
}