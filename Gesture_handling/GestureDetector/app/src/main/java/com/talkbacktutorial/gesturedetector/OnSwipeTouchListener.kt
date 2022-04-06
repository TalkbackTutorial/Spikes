package com.talkbacktutorial.gesturedetector

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class OnSwipeTouchListener(context: Context): View.OnTouchListener {

    private val gestureDetector: GestureDetector = GestureDetector(context, GestureListener())

    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        return this.gestureDetector.onTouchEvent(motionEvent)
    }

    private class GestureListener: GestureDetector.SimpleOnGestureListener() {

        companion object {
            private const val SWIPE_THRESHOLD = 100
            private const val SWIPE_VELOCITY_THRESHOLD = 100
        }

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

        fun onSwipeRight() { }

        fun onSwipeLeft() { }

        fun onSwipeUp() { }

        fun onSwipeDown() { }

    }

}