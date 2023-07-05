package com.project.playground.view.mainscreen.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.playground.R
import com.project.playground.adapter.NoticeListAdapter
import com.project.playground.applicationmanager.ViewModelFactory
import com.project.playground.databinding.FragmentNoticeBinding


class NoticeFragment : Fragment() {

    private var _binding : FragmentNoticeBinding?=null
    private val binding : FragmentNoticeBinding
        get()=_binding!!

    private val viewModel : NoticeListViewModel by viewModels{
        ViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentNoticeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllNotice().observe(viewLifecycleOwner){
            list->

            if(list.isNotEmpty()) binding.emptyCaption.text=null
            binding.noticeList.apply {
                layoutManager=LinearLayoutManager(context)
                adapter=NoticeListAdapter(list)
            }
        }


    }

    override fun onDestroyView() {
        _binding=null
        super.onDestroyView()
    }



}