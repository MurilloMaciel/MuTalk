package com.maciel.murillo.mutalk.presentation.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.maciel.murillo.mutalk.core.base.BaseFragment
import com.maciel.murillo.mutalk.core.extensions.newTextWatcher
import com.maciel.murillo.mutalk.core.extensions.showToast
import com.maciel.murillo.mutalk.core.helper.EventObserver
import com.maciel.murillo.mutalk.databinding.FragmentSettingsBinding
import com.maciel.murillo.mutalk.presentation.signup.SignupFragmentDirections
import com.maciel.murillo.mutalk.presentation.signup.SignupViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingsBinding =
        FragmentSettingsBinding::inflate

    private val settingsViewModel: SettingsViewModel by viewModel()

//    private val nameTextWatcher = newTextWatcher { charSequence, _, _, _ -> onChangeName(charSequence.toString()) }

    override fun setUpViews() {

    }

//    override fun setUpObservers() = with(settingsViewModel) {
//        signupError.observe(viewLifecycleOwner, EventObserver { authError ->
//            authError.resource.showToast(context)
//        })
//
//        signupSuccessfull.observe(viewLifecycleOwner, EventObserver {
//            findNavController().navigate(SignupFragmentDirections.goToMain())
//        })
//    }
//
//    override fun setUpListeners() = with(binding) {
//        btSignup.setOnClickListener {
//            signupViewModel.onClickSignup()
//        }
//
//        ivBack.setOnClickListener {
//            findNavController().popBackStack()
//        }
//
//        etName.addTextChangedListener(nameTextWatcher)
//        etEmail.addTextChangedListener(emailTextWatcher)
//        etPassword.addTextChangedListener(passwordTextWatcher)
//        etPasswordConfirm.addTextChangedListener(passwordConfirmTextWatcher)
//    }
}