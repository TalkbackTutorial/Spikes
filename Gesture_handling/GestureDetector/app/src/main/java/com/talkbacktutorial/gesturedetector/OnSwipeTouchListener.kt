package com.talkbacktutorial.gesturedetector

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class OnSwipeTouchListener(context: Context): GestureDetector.SimpleOnGestureListener() {

    companion object {
        private const val SWIPE_THRESHOLD = 100
        private const val SWIPE_VELOCITY_THRESHOLD = 100
    }

    private val detector = GestureDetector(context, this)

    private var onSwipeUp: (() -> Unit)? = null
    private var onSwipeDown: (() -> Unit)? = null
    private var onSwipeLeft: (() -> Unit)? = null
    private var onSwipeRight: (() -> Unit)? = null

    fun callOnEvent(event: MotionEvent) { this.detector.onTouchEvent(event) }
    fun setSwipeUp(call: () -> Unit): OnSwipeTouchListener {
        this.onSwipeUp = call
        return this
    }
    fun setSwipeDown(call: () -> Unit): OnSwipeTouchListener {
        this.onSwipeDown = call
        return this
    }
    fun setSwipeLeft(call: () -> Unit): OnSwipeTouchListener {
        this.onSwipeLeft = call
        return this
    }
    fun setSwipeRight(call: () -> Unit): OnSwipeTouchListener {
        this.onSwipeRight = call
        return this
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
                if (xDelta > 0) this.onSwipeRight?.let { it() }
                else this.onSwipeLeft?.let { it() }
                return true
            } else {
                super.onFling(downMotionEvent, moveMotionEvent, velocityX, velocityY)
            }
        } else {
            if (abs(yDelta) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (yDelta > 0) this.onSwipeDown?.let { it() }
                else this.onSwipeUp?.let { it() }
                return true
            } else {
                super.onFling(downMotionEvent, moveMotionEvent, velocityX, velocityY)
            }
        }
        return false
    }

}