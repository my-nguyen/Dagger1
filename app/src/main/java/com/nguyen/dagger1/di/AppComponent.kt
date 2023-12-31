package com.nguyen.dagger1.di

import android.content.Context
import com.nguyen.dagger1.login.LoginComponent
import com.nguyen.dagger1.main.MainActivity
import com.nguyen.dagger1.registration.RegistrationComponent
import com.nguyen.dagger1.settings.SettingsActivity
import com.nguyen.dagger1.user.UserManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
// AppComponent includes StorageModule with information on how to provide Storage instances. Storage
// has a dependency on Context, which is passed in the AppComponent's factory create method.
// so Storage has all its dependencies covered
// AppSubccomponents also needs to be included in the AppComponent, so AppComponent is now aware
// that RegistrationComponent is its subcomponent
@Component(modules = [StorageModule::class, AppSubcomponents::class])
// AppComponent is attached to the lifecycle of the Application because we want to use the same
// instance of the graph as long as the application is in memory
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

    // for the RegistrationActivity to create instances of RegistrationComponent, we need to expose
    // RegistrationComponent factory here
    fun registrationComponent(): RegistrationComponent.Factory
    // also, expose LoginComponent factory
    fun loginComponent(): LoginComponent.Factory

    // Expose UserManager so that MainActivity and SettingsActivity can access a particular instance
    // of UserComponent
    fun userManager(): UserManager
}