package com.nguyen.dagger1.user

import com.nguyen.dagger1.storage.Storage
import javax.inject.Inject
import javax.inject.Singleton

private const val REGISTERED_USER = "registered_user"
private const val PASSWORD_SUFFIX = "password"

/**
 * Handles User lifecycle. Manages registrations, logs in and logs out.
 * Knows when the user is logged in.
 */
// use @Singleton scope, so that the same instance of UserManager will be provided to
// RegistrationActivity and MainActivity. without this @Singleton scoping, Dagger will provide
// a different instance of UserManager for MainActivity and RegistrationActivity
@Singleton
// attach the lifetime of UserComponent to UserManager, since UserManager handles registrations,
// log in and log out attempts
class UserManager @Inject constructor(private val storage: Storage,
                                      // UserManager needs to know how to create instances of it
                                      private val userComponentFactory: UserComponent.Factory) {
    // use UserComponent instead of UserDataRepository
    var userComponent: UserComponent? = null
        private set

    val username: String
        get() = storage.getString(REGISTERED_USER)

    fun isUserLoggedIn() = userComponent != null

    fun isUserRegistered() = storage.getString(REGISTERED_USER).isNotEmpty()

    fun registerUser(username: String, password: String) {
        storage.setString(REGISTERED_USER, username)
        storage.setString("$username$PASSWORD_SUFFIX", password)
        userJustLoggedIn()
    }

    fun loginUser(username: String, password: String): Boolean {
        val registeredUser = this.username
        if (registeredUser != username) return false

        val registeredPassword = storage.getString("$username$PASSWORD_SUFFIX")
        if (registeredPassword != password) return false

        userJustLoggedIn()
        return true
    }

    fun logout() {
        userComponent = null
    }

    fun unregister() {
        val username = storage.getString(REGISTERED_USER)
        storage.setString(REGISTERED_USER, "")
        storage.setString("$username$PASSWORD_SUFFIX", "")
        logout()
    }

    private fun userJustLoggedIn() {
        userComponent = userComponentFactory.create()
    }
}
