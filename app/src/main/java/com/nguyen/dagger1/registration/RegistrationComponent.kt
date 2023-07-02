package com.nguyen.dagger1.registration

import com.nguyen.dagger1.di.ActivityScope
import com.nguyen.dagger1.registration.enterdetails.EnterDetailsFragment
import com.nguyen.dagger1.registration.termsandconditions.TermsAndConditionsFragment
import dagger.Subcomponent

// Classes annotated with @ActivityScope will have a unique instance in this Component
@ActivityScope
@Subcomponent
// we want EnterDetailsFragment and TermsAndConditionsFragment to reuse the same RegistrationViewModel
// coming from RegistrationActivity, but if RegistrationActivity changes, we want a different
// RegistrationViewModel.
// However, we cannot use @Singleton because it's already been used by AppComponent.
// So we need to scope RegistrationViewModel to RegistrationActivity.
// A subcomponent inherits and extends the object graph of a parent component. Thus, all objects
// provided in the parent component will be provided in the subcomponent too
// RegistrationActivity is the right lifetime for RegistrationComponent: for every new Activity,
// we'll create a new RegistrationComponent and Fragments that can use that instance of RegistrationComponent
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