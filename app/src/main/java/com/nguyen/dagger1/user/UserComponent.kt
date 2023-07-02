package com.nguyen.dagger1.user

import com.nguyen.dagger1.main.MainActivity
import com.nguyen.dagger1.settings.SettingsActivity
import dagger.Subcomponent

// scoped to LoggedUserScope so UserComponent can always provide the same instance of UserDataRepository
@LoggedUserScope
// Component that lives as long as the user is logged in. All the Activities (MainActivity and
// SettingsActivity) that can be accessed after the user is logged in will be injected by this component
@Subcomponent
interface UserComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: SettingsActivity)
}