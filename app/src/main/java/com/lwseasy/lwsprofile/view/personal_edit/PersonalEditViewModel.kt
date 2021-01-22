package com.lwseasy.lwsprofile.view.personal_edit

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.lwseasy.lwsprofile.base.BaseViewModel
import com.lwseasy.lwsprofile.realm.Person
import com.lwseasy.lwsprofile.repo.PersonRepo

class PersonalEditViewModel : BaseViewModel() {

    private val personRepo by lazy { PersonRepo() }
    val personData = personRepo.getPersonData
    val personUpdateSuccess = personRepo.getUpdateSuccess

    @Bindable
    val nameEditText = MutableLiveData<String>()

    @Bindable
    val titleEditText = MutableLiveData<String>()

    @Bindable
    val emailEditText = MutableLiveData<String>()

    @Bindable
    val phoneEditText = MutableLiveData<String>()

    @Bindable
    val aboutEditText = MutableLiveData<String>()

    init {
        loadPersonData()
    }

    fun setupAllDetail(person: Person?) {
        if (person != null) {
            nameEditText.value = person.name
            titleEditText.value = person.title
            emailEditText.value = person.email
            phoneEditText.value = person.phoneNumber
            aboutEditText.value = person.description
        }
    }

    fun clickEdit() {
        val person = Person().apply {
            name = nameEditText.value.toString()
            title = titleEditText.value.toString()
            email = emailEditText.value.toString()
            phoneNumber = phoneEditText.value.toString()
            description = aboutEditText.value.toString()
        }

        personRepo.updatePersonDetail(person)
    }

    private fun loadPersonData() {
        personRepo.loadPersonDetail()
    }
}