package com.example.citizenmecase.ui.homepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.citizenmecase.R
import com.example.citizenmecase.databinding.FragmentHomeBinding
import com.example.citizenmecase.datasource.Status
import com.example.citizenmecase.ui.homepage.adapter.HomeAdapter
import com.github.ajalt.timberkt.Timber
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var recyclerViewAdapter: HomeAdapter
    private lateinit var recyclerView: RecyclerView


    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        recyclerView = binding.recyclerView
        recyclerViewAdapter = HomeAdapter()
        homeViewModel.getLocalCustomPost().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    recyclerView.adapter = recyclerViewAdapter
                    recyclerViewAdapter.setListData(it.data)
                    recyclerViewAdapter.notifyDataSetChanged()
                }

            }

        })
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.d("TAG", "onViewCreated: ")
        homeViewModel.getPosts().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {

                    it?.data?.let { it1 ->
                        homeViewModel.getPhotos(it1).observe(viewLifecycleOwner) {
                            when (it.status) {
                                Status.SUCCESS -> {
                                    recyclerView.adapter =recyclerViewAdapter
                                    recyclerViewAdapter.setListData(it.data)
                                    recyclerViewAdapter.notifyDataSetChanged()
                                }

                        }


                        }
                    }
                }
            }
        })



    }
}