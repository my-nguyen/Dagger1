package com.nguyen.dagger1.di

import android.content.Context
import com.nguyen.dagger1.main.MainActivity
import com.nguyen.dagger1.registration.RegistrationComponent
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

    // the inject methods for registration view classes (RegistrationActivity, EnterDetailsFragment,
    // and TermsAndConditionsFragment) have been removed because they won't be used anymore. Those
    // classes will use the RegistrationComponent
    fun inject(activity: MainActivity)

    // Instead, for the RegistrationActivity to create instances of RegistrationComponent, we need
    // to expose RegistrationComponent factory here in AppComponent
    fun registrationComponent(): RegistrationComponent.Factory
}