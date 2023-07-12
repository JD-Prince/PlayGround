package com.project.playground.view.auth.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.project.playground.applicationmanager.ViewModelFactory
import com.project.playground.databinding.FragmentSignUpBinding
import com.project.playground.view.mainscreen.HomeMainActivity


class SignUpFragment() : Fragment() {


    private val viewModel: SignUpViewModel by viewModels{
        ViewModelFactory
    }

    private var _binding: FragmentSignUpBinding? = null
    private val binding : FragmentSignUpBinding
        get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentSignUpBinding.inflate(inflater,container,false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        viewModel.isExistingUser.observe(viewLifecycleOwner, Observer { alreadyExist->
            if(alreadyExist){
                binding.usernameForSignup.error="UserName Already Exist"
            }

        })
        viewModel.successfulRegistration.observe(viewLifecycleOwner, Observer {isRegistered->
            if(isRegistered){

            startActivity(Intent(requireContext(),HomeMainActivity :: class.java))
                requireActivity().finish()
            }
        })


        binding.signupButton.setOnClickListener{
            var allowance = true
            val userName = binding.usernameForSignup.text.toString().trim()
            val password = binding.passwordForSignup.text.toString().trim()
            val alias = binding.name.text.toString().trim()
            val passwordReEntered = binding.passwordReenterFieldForSignup.text.toString().trim()
            val phonenumber = binding.phoneNumberForSignup.text.toString().trim()
            if(userName.isNullOrBlank() ){
                binding.usernamaeLayout.error="Field Cannot be Blank"
                allowance=false
            }
            val passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#\$%^&+=])(?=.*[a-z])(?=\\S+\$).{8,20}\$".toRegex()
            val phoneNumberRegex = """^(\+?91|0)?[6789]\d{9}$""".toRegex()


            if(!password.matches(passwordRegex) && !password.isNullOrBlank()){
                binding.passwordLayout.error="password Doesn't match the constraints"
                binding.passswordHint.visibility=View.VISIBLE
                allowance=false

            }
            if(alias.isNullOrBlank()){
                binding.nameLayout.error="Field cannot be empty"
                allowance=false
            }
            if(!phonenumber.matches(phoneNumberRegex)){
                binding.phonenumberLayout.error="Invalid Format"
                allowance=false
            }
            if(password.isNullOrBlank()){
                binding.passwordLayout.error="Field Cannot be empty"
                allowance=false
            }
            if(!allowance) return@setOnClickListener
            if(password==passwordReEntered){

                viewModel.registerUser(userName = userName,alias=alias, phoneNumber = phonenumber, password = password,edit=requireActivity().getSharedPreferences("LOGIN_PREF",Context.MODE_PRIVATE).edit())

            }
            else{
                binding.reenteredPasswordLayout.error = "Password mismatch"
            }

        }
        binding.usernameForSignup.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                binding.usernamaeLayout.error=null
            }
        }
        binding.passwordForSignup.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus) binding.passwordLayout.error=null
        }
        binding.passwordReenterFieldForSignup.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus) binding.reenteredPasswordLayout.error=null
        }
        binding.phoneNumberForSignup.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus)binding.phonenumberLayout.error=null
        }
        binding.name.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus)binding.nameLayout.error=null
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


//todo handle saveinstance at orientation change



}