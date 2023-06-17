package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.models.DataModel

class MainViewModel : ViewModel() {
    val liveDataCurrent = MutableLiveData<DataModel>()
    val liveDataList = MutableLiveData<List<DataModel>>()



}