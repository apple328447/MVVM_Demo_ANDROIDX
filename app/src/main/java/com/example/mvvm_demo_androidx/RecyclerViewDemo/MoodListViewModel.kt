package com.example.mvvm_demo_androidx.RecyclerViewDemo

import androidx.lifecycle.*
import com.example.mvvm_demo_androidx.database.ListData
import kotlinx.coroutines.launch

class MoodListViewModel(private val repository: MoodListRepository) : ViewModel() {

    val mRvData :LiveData<MutableList<ListData>>
        get() = _mRvData

    private var _mRvData = MutableLiveData<MutableList<ListData>>()

    fun AddMoodData() {
        viewModelScope.launch {
            var newData = repository.addOneMood()
            var oldData = _mRvData.value ?: mutableListOf()
            oldData?.add(newData)
            _mRvData.value = oldData
        }
    }

    fun ClearMoodList() {
        _mRvData.value = mutableListOf()
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