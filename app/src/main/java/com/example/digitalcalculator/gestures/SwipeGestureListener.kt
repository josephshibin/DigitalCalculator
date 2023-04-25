package com.example.digitalcalculator.gestures


import android.view.GestureDetector
import android.view.MotionEvent
import kotlin.math.abs

class SwipeGestureListener(val onSwipeUp: () -> Unit, val onSwipeDown: () -> Unit,
                           val onSwipeLeft: () -> Unit, val onSwipeRight: () -> Unit) :
    GestureDetector.SimpleOnGestureListener() {

    companion object {
        private const val SWIPE_THRESHOLD = 100
        private const val SWIPE_VELOCITY_THRESHOLD = 100
    }

    override fun onDown(e: MotionEvent): Boolean {
        return true
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        val diffX = e2?.x?.minus(e1?.x ?: 0F) ?: 0F
        val diffY = e2?.y?.minus(e1?.y ?: 0F) ?: 0F

        if (abs(diffX) > abs(diffY)) {
            if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    onSwipeRight()
                } else {
                    onSwipeLeft()
                }
                return true
            }
        } else {
            if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY < 0) {
                    onSwipeUp()
                } else {
                    onSwipeDown()
                }
                return true
            }
        }

        return false
    }
}
