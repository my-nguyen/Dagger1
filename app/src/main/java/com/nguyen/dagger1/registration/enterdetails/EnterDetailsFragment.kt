package com.nguyen.dagger1.registration.enterdetails

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.nguyen.dagger1.R
import com.nguyen.dagger1.databinding.FragmentEnterDetailsBinding
import com.nguyen.dagger1.registration.RegistrationActivity
import com.nguyen.dagger1.registration.RegistrationViewModel

class EnterDetailsFragment : Fragment(R.layout.fragment_enter_details) {

    /**
     * RegistrationViewModel is used to set the username and password information (attached to
     * Activity's lifecycle and shared between different fragments)
     * EnterDetailsViewModel is used to validate the user input (attached to this
     * Fragment's lifecycle)
     *
     * They could get combined but for the sake of the codelab, we're separating them so we have
     * different ViewModels with different lifecycles.
     */
    private lateinit var registrationViewModel: RegistrationViewModel
    private lateinit var enterDetailsViewModel: EnterDetailsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEnterDetailsBinding.bind(view)

        registrationViewModel = (activity as RegistrationActivity).registrationViewModel

        enterDetailsViewModel = EnterDetailsViewModel()
        enterDetailsViewModel.enterDetailsState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is EnterDetailsSuccess -> {
                    val username = binding.username.text.toString()
                    val password = binding.password.text.toString()
                    registrationViewModel.updateUserData(username, password)

                    (activity as RegistrationActivity).onDetailsEntered()
                }

                is EnterDetailsError -> {
                    binding.error.text = state.error
                    binding.error.visibility = View.VISIBLE
                }
            }
        }

        setupViews(binding)
    }

    private fun setupViews(binding: FragmentEnterDetailsBinding) {
        binding.username.doOnTextChanged { _, _, _, _ -> binding.error.visibility = View.INVISIBLE }

        binding.password.doOnTextChanged { _, _, _, _ -> binding.error.visibility = View.INVISIBLE }

        binding.next.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            enterDetailsViewModel.validateInput(username, password)
        }
    }
}

sealed class EnterDetailsViewState
object EnterDetailsSuccess : EnterDetailsViewState()
data class EnterDetailsError(val error: String) : EnterDetailsViewState()
