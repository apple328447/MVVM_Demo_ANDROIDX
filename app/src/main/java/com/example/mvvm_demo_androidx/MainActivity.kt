package com.example.mvvm_demo_androidx

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvm_demo_androidx.base.BaseToolBarActivity

class MainActivity : BaseToolBarActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
    }

    override fun setContentView(): Int {
        return R.layout.activity_main
    }
}