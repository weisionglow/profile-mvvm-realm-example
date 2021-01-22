package com.lwseasy.lwsprofile.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.databinding.Observable
import com.lwseasy.lwsprofile.constant.ClickModel

open class BaseViewModel : ViewModel(), Observable {

    private val clickButton = MutableLiveData<ClickModel>()
    val getClickModel: LiveData<ClickModel> get() = clickButton

    fun clickButton(clickModel: ClickModel) {
        clickButton.value = clickModel
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}