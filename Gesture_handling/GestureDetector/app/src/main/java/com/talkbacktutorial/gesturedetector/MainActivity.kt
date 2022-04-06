package com.talkbacktutorial.gesturedetector

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MotionEventCompat
import com.talkbacktutorial.gesturedetector.databinding.ActivityMainBinding
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var swipeTouchListener: OnSwipeTouchListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.swipeTouchListener = OnSwipeTouchListener(this)
            .setSwipeUp(::onSwipeUp)
            .setSwipeDown(::onSwipeDown)
            .setSwipeLeft(::onSwipeLeft)
            .setSwipeRight(::onSwipeRight)
        /*
        Could also do:

        this.swipeTouchListener = OnSwipeTouchListener(this)
            .setSwipeUp {
                // Do things here
            }

        The lesson here is lambda expressions are very useful
         */
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val action: Int = event?.action ?: return false

        this.swipeTouchListener.callOnEvent(event)

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
        binding.onTouchEventLabel.text = text
    }

    fun onSwipeRight() {
        binding.gestureDetectorLabel.text = "Swiped Right"
    }

    fun onSwipeLeft() {
        binding.gestureDetectorLabel.text = "Swiped Left"
    }

    fun onSwipeUp() {
        binding.gestureDetectorLabel.text = "Swiped Up"
    }

    fun onSwipeDown() {
        binding.gestureDetectorLabel.text = "Swiped Down"
    }

}