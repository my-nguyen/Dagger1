package com.nguyen.dagger1.user

import javax.inject.Scope

@Scope
@MustBeDocumented
@Retention(value = AnnotationRetention.RUNTIME)
// LoggedUserScope can cover multiple activities but not all the application
annotation class LoggedUserScope {
}