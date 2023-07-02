package com.nguyen.dagger1.di

import com.nguyen.dagger1.login.LoginComponent
import com.nguyen.dagger1.registration.RegistrationComponent
import com.nguyen.dagger1.user.UserComponent
import dagger.Module

// tell AppComponent that RegistrationComponent is its subcomponent
// also add LoginComponent and UserComponent to the list
@Module(subcomponents = [RegistrationComponent::class, LoginComponent::class, UserComponent::class])
class AppSubcomponents