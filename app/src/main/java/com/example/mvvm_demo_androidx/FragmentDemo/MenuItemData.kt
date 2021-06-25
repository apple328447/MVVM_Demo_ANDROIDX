package com.example.mvvm_demo_androidx.FragmentDemo

data class MenuItemData(
    val imgId:Int,
    val gameName:String,
    val title:String,
    var isSelected:Int //0:沒被置頂 ; 1:置頂
)
