package com.nguyen.dagger1

class MyTestApplication : MyApplication() {
    // Creates a new TestAppComponent that injects fakes types
    override fun initializeComponent() = DaggerTestAppComponent.create()
}
