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

                Could use this to check for advanced gestures?
                Idea1: We have the x and y coordinates of each touch, therefore, on the first touch
                we can construct a box (store coordinates of each corner) around the first touch,
                along as the following touches are within the box the tracking continues (with) each
                touch we move the box (to prevent going backwards). When the touch changes direction
                we create a new box and repeat the previous steps.
                The issue is we couldn't use this for sandbox mode as we need to know what gesture
                they are attempting so we can construct the correct boxes.
                We can test talkback gestures to estimate what sizes the boxes should be.
                Diagram:
                    ___________
                    |     _____|___________________
                    |     |    |  . ..       ...  |
                    |     T ...|....  .......   T |
                    |    .|____|__________________|
                    |     .    |
                    |     .    |
                    |   .      |
                    |_____T____|
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