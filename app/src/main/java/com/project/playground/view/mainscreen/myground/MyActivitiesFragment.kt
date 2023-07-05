package com.project.playground.view.mainscreen.myground

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.playground.adapter.ActivityListAdapter
import com.project.playground.adapter.SportsListRVAdapter
import com.project.playground.applicationmanager.ViewModelFactory
import com.project.playground.databinding.FragmentMyActivitiesBinding
import com.project.playground.enums.UserViewMode
import com.project.playground.view.mainscreen.activityhandler.NewSportActivityCreaterActivity


class MyActivitiesFragment : Fragment() {
    private var _binding: FragmentMyActivitiesBinding?=null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: MyActivitiesViewModel
    private lateinit var recyclerviewAdapter : SportsListRVAdapter

//    private val dataUpdateReceiver : BroadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
////            mParentFragmentManager.beginTransaction().attach(this@MyActivitiesFragment).commit()
//            reFreshData()
//        }

//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        Log.d("MyCheck","on Create")
        _binding= FragmentMyActivitiesBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(this,ViewModelFactory)[MyActivitiesViewModel :: class.java]
//        repository= ActivityRepository(DatabaseManager.getDatabase(this.requireContext()).activityDao())


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerviewAdapter= SportsListRVAdapter(UserViewMode.HOST)
        Log.d("MyCheck","on oVC")
//        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(dataUpdateReceiver,
//            IntentFilter("ACTION_NEW_ACTIVITY_ADDED")
//        )


        binding.newActivityCreaterButton.setOnClickListener {

            startActivity(Intent(requireContext(), NewSportActivityCreaterActivity::class.java))

        }

        binding.activityList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recyclerviewAdapter
        }
        reFreshData()



    }

    override fun onResume() {
        super.onResume()
        Log.d("MyCheck","on resume")
    }


    private fun reFreshData() {


        viewModel.myActivitiesList.observe(viewLifecycleOwner,
        Observer {list->

            binding.emptyfieldText.text = "You haven't created any Activities".takeIf {  list.isEmpty()}
            recyclerviewAdapter.updateDatabase(list)


        })
    }


    override fun onPause() {
        super.onPause()
        Log.d("MyCheck","on pause")
//        parentFragmentManager.beginTransaction().detach(this).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MyCheck","on destroy")
        _binding=null

//        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(dataUpdateReceiver)
    }
}


//TODO -> need to change all the connections