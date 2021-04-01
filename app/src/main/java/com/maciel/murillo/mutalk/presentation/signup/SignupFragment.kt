package com.maciel.murillo.mutalk.presentation.signup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.maciel.murillo.mutalk.core.base.BaseFragment
import com.maciel.murillo.mutalk.core.extensions.newTextWatcher
import com.maciel.murillo.mutalk.core.extensions.showToast
import com.maciel.murillo.mutalk.core.helper.EventObserver
import com.maciel.murillo.mutalk.databinding.FragmentSignupBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignupFragment : BaseFragment<FragmentSignupBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignupBinding =
        FragmentSignupBinding::inflate

    private val signupViewModel: SignupViewModel by viewModel()

    private val nameTextWatcher = newTextWatcher { charSequence, _, _, _ -> onChangeName(charSequence.toString()) }
    private val emailTextWatcher = newTextWatcher { charSequence, _, _, _ -> onChangeEmail(charSequence.toString()) }
    private val passwordTextWatcher = newTextWatcher { charSequence, _, _, _ -> onChangePassword(charSequence.toString()) }
    private val passwordConfirmTextWatcher = newTextWatcher { charSequence, _, _, _ -> onChangePasswordConfirm(charSequence.toString()) }

    override fun setUpObservers() = with(signupViewModel) {
        signupError.observe(viewLifecycleOwner, EventObserver { authError ->
            authError.resource.showToast(context)
        })

        signupSuccessfull.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(SignupFragmentDirections.goToMain())
        })
    }

    override fun setUpListeners() = with(binding) {
        btSignup.setOnClickListener {
            signupViewModel.onClickSignup()
        }

        ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        etName.addTextChangedListener(nameTextWatcher)
        etEmail.addTextChangedListener(emailTextWatcher)
        etPassword.addTextChangedListener(passwordTextWatcher)
        etPasswordConfirm.addTextChangedListener(passwordConfirmTextWatcher)
    }

    private fun onChangeName(name: String) {
        signupViewModel.onChangeName(name)
    }

    private fun onChangeEmail(email: String) {
        signupViewModel.onChangeEmail(email)
    }

    private fun onChangePassword(password: String) {
        signupViewModel.onChangePassword(password)
    }

    private fun onChangePasswordConfirm(passwordConfirm: String) {
        signupViewModel.onChangePasswordConfirm(passwordConfirm)
    }
}