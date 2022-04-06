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

        val action: Int = MotionEventCompat.getActionMasked(event)

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
                setGestureText("x: ${event?.rawX.toString()} \n y: ${event?.rawY.toString()}")
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
        binding.gestureLabel.setText(text)
    }
}