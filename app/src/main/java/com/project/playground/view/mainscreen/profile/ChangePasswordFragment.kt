package com.project.playground.view.mainscreen.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.playground.R
import com.project.playground.applicationmanager.ViewModelFactory
import com.project.playground.databinding.FragmentChangePasswordBinding
import com.project.playground.enums.PasswordCorrectionStatus

class ChangePasswordFragment : BottomSheetDialogFragment() {

    private var _binding  : FragmentChangePasswordBinding? = null
    private val binding : FragmentChangePasswordBinding
        get()=_binding!!

    private val viewModel : PasswordHandlerViewModel by viewModels {
        ViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentChangePasswordBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.result.observe(viewLifecycleOwner){status->
            when(status){
                PasswordCorrectionStatus.INCORRECT_PASSWORD ->{
                    binding.OldPasswordLayout.error="Incorrect Password"
                }
                PasswordCorrectionStatus.PASSWORD_MISMATCH -> {
                    binding.reenerednewpasswordLayout.error="Password mismatch"
                }
                PasswordCorrectionStatus.FIELD_CANNOT_BE_EMPTY ->{
                    binding.apply {
                        if(OldPasswordLayout.editText?.text.toString().trim().isNullOrEmpty()) OldPasswordLayout.error="Field cannot be empty"
                        if(newPasswordLayout.editText?.text.toString().trim().isNullOrEmpty()) newPasswordLayout.error="Field cannot be empty"
                        if(reenerednewpasswordLayout.editText?.text.toString().trim().isNullOrEmpty()) reenerednewpasswordLayout.error="Field cannot be empty"
                    }
                }
                PasswordCorrectionStatus.REGEX_MISMATCH -> {
                    binding.newPasswordLayout.error="Invalid"
                    binding.passswordHint.setTextColor(ContextCompat.getColor(requireContext(),R.color.red))
                }
                PasswordCorrectionStatus.SUCCESSFUL ->{
                    Toast.makeText(requireContext(),"Password updated successfully",Toast.LENGTH_SHORT).show()
                    dismiss()
                }
            }

        }

        viewModel.loadUserData().observe(viewLifecycleOwner) { player ->

            viewModel.currentUser=player
            binding.OldPasswordLayout.editText?.setOnClickListener {
                binding.OldPasswordLayout.error = null
            }

            binding.newPasswordLayout.editText?.setOnClickListener {
                binding.newPasswordLayout.error = null
            }

            binding.reenerednewpasswordLayout.editText?.setOnClickListener {
                binding.newPasswordLayout.error = null
            }

            binding.updateBtn.setOnClickListener {
                binding.OldPasswordLayout.error=null
                binding.newPasswordLayout.error=null
                binding.reenerednewpasswordLayout.error=null
                binding.passswordHint.setTextColor(ContextCompat.getColor(requireContext(),R.color.black_overlay))
                val currentPassword = binding.OldPasswordLayout.editText?.text.toString().trim()
                val newPassword = binding.newPasswordLayout.editText?.text.toString().trim()
                val reEnteredPassword = binding.reenerednewpasswordLayout.editText?.text.toString().trim()
                viewModel.checkData(currentPassword,newPassword,reEnteredPassword)

            }
        }

    }


}