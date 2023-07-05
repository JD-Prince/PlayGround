package com.project.playground.view.mainscreen.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.playground.R
import com.project.playground.adapter.RequestListAdapter
import com.project.playground.applicationmanager.ViewModelFactory
import com.project.playground.databinding.FragmentRequestsListBinding
import com.project.playground.model.Notification
import com.project.playground.model.SportActivity


class RequestsListFragment : Fragment() , RequestHandler{
    private var _binding : FragmentRequestsListBinding?=null
    private val binding : FragmentRequestsListBinding
        get()=_binding!!
    private val viewModel : RequestHandlerViewModel by viewModels {

        ViewModelFactory

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentRequestsListBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRequestData().observe(viewLifecycleOwner) { requestList ->
            binding.emptyCaption.text="The Request list is currently empty".takeIf { requestList.isEmpty() }
            binding.list.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = RequestListAdapter(requestList, this@RequestsListFragment)
            }


        }

    }

    override fun approveRequest(request: Notification.Request,event: SportActivity) {
        viewModel.approveRequest(request,event)
    }

    override fun showProfile(playerId: Int) {
    //todo
    }

    override fun declineRequest(request: Notification.Request,eventTitle:String) {
        viewModel.declineRequest(request,eventTitle)
    }


}