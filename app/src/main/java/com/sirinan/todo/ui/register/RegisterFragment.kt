package com.sirinan.todo.ui.register

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import com.sirinan.todo.R
import com.sirinan.todo.core.BaseFragment
import com.sirinan.todo.databinding.FragmentRegisterBinding
import com.sirinan.todo.source.model.login.LoginBody
import com.sirinan.todo.source.model.register.RegisterBody
import com.sirinan.todo.ui.todo.TodoActivity
import com.sirinan.todo.utils.*

class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>(
    RegisterViewModel::class,
    FragmentRegisterBinding::inflate
) {

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment().withArguments {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        checkLogin()
    }

    override fun onSubscribe() {
        super.onSubscribe()
        observe(viewModel.statusSuccess, ::openMainPage)
    }

    private fun setupView() {
        binding.run {
            buttonLogin.setOnClickListener(::onClickListener)
            textLogin.setOnClickListener(::onClickListener)

            editEmail.setText("android3@gmail.com")
            editPassword.setText("12345678")
        }
    }

    private fun checkLogin() {
        if(PreferencesUtils.instant.token.isNotEmpty()){
            launchActivity<TodoActivity>()
            activity?.finish()
        }else{
            binding.layoutProgress.gone()
        }


    }

    private fun getInputRegister() {
        binding.run {
            RegisterBody(
                editName.textOrEmpty,
                editEmail.textOrEmpty,
                editPassword.textOrEmpty,
                editAge.intDefault
            ).also {
                viewModel.validateInput(it)
            }
        }
    }

    private fun getInputLogin() {
        binding.run {

            LoginBody(
                editEmail.textOrEmpty,
                editPassword.textOrEmpty
            ).also {
                viewModel.validateInput(it)
            }
        }
    }

    private fun showLogin() {
        binding.run {
            buttonLogin.text = "Login"
            textTitle.text = "Login"
            editName.gone()
            editAge.gone()
            textLogin.gone()
        }
    }

    private fun openMainPage() {
        launchActivity<TodoActivity>()
        activity?.finish()
    }

    private fun onClickListener(view: View) {
        when (view.id) {
            R.id.buttonLogin -> if (binding.textLogin.isGone) getInputLogin() else getInputRegister()
            R.id.textLogin -> showLogin()
        }
    }
}