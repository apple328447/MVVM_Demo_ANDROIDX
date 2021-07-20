package com.example.mvvm_demo_androidx.SimplifyRV

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_demo_androidx.R
import com.example.mvvm_demo_androidx.utils.TimeUtils
import kotlinx.android.synthetic.main.content_simplify_league_rv.view.*
import kotlinx.android.synthetic.main.content_simplify_rv_date_line.view.*

class FirstRvAdapter(val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ItemType { HEAD, BODY }

    var dataList =  mutableListOf<LeagueOddsItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return when (dataList[position].date.toInt()) {
            0 -> ItemType.BODY.ordinal
            else -> ItemType.HEAD.ordinal
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
        fun bind(item: LeagueOddsItem) {
            if(adapterPosition !=0 ){
                val params = itemView.ll_content.layoutParams as LinearLayout.LayoutParams
                params.setMargins(0,14,0,0)
                itemView.ll_content.layoutParams = params
            }

          itemView.tv_date.text =  "${itemView.context.getString(TimeUtils().setupDayOfWeek(item.date))}"// 也有context.getString(R.String.xxx) 但不知道為什麼一職失敗

            //Resources.getSystem().getString(TimeUtils().setupDayOfWeek(item.date))
        }

        companion object {
            fun from(parent: ViewGroup): HeadViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.content_simplify_rv_date_line, parent, false)
                return HeadViewHolder(view)
            }
        }
    }

    class BodyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: LeagueOddsItem) {
            itemView.tv_league_title.text = "${item.league?.name}"

            itemView.rv_second.layoutManager = LinearLayoutManager(
                itemView.context,
                LinearLayoutManager.VERTICAL, false
            )
            val secondRvAdapter = SecondRvAdapter()
            itemView.rv_second.adapter = secondRvAdapter
            secondRvAdapter.dataList = filterSecondRvData(item.matchOdds)
        }

        companion object {
            fun from(parent: ViewGroup): BodyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.content_simplify_league_rv, parent, false)
                return BodyViewHolder(view)
            }
        }

        private fun filterSecondRvData(matchOddsItem: List<MatchOddsItem>?): MutableList<EpsOdds> {
            val newListV2 = mutableListOf<EpsOdds>()
            matchOddsItem?.forEach {
                newListV2.add(EpsOdds(matchInfo = it.matchInfo, eps = null))
                it.odds.eps?.forEach { EPSItem ->
                    newListV2.add(EpsOdds(matchInfo = null, eps = EPSItem))
                }
            }
            return newListV2
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeadViewHolder -> {
                val data = dataList[position]
                holder.bind(data)
            }
            is BodyViewHolder -> {
                val data = dataList[position]
                holder.bind(data)
            }
        }
    }
}