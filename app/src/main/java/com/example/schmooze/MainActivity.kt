package com.example.schmooze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.findNavController
import android.content.Intent


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val ll_scheduler = findViewById<LinearLayout>(R.id.llScheduler) as LinearLayout

        //create the button
        //val button_scheduler = Button(this)
        // setting layout_width and layout_height using layout parameters
       // button_scheduler.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
       // button_scheduler.text = "Scheduler"
        // add Button to LinearLayout
       // ll_scheduler.addView(button_scheduler)

       // val btnScheduler = findViewById<LinearLayout>(R.id.llScheduler)
        //btnScheduler.setOnClickListener{
            //view -> view.findNavController().navigate(R.id.action_mainActivity_to_blankScheduler)
        //}

        // get reference to Linear Layout
        val schedule_click = findViewById<LinearLayout>(R.id.llScheduler)
        // set onclick listener
        schedule_click.setOnClickListener(){
            //code to perform
            view -> view.findNavController().navigate(R.id.activityScheduler)
        }
    }
}
