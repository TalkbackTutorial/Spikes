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
    private lateinit var detector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.detector = GestureDetector(this, GestureListener())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val action: Int = event?.action ?: return false

        this.detector.onTouchEvent(event)

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

    inner class GestureListener: GestureDetector.SimpleOnGestureListener() {

        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(
            downMotionEvent: MotionEvent?,
            moveMotionEvent: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val xDelta: Float = moveMotionEvent?.x?.minus(downMotionEvent!!.x) ?: 0.0F
            val yDelta: Float = moveMotionEvent?.y?.minus(downMotionEvent!!.y) ?: 0.0F
            if (abs(xDelta) > abs(yDelta)) {
                if (abs(xDelta) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (xDelta > 0) onSwipeRight()
                    else onSwipeLeft()
                    return true
                } else {
                    super.onFling(downMotionEvent, moveMotionEvent, velocityX, velocityY)
                }
            } else {
                if (abs(yDelta) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (yDelta > 0) onSwipeDown()
                    else onSwipeUp()
                    return true
                } else {
                    super.onFling(downMotionEvent, moveMotionEvent, velocityX, velocityY)
                }
            }
            return false
        }

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