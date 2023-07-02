package com.nguyen.dagger1.registration

import com.nguyen.dagger1.registration.enterdetails.EnterDetailsFragment
import com.nguyen.dagger1.registration.termsandconditions.TermsAndConditionsFragment
import dagger.Subcomponent

@Subcomponent
// we don't want to annotate RegistrationViewModel with @Singleton. we want EnterDetailsFragment and
// TermsAndConditionsFragment to reuse the same RegistrationViewModel coming from RegistrationActivity,
// but if RegistrationActivity changes, we want a different RegistrationViewModel.
// So we need to scope RegistrationViewModel to RegistrationActivity.
// A subcomponent inherits and extends the object graph of a parent component. Thus, all objects
// provided in the parent component will be provided in the subcomponent too
interface RegistrationComponent {
    // This Component needs to contain registration specific information. for that, we need to:
    // 1. Create a subcomponent Factory that we can use to create instances of this subcomponent.
    @Subcomponent.Factory
    interface Factory {
        fun create(): RegistrationComponent
    }

    // 2. Add the inject methods from AppComponent that are specific to Registration
    fun inject(activity: RegistrationActivity)
    fun inject(fragment: EnterDetailsFragment)
    fun inject(fragment: TermsAndConditionsFragment)
}