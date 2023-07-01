package com.nguyen.dagger1.di

import com.nguyen.dagger1.registration.RegistrationActivity
import dagger.Component

// if we build the app now, we'll get this error:
// [Dagger/MissingBinding] com.nguyen.dagger1.storage.Storage cannot be provided without an @Provides-annotated method.
// that's because we haven't told Dagger how to provide an object of type Storage which is needed by UserManager!
@Component
interface AppComponent {
    // RegistrationActivity requests injection; Dagger has to provide the dependencies annotated
    // with @Inject (RegistrationViewModel) (Dagger has to create an instance of RegistrationViewModel)
    fun inject(activity: RegistrationActivity)
}