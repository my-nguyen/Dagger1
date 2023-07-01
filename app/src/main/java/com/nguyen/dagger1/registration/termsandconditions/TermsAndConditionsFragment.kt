package com.nguyen.dagger1.registration.termsandconditions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nguyen.dagger1.R
import com.nguyen.dagger1.databinding.FragmentTermsAndConditionsBinding
import com.nguyen.dagger1.registration.RegistrationActivity
import com.nguyen.dagger1.registration.RegistrationViewModel

class TermsAndConditionsFragment : Fragment(R.layout.fragment_terms_and_conditions) {

    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentTermsAndConditionsBinding.bind(view)
        registrationViewModel = (activity as RegistrationActivity).registrationViewModel

        binding.next.setOnClickListener {
            registrationViewModel.acceptTCs()
            (activity as RegistrationActivity).onTermsAndConditionsAccepted()
        }
    }
}
