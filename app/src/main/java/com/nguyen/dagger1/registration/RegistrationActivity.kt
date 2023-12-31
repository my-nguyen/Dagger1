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
    // Since RegistrationComponent is attached to the RegistrationActivity lifecycle, we need
    // a reference to the component
    // this variable is not annotated with @Inject because we don't expect it to be provided by Dagger
    lateinit var registrationComponent: RegistrationComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        // Creates an instance of Registration component by grabbing the factory from the app graph
        registrationComponent = (application as MyApplication).appComponent.registrationComponent().create()
        // Injects this activity to the registration component
        registrationComponent.inject(this)
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
