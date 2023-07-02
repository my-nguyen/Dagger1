package com.nguyen.dagger1.di

import android.content.Context
import com.nguyen.dagger1.storage.SharedPreferencesStorage
import com.nguyen.dagger1.storage.Storage
import dagger.Binds
import dagger.Module
import dagger.Provides

// Modules tell Dagger how to provide instances of a certain type.
@Module
abstract class StorageModule {
    // Apart from the @Inject and @Binds annotations, you can use @Provides to tell Dagger how to
    // provide an instance of a class inside a Dagger module
    // use the @Provides annotation in Dagger modules to tell Dagger how to provide classes that
    // your project doesn't own (e.g. instances of Retrofit).
    @Provides
    fun provideStorage(context: Context): Storage {
        return SharedPreferencesStorage(context)
    }
}