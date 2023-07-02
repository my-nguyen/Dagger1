package com.nguyen.dagger1.di

import dagger.Component
import javax.inject.Singleton

@Singleton
// specify all the modules in this TestAppComponent
@Component(modules = [TestStorageModule::class, AppSubcomponents::class])
// Since production and testing use a different component configuration, we have to create
// a different AppComponent
interface TestAppComponent: AppComponent