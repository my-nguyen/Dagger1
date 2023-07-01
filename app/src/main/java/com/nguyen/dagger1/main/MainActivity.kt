package com.nguyen.dagger1.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nguyen.dagger1.MyApplication
import com.nguyen.dagger1.databinding.ActivityMainBinding
import com.nguyen.dagger1.login.LoginActivity
import com.nguyen.dagger1.registration.RegistrationActivity
import com.nguyen.dagger1.settings.SettingsActivity
import com.nguyen.dagger1.user.UserManager
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mainViewModel: MainViewModel
    @Inject
    lateinit var userManager: UserManager

    private lateinit var binding: ActivityMainBinding

    /**
     * If the User is not registered, RegistrationActivity will be launched,
     * If the User is not logged in, LoginActivity will be launched,
     * else carry on with MainActivity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        if (!userManager.isUserLoggedIn()) {
            if (!userManager.isUserRegistered()) {
                startActivity(Intent(this, RegistrationActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        } else {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            setupViews()
        }
    }

    /**
     * Updating unread notifications onResume because they can get updated on SettingsActivity
     */
    override fun onResume() {
        super.onResume()
        binding.notifications.text = mainViewModel.notificationsText
    }

    private fun setupViews() {
        binding.hello.text = mainViewModel.welcomeText
        binding.settings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
