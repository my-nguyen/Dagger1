package com.nguyen.dagger1.di

import com.nguyen.dagger1.registration.RegistrationComponent
import dagger.Module

// tell AppComponent that RegistrationComponent is its subcomponent
@Module(subcomponents = [RegistrationComponent::class])
class AppSubcomponents