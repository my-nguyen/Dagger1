package com.nguyen.dagger1

import com.nguyen.dagger1.di.AppComponent
import com.nguyen.dagger1.di.DaggerTestAppComponent

class MyTestApplication : MyApplication() {
    // Creates a new TestAppComponent that injects fakes types
    override fun initializeComponent(): AppComponent = DaggerTestAppComponent.create()
}
