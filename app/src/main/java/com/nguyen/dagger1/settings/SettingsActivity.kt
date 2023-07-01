package com.nguyen.dagger1.settings

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nguyen.dagger1.MyApplication
import com.nguyen.dagger1.R
import com.nguyen.dagger1.databinding.ActivitySettingsBinding
import com.nguyen.dagger1.login.LoginActivity

class SettingsActivity : AppCompatActivity(R.layout.activity_settings) {

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userManager = (application as MyApplication).userManager

        settingsViewModel = SettingsViewModel(userManager.userDataRepository!!, userManager)
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
