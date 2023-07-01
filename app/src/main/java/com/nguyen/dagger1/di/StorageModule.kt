package com.nguyen.dagger1.di

import com.nguyen.dagger1.storage.SharedPreferencesStorage
import com.nguyen.dagger1.storage.Storage
import dagger.Binds
import dagger.Module

// Modules tell Dagger how to provide instances of a certain type.
@Module
abstract class StorageModule {
    // because Storage is an interface, we need to tell Dagger what implementation of Storage to use
    // @Binds tells Dagger to use SharedPreferencesStorage as implementation for Storage
    // SharedPreferencesStorage requires Context which is provided by @BindInstance in AppComponent
    // factory create method
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
}