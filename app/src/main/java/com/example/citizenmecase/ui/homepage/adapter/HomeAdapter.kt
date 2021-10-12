package com.example.citizenmecase.ui.homepage.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.citizenmecase.R
import com.example.citizenmecase.api.model.CustomPostModel
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.post_item.view.*

class HomeAdapter(): RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    private var listData: List<CustomPostModel>? = null
    fun setListData(listData: List<CustomPostModel>?) {
        this.listData = listData
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeAdapter.MyViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(listData == null )return 0
        return listData?.size!!
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image_avatar_url:ImageView = view.iv_post_item
        val tvName:TextView = view.tv_post_item_title
        val tvDesc:TextView = view.tv_post_item_detail
        val mainItem:LinearLayout = view.ll_toot_of_item

        fun bind(data: CustomPostModel) {
            tvName.text = data.title
            tvDesc.text = data.body

            mainItem.setOnClickListener{
                val bundle = Bundle()
                bundle.putInt("post_id",data.postId!! )
                bundle.putString("image_url",data.thumbnailUrl!! )
                bundle.putString("title",data.title!! )

                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_postFragment,bundle)
            }

            Glide.with(image_avatar_url.context)
                .load(data.thumbnailUrl +".png")
                .timeout(15000)
                .apply {
                    RequestOptions()
                        .error(R.mipmap.ic_launcher)
                }
                .into(image_avatar_url)
        }
    }
}