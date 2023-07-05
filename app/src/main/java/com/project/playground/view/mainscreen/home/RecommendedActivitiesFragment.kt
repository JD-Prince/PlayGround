package com.project.playground.view.mainscreen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.playground.adapter.SportsListRVAdapter
import com.project.playground.applicationmanager.ViewModelFactory
import com.project.playground.databinding.FragmentRecommendedActivitiesBinding
import com.project.playground.enums.UserViewMode


class RecommendedActivitiesFragment : Fragment() {

    private var _binding : FragmentRecommendedActivitiesBinding?=null
    private val binding
        get()=_binding!!

    private lateinit var viewModel: RecommendedActivitiesViewModel

    private lateinit var recyclerviewAdapter : SportsListRVAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentRecommendedActivitiesBinding.inflate(inflater,container,false)
        recyclerviewAdapter= SportsListRVAdapter(UserViewMode.RECOMMENDED)
        viewModel=ViewModelProvider(this,ViewModelFactory)[RecommendedActivitiesViewModel::class.java]
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.recommednedActivities.observe(viewLifecycleOwner
        ) { list ->
            if (list.isNotEmpty()) {
                binding.emptyfieldText.text = ""
                recyclerviewAdapter.updateDatabase(list)

            }
        }
        binding.activityList.apply {
            this.layoutManager=LinearLayoutManager(requireContext())
            adapter=recyclerviewAdapter

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}