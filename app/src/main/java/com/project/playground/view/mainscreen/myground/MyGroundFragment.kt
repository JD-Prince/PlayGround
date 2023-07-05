package com.project.playground.view.mainscreen.myground

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.project.playground.adapter.HomeFragmentAdapter
import com.project.playground.databinding.FragmentMyGroundBinding


class MyGroundFragment : Fragment() {

    private var _binding : FragmentMyGroundBinding?=null
    private val binding : FragmentMyGroundBinding
        get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentMyGroundBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tablayout.addTab(binding.tablayout.newTab())
        binding.tablayout.addTab(binding.tablayout.newTab())

        binding.myGroundViewPager.apply {
            adapter= HomeFragmentAdapter(childFragmentManager,lifecycle,binding.tablayout.tabCount,
                MyActivitiesFragment(), EnrolledGamesViewFragment()
            )
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tablayout.selectTab(binding.tablayout.getTabAt(position))

                }
            })

        }
        TabLayoutMediator(binding.tablayout, binding.myGroundViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "My Games"
                1 -> tab.text = "Enrolled Games"
            }
        }.attach()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.myGroundViewPager.adapter=null
        _binding=null
    }


}