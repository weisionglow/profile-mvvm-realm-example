package com.lwseasy.lwsprofile

import android.app.Application
import io.realm.Realm

class ProfileApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}