package com.nguyen.dagger1.main

import com.nguyen.dagger1.user.UserDataRepository
import javax.inject.Inject

/**
 * MainViewModel is the ViewModel that [MainActivity] uses to
 * obtain information of what to show on the screen.
 */
class MainViewModel @Inject constructor(private val userDataRepository: UserDataRepository) {

    val welcomeText: String
        get() = "Hello ${userDataRepository.username}!"

    val notificationsText: String
        get() = "You have ${userDataRepository.unreadNotifications} unread notifications"
}
