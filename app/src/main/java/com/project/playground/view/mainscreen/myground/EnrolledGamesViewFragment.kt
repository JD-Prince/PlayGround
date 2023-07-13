package com.project.playground.view.mainscreen.myground

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.playground.R
import com.project.playground.adapter.SportsListRVAdapter
import com.project.playground.applicationmanager.ViewModelFactory
import com.project.playground.databinding.FragmentEnrolledGamesViewBinding
import com.project.playground.enums.UserViewMode
import com.project.playground.model.SportActivity


class EnrolledGamesViewFragment : Fragment() {

    private var _binding : FragmentEnrolledGamesViewBinding?=null
    private val binding : FragmentEnrolledGamesViewBinding
        get()=_binding!!

    private val viewModel : EnrolledGamesViewModel by viewModels { ViewModelFactory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentEnrolledGamesViewBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEnrolledGameList().observe(viewLifecycleOwner){
            viewModel.player = it.player
            viewModel.gameList = it.activitiesList
            binding.activityList.apply {
                layoutManager=LinearLayoutManager(context)
                if(viewModel.gameList.isNotEmpty()) binding.emptyCaption.text=null
                adapter=SportsListRVAdapter(UserViewMode.ENROLLED).also {
                    adapter->
                    adapter.updateDatabase(viewModel.gameList)
                }
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}