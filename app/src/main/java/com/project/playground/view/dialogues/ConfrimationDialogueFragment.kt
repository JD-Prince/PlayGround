package com.project.playground.view.dialogues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.project.playground.databinding.FragmentDialogueBinding
import com.project.playground.enums.ConfrimationStatus

class ConfrimationDialogueFragment(private val content : String,private val executor : ProcessExecutor) : DialogFragment() {

    var _binding : FragmentDialogueBinding?=null
    val binding : FragmentDialogueBinding
        get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDialogueBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        binding.content.text=content
        binding.acceptBtn.setOnClickListener {
            executor.execute(ConfrimationStatus.YES)
        }
        binding.cancelBtn.setOnClickListener {
            dismiss()
        }

    }

    interface ProcessExecutor{
        fun execute(result : ConfrimationStatus)
    }

}