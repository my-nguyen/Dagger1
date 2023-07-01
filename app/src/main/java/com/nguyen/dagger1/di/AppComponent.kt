package com.nguyen.dagger1.di

import android.content.Context
import com.nguyen.dagger1.main.MainActivity
import com.nguyen.dagger1.registration.RegistrationActivity
import dagger.BindsInstance
import dagger.Component

// AppComponent includes StorageModule with information on how to provide Storage instances. Storage
// has a dependency on Context, which is passed in the AppComponent's factory create method.
// so Storage has all its dependencies covered
@Component(modules = [StorageModule::class])
interface AppComponent {
    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // Context is required by SharedPreferencesStorage, in turn required by StorageModule
        // so we need to pass in Context when we create the application graph
        // otherwise we get this error:
        // [Dagger/MissingBinding] android.content.Context cannot be provided without an @Provides-annotated method
        fun create(@BindsInstance context: Context): AppComponent
    }

    // RegistrationActivity requests injection, so Dagger has to provide the dependencies annotated
    // with @Inject, which is RegistrationViewModel
    fun inject(activity: RegistrationActivity)
    // tell Dagger that MainActivity also requests injection
    fun inject(activity: MainActivity)
}