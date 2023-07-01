package com.nguyen.dagger1.registration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nguyen.dagger1.MyApplication
import com.nguyen.dagger1.R
import com.nguyen.dagger1.databinding.ActivityRegistrationBinding
import com.nguyen.dagger1.main.MainActivity
import com.nguyen.dagger1.registration.enterdetails.EnterDetailsFragment
import com.nguyen.dagger1.registration.termsandconditions.TermsAndConditionsFragment
import javax.inject.Inject

class RegistrationActivity : AppCompatActivity(R.layout.activity_registration) {
    // @Inject annotated fields will be provided by Dagger
    @Inject
    lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // Ask Dagger to inject our dependencies.
        // this populates the fields that RegistrationActivity has annotated with @Inject (i.e. registrationViewModel).
        // When using Activities, inject Dagger in the Activity's onCreate method before calling
        // super.onCreate to avoid issues with fragment restoration
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        val binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_holder, EnterDetailsFragment())
            .commit()
    }

    /**
     * Callback from EnterDetailsFragment when username and password has been entered
     */
    fun onDetailsEntered() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_holder, TermsAndConditionsFragment())
            .addToBackStack(TermsAndConditionsFragment::class.java.simpleName)
            .commit()
    }

    /**
     * Callback from T&CsFragment when TCs have been accepted
     */
    fun onTermsAndConditionsAccepted() {
        registrationViewModel.registerUser()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
