package com.nguyen.dagger1.settings

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nguyen.dagger1.MyApplication
import com.nguyen.dagger1.R
import com.nguyen.dagger1.databinding.ActivitySettingsBinding
import com.nguyen.dagger1.login.LoginActivity
import javax.inject.Inject

class SettingsActivity : AppCompatActivity(R.layout.activity_settings) {
    @Inject
    lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val userManager = (application as MyApplication).appComponent.userManager()
        userManager.userComponent!!.inject(this)
        super.onCreate(savedInstanceState)

        val binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews(binding)
    }

    private fun setupViews(binding: ActivitySettingsBinding) {
        binding.refresh.setOnClickListener {
            settingsViewModel.refreshNotifications()
        }
        binding.logout.setOnClickListener {
            settingsViewModel.logout()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}
