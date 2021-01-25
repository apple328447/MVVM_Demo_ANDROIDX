package com.example.mvvm_demo_androidx.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvm_demo_androidx.R
import kotlinx.android.synthetic.main.activity_base_tool_bar.*

abstract class BaseToolBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_tool_bar)

        val view = resources.getLayout(setContentView())
        val view2 = layoutInflater.inflate(resources.getLayout(setContentView()), null)
        frame_layout.addView(view2)
    }
    abstract fun setContentView():Int
}