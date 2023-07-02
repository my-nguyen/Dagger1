package com.nguyen.dagger1.di

import com.nguyen.dagger1.login.LoginComponent
import com.nguyen.dagger1.registration.RegistrationComponent
import dagger.Module

// tell AppComponent that RegistrationComponent is its subcomponent
// also add LoginComponent to the list
@Module(subcomponents = [RegistrationComponent::class, LoginComponent::class])
class AppSubcomponents