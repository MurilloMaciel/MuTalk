//package com.maciel.murillo.mutalk.presentation.login
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.navigation.fragment.findNavController
//import com.example.core.base.BaseFragment
//import com.example.core.extensions.newTextWatcher
//import com.example.core.extensions.showToast
//import com.example.core.helper.EventObserver
//import com.maciel.murillo.mutalk.databinding.FragmentLoginBinding
//import org.koin.androidx.viewmodel.ext.android.viewModel
//
//class LoginFragment : BaseFragment<FragmentLoginBinding>() {
//
//    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding =
//        FragmentLoginBinding::inflate
//
//    private val loginViewModel: LoginViewModel by viewModel()
//
//    private val emailTextWatcher = newTextWatcher { charSequence, _, _, _ -> onChangeEmail(charSequence.toString()) }
//    private val passwordTextWatcher = newTextWatcher { charSequence, _, _, _ -> onChangePassword(charSequence.toString()) }
//
//    override fun setUpObservers() = with(loginViewModel) {
//        loginError.observe(viewLifecycleOwner, EventObserver { authError ->
//            authError.resource.showToast(context)
//        })
//
//        loginSuccessfull.observe(viewLifecycleOwner, EventObserver {
//            findNavController().navigate(LoginFragmentDirections.goToMain())
//        })
//
//        signup.observe(viewLifecycleOwner, EventObserver {
//            findNavController().navigate(LoginFragmentDirections.goToSignup())
//        })
//    }
//
//    override fun setUpListeners() = with(binding) {
//        btLogin.setOnClickListener {
//            loginViewModel.onClickLogin()
//        }
//
//        tvSignup.setOnClickListener {
//            loginViewModel.onClickSignup()
//        }
//
//        etEmail.addTextChangedListener(emailTextWatcher)
//        etPassword.addTextChangedListener(passwordTextWatcher)
//    }
//
//    private fun onChangeEmail(email: String) {
//        loginViewModel.onChangeEmail(email)
//    }
//
//    private fun onChangePassword(password: String) {
//        loginViewModel.onChangePassword(password)
//    }
//}