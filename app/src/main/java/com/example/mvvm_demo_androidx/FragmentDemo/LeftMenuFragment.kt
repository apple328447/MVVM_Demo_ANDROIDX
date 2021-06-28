package com.example.mvvm_demo_androidx.FragmentDemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_demo_androidx.R
import kotlinx.android.synthetic.main.fragment_left_menu.*


class LeftMenuFragment : Fragment() {

    var selectedData: MutableList<MenuItemData> = mutableListOf()

    var mData: MutableList<MenuItemData> = mutableListOf(
        MenuItemData(R.drawable.ic_soccer, "足球", SportType.FOOTBALL.code, 0),
        MenuItemData(R.drawable.ic_basketball, "籃球", SportType.BASKETBALL.code, 0),
        MenuItemData(R.drawable.ic_tennis, "羽球", SportType.TENNIS.code, 0),
        MenuItemData(R.drawable.ic_volleyball, "排球", SportType.VOLLEYBALL.code, 0)
    )

    //點擊置頂後
    var unselectedAdapter = LeftMenuItemAdapter(LeftMenuItemAdapter.ItemClickListener { gameName ->
        mData.forEach {
            if (it.gameName == gameName) {
                it.isSelected = 1 //要改成已讀
                selectedData.add(it)
            }
        }
        refreshRv(mData)
        refreshRv2(selectedData)
    })

    //取消置頂
    var selectedAdapter =
        LeftMenuItemSelectedAdapter(LeftMenuItemSelectedAdapter.ItemClickListener { gameName ->
            mData.forEach {
                if (it.gameName == gameName) {
                    it.isSelected = 0 // 改成未讀
                }
            }

            for (i in 0 until selectedData.size) {
                if (selectedData[i].gameName == gameName) {
                    selectedData.removeAt(i)
                    break
                }
            }

            refreshRv(mData)
            refreshRv2(selectedData)
        })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_left_menu, container, false)
    }


    fun initRecyclerView() {

        rv_unselect.layoutManager =
            object : LinearLayoutManager(rv_unselect.context, VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }


//        rv_unselect.layoutManager = LinearLayoutManager(rv_unselect.context,LinearLayoutManager.VERTICAL,false)
        rv_unselect.adapter = unselectedAdapter
        unselectedAdapter.data = mData


        rv_selected.layoutManager =
            object : LinearLayoutManager(rv_selected.context, VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
        rv_selected.adapter = selectedAdapter
        selectedAdapter.data = selectedData

    }

    private fun refreshRv(mData: List<MenuItemData>) {
        unselectedAdapter.data = mData
    }

    private fun refreshRv2(selectedData: MutableList<MenuItemData>) {
        selectedAdapter.data = selectedData
    }
}