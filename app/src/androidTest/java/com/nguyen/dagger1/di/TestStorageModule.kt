package com.nguyen.dagger1.di

import com.nguyen.dagger1.storage.Storage
import dagger.Binds
import dagger.Module

@Module
abstract class TestStorageModule {
    @Binds
    // Makes Dagger provide FakeStorage when a Storage type is requested
    abstract fun provideStorage(storage: FakeStorage): Storage
}