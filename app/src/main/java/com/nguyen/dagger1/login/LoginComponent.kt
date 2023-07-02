package com.nguyen.dagger1.login

import com.nguyen.dagger1.di.ActivityScope
import dagger.Subcomponent

// annotate with ActivityScope since LoginComponent will have the same lifetime as LoginActivity
@ActivityScope
@Subcomponent
interface LoginComponent {
    // Factory to create instances of LoginComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: LoginActivity)
}