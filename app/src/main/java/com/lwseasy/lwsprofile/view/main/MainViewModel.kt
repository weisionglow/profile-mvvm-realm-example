package com.lwseasy.lwsprofile.view.main

import com.lwseasy.lwsprofile.base.BaseViewModel
import com.lwseasy.lwsprofile.repo.PersonRepo

class MainViewModel : BaseViewModel() {

    private val personRepo by lazy { PersonRepo() }
    val personData = personRepo.getPersonData

    init {
        loadPersonData()
    }

    fun loadPersonData() {
        personRepo.loadPersonDetail()
    }
}