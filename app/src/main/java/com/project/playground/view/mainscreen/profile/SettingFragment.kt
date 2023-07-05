package com.project.playground.view.mainscreen.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.playground.R
import com.project.playground.databinding.FragmentSettingBinding
import com.project.playground.enums.ConfrimationStatus
import com.project.playground.view.auth.AuthActivity
import com.project.playground.view.dialogues.ConfrimationDialogueFragment

class SettingFragment(private val authenticationController: AuthenticationController): BottomSheetDialogFragment() {


   private var _binding : FragmentSettingBinding?=null
    private val binding : FragmentSettingBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editProfileTv.setOnClickListener {
            startActivity(Intent(requireContext(),EditProfileActivity::class.java))
            dismiss()
        }
        binding.logoutTv.setOnClickListener {
            ConfrimationDialogueFragment("Are you sure want to Log out ?",object :
                ConfrimationDialogueFragment.ProcessExecutor{
                override fun execute(result: ConfrimationStatus) {
                    authenticationController.logout()
                    startActivity(Intent(requireContext(), AuthActivity :: class.java))
                    requireActivity().finish()
                }

            }).show(childFragmentManager,"LOGOUT_DIALOGUE")


        }
        binding.changePasswordTv.setOnClickListener {
            dismiss()
            ChangePasswordFragment().show(parentFragmentManager,"CHANGE_PASSWORD_FRAGMENT")
        }

    }

    override fun onPause() {
        super.onPause()
        dismiss()
    }
    interface AuthenticationController{
        fun logout()
    }


}