package com.example.mvvm_demo_androidx.RecyclerViewDemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_demo_androidx.R
import com.example.mvvm_demo_androidx.database.ListData
import kotlinx.android.synthetic.main.content_mood_list.view.*

class MoodListAdapter(private val clickListener: ItemClickListener) : RecyclerView.Adapter<MoodListAdapter.ViewHolder>() {

    var data = mutableListOf<ListData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item,clickListener)
    }

    override fun getItemCount() = data?.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * 方法一：
         */
        val img_mood: ImageView = itemView.findViewById(R.id.quality_image) //itemView.quality_image
        val txv_title: TextView = itemView.findViewById(R.id.txv_description) //itemView.txv_description 可以直接替換成這個
        fun bind(item: ListData,clickListener:ItemClickListener) {
            img_mood.setImageResource(
                when (item.moodIndex) {
                    0 -> R.drawable.ic_sleep_0
                    1 -> R.drawable.ic_sleep_1
                    2 -> R.drawable.ic_sleep_2
                    3 -> R.drawable.ic_sleep_3
                    4 -> R.drawable.ic_sleep_4
                    5 -> R.drawable.ic_sleep_5
                    else -> R.drawable.ic_sleep_0
                }
            )
            txv_title.text = item.time.toString()
            txv_title.setOnClickListener {
                clickListener.onClick(item.time.toString())
            }

            //2021/05/07新增旋轉動畫
//            if (img_mood.isSelected) {
                img_mood.startAnimation(
                    AnimationUtils.loadAnimation(
                        itemView.context,
                        R.anim.rotate_sport
                    )
                )
//            }
        }
        /**
         * 方法二：
         */
        fun bind2(item: ListData,clickListener:ItemClickListener) {
            itemView.quality_image.setImageResource(
                when (item.moodIndex) {
                    0 -> R.drawable.ic_sleep_0
                    1 -> R.drawable.ic_sleep_1
                    2 -> R.drawable.ic_sleep_2
                    3 -> R.drawable.ic_sleep_3
                    4 -> R.drawable.ic_sleep_4
                    5 -> R.drawable.ic_sleep_5
                    else -> R.drawable.ic_sleep_0
                }
            )
            itemView.txv_description.text = item.time.toString()
            itemView.setOnClickListener {
                clickListener.onClick(item.time.toString())
            }
        }

        /**
         * 綁定Layout
         */
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.content_mood_list, parent, false)
                return ViewHolder(view)
            }
        }
    }

    class ItemClickListener(private val clickListener: (string:String) -> Unit) {
        fun onClick(string:String) = clickListener(string)
    }
}