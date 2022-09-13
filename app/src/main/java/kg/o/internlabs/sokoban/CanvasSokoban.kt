package kg.o.internlabs.sokoban

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import kg.o.internlabs.sokoban.R


class CanvasSokoban : View {
    private val model: Model
    private val paint: Paint

    constructor(viewer: Viewer, model: Model) : super(viewer) {
        this.model = model
        paint = Paint()
        setBackgroundResource(R.drawable.black_background)
        println("I am CanvasSokoban")


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.setColor(Color.RED)
        canvas?.drawRect(30F,30F,80F,80F,paint)

    }
}