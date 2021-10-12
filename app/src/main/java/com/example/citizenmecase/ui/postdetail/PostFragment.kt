package com.example.citizenmecase.ui.postdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.citizenmecase.R
import com.example.citizenmecase.databinding.FragmentPostBinding
import com.example.citizenmecase.datasource.Status
import com.example.citizenmecase.ui.homepage.adapter.HomeAdapter
import com.example.citizenmecase.ui.postdetail.adapter.CommandAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class PostFragment : Fragment() {

    private val postViewModel:PostViewModel by viewModels()
    private lateinit var recyclerViewAdapter: CommandAdapter
    private lateinit var recyclerView: RecyclerView


    private lateinit var binding: FragmentPostBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_post, container, false
        )

        recyclerView = binding.recyclerView
        recyclerViewAdapter = CommandAdapter()
        recyclerView.adapter = recyclerViewAdapter
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val post_id:Int? = arguments?.getInt("post_id")
        val image_url: String? = arguments?.getString("image_url")
        val title:String? = arguments?.getString("title")

        Glide.with(binding.ivPost.context)
            .load(image_url +".png")
            .timeout(15000)
            .apply {
                RequestOptions()
                    .error(R.mipmap.ic_launcher)
            }
            .into(binding.ivPost)
        binding.tvPostTitle.text = title
        postViewModel.getCommands(post_id!!).observe(viewLifecycleOwner,{
            when(it.status){
                Status.SUCCESS -> {
                    Timber.d("Post Response ->  $it")

                    recyclerViewAdapter.setListData(it.data!!)
                    recyclerViewAdapter.notifyDataSetChanged()
                }
            }
        })













    }
}