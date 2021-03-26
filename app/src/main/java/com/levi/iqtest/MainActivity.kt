package com.levi.iqtest

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar?.hide()
//        this.supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
//        supportActionBar!!.setDisplayShowCustomEnabled(false)
//        supportActionBar!!.setCustomView(R.layout.custom_action_bar)
//        val view: View = supportActionBar!!.customView
//        val name: TextView = view.findViewById(R.id.name)
//        name.setOnClickListener {
//            Toast.makeText(this@MainActivity, "You have clicked tittle", Toast.LENGTH_LONG)
//                .show()
//        }
//        view.findViewById<AppCompatImageButton>(R.id.btnBack).setOnClickListener {
//            this.onBackPressed()
//        }
//        view.findViewById<AppCompatImageButton>(R.id.btnNextQuestion).setOnClickListener {
//
//        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}
