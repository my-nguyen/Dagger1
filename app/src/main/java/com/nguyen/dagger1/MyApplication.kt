package com.nguyen.dagger1

import android.app.Application
import com.nguyen.dagger1.storage.SharedPreferencesStorage
import com.nguyen.dagger1.user.UserManager

open class MyApplication : Application() {

    open val userManager by lazy {
        UserManager(SharedPreferencesStorage(this))
    }
}
