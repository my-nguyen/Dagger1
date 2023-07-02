package com.nguyen.dagger1

import android.app.Application
import com.nguyen.dagger1.di.AppComponent
import com.nguyen.dagger1.di.DaggerAppComponent

open class MyApplication : Application() {
    // an AppComponent that will be used by all the Activities
    val appComponent: AppComponent by lazy {
        // Create an AppComponent using its Factory constructor, with applicationContext as Context
        DaggerAppComponent.factory().create(applicationContext)
    }
}
