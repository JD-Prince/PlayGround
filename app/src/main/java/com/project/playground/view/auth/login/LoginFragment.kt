package com.project.playground.view.auth.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.project.playground.applicationmanager.ViewModelFactory
import com.project.playground.databinding.FragmentLoginBinding
import com.project.playground.enums.AuthenticationStatements
import com.project.playground.view.mainscreen.HomeMainActivity


class LoginFragment : Fragment() {



    private var _binding : FragmentLoginBinding? = null
    private val binding : FragmentLoginBinding
        get()=_binding!!
    private val viewModel : LoginViewModel by viewModels {
        ViewModelFactory
    }



    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.usernameForLogin.setText(savedInstanceState?.getString("USERNAME",""))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater,container,false)


        return binding.root
    }








    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences=requireActivity().getSharedPreferences("LOGIN_PREF",Context.MODE_PRIVATE)

        viewModel.username?.let {
            binding.usernameForLogin.setText(it)
        }


//        viewModel= ViewModelProvider(this,ViewModelFactory)[LoginViewModel :: class.java]


        viewModel.isEmptyField.observe(viewLifecycleOwner, Observer {FieldisEmpty ->
            if(FieldisEmpty){
                Toast.makeText(requireContext(),"Please Fill all the Fields",Toast.LENGTH_SHORT).show()
                if(binding.usernameForLogin.text.isNullOrEmpty()) binding.usernamaeLayout.error="Field cannot be empty"

                if(binding.passwordForLogin.text.isNullOrEmpty()) binding.passwordLayout.error="Field cannot be empty"
            }
        })

        viewModel.userNotFound.observe(viewLifecycleOwner, Observer { isInvalidUser ->
            if(isInvalidUser){
                binding.usernamaeLayout.error=AuthenticationStatements.USER_NOT_FOUND.toString()
            }

        })

        viewModel.isValidUser.observe(viewLifecycleOwner, Observer { isValid ->
            if(isValid){
                startActivity(Intent(this.context,HomeMainActivity :: class.java))
                requireActivity().finish()


            }else{
                binding.passwordLayout.error="Incorrect Password"

            }


        })

        binding.loginButton.setOnClickListener {
            val username = binding.usernameForLogin.text.toString().trim()
            val password = binding.passwordForLogin.text.toString().trim()
            viewModel.login(username, password,sharedPreferences.edit())

        }
        binding.usernameForLogin.setOnClickListener {
            binding.usernamaeLayout.error=null
        }
        binding.passwordForLogin.setOnClickListener { binding.passwordLayout.error=null
        }



    }

    override fun onPause() {
        super.onPause()
        viewModel.username=binding.usernameForLogin.text.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putCharSequence("USERNAME",binding.usernameForLogin.text.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }




}