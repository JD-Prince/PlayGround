package com.project.playground.view.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.playground.R
import com.project.playground.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : BottomSheetDialogFragment() {

    private var _binding : FragmentForgotPasswordBinding?=null
    private val binding : FragmentForgotPasswordBinding
        get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForgotPasswordBinding.inflate(inflater,container,false)
        return binding.root
    }


}