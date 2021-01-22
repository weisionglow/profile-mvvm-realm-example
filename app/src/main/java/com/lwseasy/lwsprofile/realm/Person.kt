package com.lwseasy.lwsprofile.realm

import io.realm.RealmObject

open class Person(
    var name: String = "",
    var title: String = "",
    var email: String = "",
    var phoneNumber: String = "",
    var dob: String = "",
    var gender: String = "",
    var description: String = ""
) : RealmObject() {


}