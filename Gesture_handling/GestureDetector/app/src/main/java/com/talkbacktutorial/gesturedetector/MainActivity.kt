package com.talkbacktutorial.gesturedetector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.core.view.MotionEventCompat
import com.talkbacktutorial.gesturedetector.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val DEBUG_TAG: String = "Debug"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val action: Int = MotionEventCompat.getActionMasked(event)

        return when (action) {
            MotionEvent.ACTION_DOWN -> {
                binding.gestureLabel.setText("Action Down")
                true
            }
            else -> super.onTouchEvent(event)
        }
    }
}