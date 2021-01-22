package com.lwseasy.lwsprofile.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lwseasy.lwsprofile.realm.Person
import io.realm.Realm

class PersonRepo {
    private val realm by lazy { Realm.getDefaultInstance() }

    private val _personData = MutableLiveData<Person?>()
    val getPersonData: LiveData<Person?> get() = _personData

    private val _updateSuccess = MutableLiveData<Boolean>()
    val getUpdateSuccess: LiveData<Boolean> get() = _updateSuccess

    fun loadPersonDetail() {
        val personData = realm.where(Person::class.java).findFirst()
        if (personData != null)
            _personData.value = realm.where(Person::class.java).findFirst()
        else
            addDefaultData()
    }

    fun updatePersonDetail(newData: Person) {
        realm.executeTransactionAsync(
            Realm.Transaction {
                val person = it.where(Person::class.java).findFirst()
                person?.name = newData.name
                person?.title = newData.title
                person?.email = newData.email
                person?.phoneNumber = newData.phoneNumber
                person?.description = newData.description
            }, Realm.Transaction.OnSuccess {
                // Original queries and Realm objects are automatically updated.
                _updateSuccess.value = true
            })
    }

    private fun addDefaultData() {
        realm.executeTransactionAsync({
            val newItem = Person().apply {
                name = "Low Wei Siong"
                title = "Senior Software Engineer"
                email = "wsiong083@gmail.com"
                phoneNumber = "+60163346331"
                description =
                    "Senior Android developer. Good knowledge of mobile platform architecture. Experience to work and discuss with iOS team, Backend team and Design Team in AGILE(JIRA and Confluent). Experience to leading a team to develop a new project, manage the timeline and monitor code quality. Keep update and share newest coding skill"
            }
            it.insert(newItem)

        }, Realm.Transaction.OnSuccess {
            // Original queries and Realm objects are automatically updated.
            loadPersonDetail()
        })
    }
}