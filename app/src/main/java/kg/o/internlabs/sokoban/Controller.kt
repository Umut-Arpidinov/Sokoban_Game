package kg.o.internlabs.sokoban

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View


class Controller : View.OnTouchListener, View.OnClickListener,
    GestureDetector.SimpleOnGestureListener {
    private val model: Model
    private val gestureDetector: GestureDetector
    private val viewer: Viewer
    private var SWIPE_THRESHOLD: Int
    private var SWIPE_VELOCITY_THRESHOLD: Int


    constructor(viewer: Viewer) {
        this.viewer = viewer
        model = Model(viewer)
        gestureDetector = GestureDetector(this)
        SWIPE_THRESHOLD = 100
        SWIPE_VELOCITY_THRESHOLD = 100
        println("I am controller object")
    }

    fun getModel(): Model {
        return model
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(
        downEvent: MotionEvent?,
        moveEvent: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        val diffX = moveEvent?.x?.minus(downEvent!!.x) ?: 0.0F
        val diffY = moveEvent?.y?.minus(downEvent!!.y) ?: 0.0F
        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    onSwipeRight()
                } else {
                    onSwipeLeft()
                }
            }
        } else {
            if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    onSwipeDown()
                } else {
                    onSwipeUp()
                }
            }
        }
        return super.onFling(downEvent, moveEvent, velocityX, velocityY)
    }


    private fun onSwipeRight() {
        model.move("Right")
        viewer.update()
        println("Right")
    }

    private fun onSwipeLeft() {
        model.move("Left")
        println("Left")
    }

    private fun onSwipeUp() {
        model.move("Up")
    }

    private fun onSwipeDown() {
        model.move("Down")
    }

}