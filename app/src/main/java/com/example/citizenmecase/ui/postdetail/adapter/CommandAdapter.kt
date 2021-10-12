package com.example.citizenmecase.ui.postdetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.citizenmecase.R
import com.example.citizenmecase.api.model.CommentsBaseItem
import kotlinx.android.synthetic.main.comment_item.view.*

class CommandAdapter(): RecyclerView.Adapter<CommandAdapter.MyViewHolder>() {

    private var listData: List<CommentsBaseItem>? = null

    fun setListData(listData: List<CommentsBaseItem>) {
        this.listData = listData
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommandAdapter.MyViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommandAdapter.MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(listData == null )return 0
        return listData?.size!!
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.comment_title
        val tvDesc: TextView = view.comment_body

        fun bind(data: CommentsBaseItem) {
            tvName.text = data.name
            tvDesc.text = data.body

        }
    }
}