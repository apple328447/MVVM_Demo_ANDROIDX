package com.example.mvvm_demo_androidx.SimplifyRV

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_demo_androidx.R
import kotlinx.android.synthetic.main.content_simplify_items_v2.view.*
import kotlinx.android.synthetic.main.content_simplify_team_title_v2.view.*

class SecondRvAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ItemType { HEAD, BODY }

    var dataList =   mutableListOf<EpsOdds>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position].matchInfo != null) {
            ItemType.HEAD.ordinal
        } else {
            ItemType.BODY.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.HEAD.ordinal -> HeadViewHolder.from(parent)
            ItemType.BODY.ordinal -> BodyViewHolder.from(parent)
            else -> HeadViewHolder.from(parent)
        }
    }

    override fun getItemCount() = dataList.size

    class HeadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(matchInfo: MatchInfo?) {
            itemView.tv_game_title.text = "${matchInfo?.homeName} V ${matchInfo?.awayName}"
            itemView.line.visibility = if (adapterPosition == 0) View.GONE else View.VISIBLE
        }

        companion object {
            fun from(parent: ViewGroup): HeadViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.content_simplify_team_title_v2, parent, false)
                return HeadViewHolder(view)
            }
        }
    }

    class BodyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: EpsOdds) {
            itemView.tv_title.text = "${item.eps?.name}"
            //TODO Bill
            itemView.match_odd.setOnClickListener {

            }
        }

        companion object {
            fun from(parent: ViewGroup): BodyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.content_simplify_items_v2, parent, false)
                return BodyViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HeadViewHolder ->{
                val data = dataList[position].matchInfo
                holder.bind(data)
            }
            is BodyViewHolder ->{
                val data = dataList[position]
                holder.bind(data)
            }
        }
    }

}