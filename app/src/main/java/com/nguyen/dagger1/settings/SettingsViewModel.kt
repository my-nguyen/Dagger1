package com.nguyen.dagger1.settings

import com.nguyen.dagger1.user.UserDataRepository
import com.nguyen.dagger1.user.UserManager
import javax.inject.Inject

/**
 * SettingsViewModel is the ViewModel that [SettingsActivity] uses to handle complex logic.
 */
class SettingsViewModel @Inject constructor(private val userDataRepository: UserDataRepository,
                                            private val userManager: UserManager) {

    fun refreshNotifications() {
        userDataRepository.refreshUnreadNotifications()
    }

    fun logout() {
        userManager.logout()
    }
}
