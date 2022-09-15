package kg.o.internlabs.sokoban

import android.graphics.*
import android.view.View


class CanvasSokoban : View {
    private val model: Model
    private val paint: Paint
    private val viewer: Viewer
    private var desktop: Array<IntArray>

    constructor(viewer: Viewer, model: Model) : super(viewer) {
        this.viewer = viewer
        this.model = model
        paint = Paint()
        desktop = model.getDesktop()
        println("I am CanvasSokoban")

        setBackgroundResource(R.drawable.black_background)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var rect: Rect = Rect()
        rect.offset(model.getX(), model.getY())
        var blue: Paint = Paint()
        var left = 20
        var top = 100
        var right = canvas.width - 970
        var bottom = (canvas.height / 2) - 800
        var dx = model.getX()
        var dy = model.getY()
        for (i in 0 until desktop.size) {

            for (j in 0 until desktop[i].size) {
                if (desktop[i][j] == 1) {
                    paint.setColor(Color.RED)
                    paint.style = Paint.Style.FILL
                    rect.set(left, top, right, bottom)
                    rect.offset(dx, dy)
                    canvas.drawRect(rect, paint)

                }
                if (desktop[i][j] == 2) {
                    paint.setColor(Color.GREEN)
                    paint.style = Paint.Style.FILL
                    rect.set(left, top, right, bottom)
                    rect.offset(dx, dy)
                    canvas.drawRect(rect, paint)
                } else {


                    paint.setColor(Color.WHITE)
                    paint.style = Paint.Style.STROKE
                    rect.set(left, top, right, bottom)
                    rect.offset(dx, dy)
                    canvas.drawRect(rect, paint)

                }
                dx += 100

            }

            dy += 180
            dx = 0

        }
    }

    fun update() {
        invalidate()
    }

}
