package com.talkbacktutorial.gesturedetector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.core.view.MotionEventCompat
import com.talkbacktutorial.gesturedetector.databinding.ActivityMainBinding
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val action: Int = event?.action ?: return false

        return when (action) {
            MotionEvent.ACTION_MOVE -> {
                /*
                A change has occurred during the touch event (between ACTION_DOWN and ACTION_UP).
                An ACTION_MOVE contains the pointer's most recent x and y coordinates along with
                any intermediate points since the last DOWN or MOVE event.
                 */
                // Could use this to create our own custom gestures?
                setGestureText("x: ${event.rawX} \n y: ${event.rawY}")
                true
            }
            MotionEvent.ACTION_DOWN -> {
                setGestureText("Action Down")
                true
            }
            MotionEvent.ACTION_UP -> {
                setGestureText("Action Up")
                true
            }
            else -> super.onTouchEvent(event)
        }
    }

    private fun setGestureText(text: String) {
        binding.gestureLabel.text = text
    }
}