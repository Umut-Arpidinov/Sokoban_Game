package kg.o.internlabs.sokoban

import android.graphics.*
import android.view.View


class CanvasSokoban : View {
    private val model: Model
    private val paint: Paint
    private val viewer: Viewer
    private var desktop: Array<IntArray>
    private val player: Bitmap
    private val wall: Bitmap
    private val box: Bitmap
    private val ground: Bitmap
    private val boxOntarget: Bitmap
    private val target: Bitmap
    private val error: Bitmap


    constructor(viewer: Viewer, model: Model) : super(viewer) {
        this.viewer = viewer
        this.model = model
        desktop = model.getDesktop()
        paint = Paint()
        println("I am CanvasSokoban")
        player = BitmapFactory.decodeResource(resources, R.drawable.player)
        wall = BitmapFactory.decodeResource(resources, R.drawable.wall)
        box = BitmapFactory.decodeResource(resources, R.drawable.box)
        boxOntarget = BitmapFactory.decodeResource(resources, R.drawable.box_on_target)
        ground = BitmapFactory.decodeResource(resources, R.drawable.ground)
        target = BitmapFactory.decodeResource(resources, R.drawable.target)
        error = BitmapFactory.decodeResource(resources, R.drawable.error2)
        setBackgroundResource(R.drawable.black_background)

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (model.getStateModel()) {
            drawContenGame(canvas)
        } else {

            drawError(canvas)
        }

    }

    fun drawContenGame(canvas: Canvas) {
        var a = 0
        var x = 0
        var y = 200
        var width = 107
        var height = 160

        for (i in 0 until desktop.size) {

            for (j in 0 until desktop[i].size) {
                if (desktop[i][j] == 2 && desktop[i][j] - 1 == 0) {

                } else {
                    canvas.drawBitmap(ground, null, Rect(x, y, x + width, y + height), paint)

                }


                if (desktop[i][j] == 1) {
                    canvas.drawBitmap(player, null, Rect(x, y, x + width, y + height), paint)
                }
                if (desktop[i][j] == 2) {
                    canvas.drawBitmap(wall, null, Rect(x, y, x + width, y + height), paint)
                }
                if (desktop[i][j] == 3) {
                    canvas.drawBitmap(box, null, Rect(x, y, x + width, y + height), paint)
                }
                if (desktop[i][j] == 4) {
                    canvas.drawBitmap(target, null, Rect(x, y, x + width, y + height), paint)
                }

                x += width
            }
            x = 0
            y += height
        }

    }

    private fun drawError(canvas: Canvas) {
        paint.setColor(Color.RED)
        paint.textSize = 100F
        paint.setTypeface(Typeface.DEFAULT_BOLD)
        canvas.drawText("Initialization Error !!!", 50F, 150F, paint)
        canvas.drawBitmap(error, null, Rect(0, 200, width - 10, height - 100), paint)

    }


    fun update() {
        invalidate()
    }

}
