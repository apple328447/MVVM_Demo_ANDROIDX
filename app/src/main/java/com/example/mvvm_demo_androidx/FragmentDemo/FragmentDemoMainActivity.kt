package com.example.mvvm_demo_androidx.FragmentDemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.mvvm_demo_androidx.R
import kotlinx.android.synthetic.main.activity_fragment_demo_main.*

class FragmentDemoMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_demo_main)
        initButton()
    }

    private fun initButton(){
        val navController = findNavController(R.id.fc_container)

        btn_left_menu.setOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.homeFragment -> navController.navigate(R.id.action_homeFragment_to_leftMenuFragment)
                else -> return@setOnClickListener
            }
        }
    }
}