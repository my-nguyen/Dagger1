package com.nguyen.dagger1.di

import com.nguyen.dagger1.registration.RegistrationActivity
import dagger.Component

@Component
interface AppComponent {
    // RegistrationActivity requests injection; Dagger has to provide the dependencies annotated
    // with @Inject (RegistrationViewModel) (Dagger has to create an instance of RegistrationViewModel)
    fun inject(activity: RegistrationActivity)
}