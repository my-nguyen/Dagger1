package com.nguyen.dagger1.di

import javax.inject.Scope

// To scope RegistrationViewModel to RegistrationComponent, we have to annotate both the class and
// the interface with @ActivityScope
@Scope
@MustBeDocumented
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope