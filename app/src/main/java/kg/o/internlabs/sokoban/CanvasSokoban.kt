package kg.o.internlabs.sokoban

import android.graphics.*
import android.view.View


class CanvasSokoban : View {
    private val model: Model
    private val paint: Paint
    private val player: Bitmap
    private val wall: Bitmap
    private val viewer: Viewer
    private val box: Bitmap
    private val ground: Bitmap
    private val boxOntarget: Bitmap
    private val target: Bitmap
    private val error: Bitmap
    private var iconDirection: String
    private var move_right: Bitmap


    constructor(viewer: Viewer, model: Model) : super(viewer) {
        this.viewer = viewer
        this.model = model
        paint = Paint()
        iconDirection = model.getIconDirection()
        println("I am CanvasSokoban")

        player = BitmapFactory.decodeResource(resources, R.drawable.player)


        wall = BitmapFactory.decodeResource(resources, R.drawable.wall)
        box = BitmapFactory.decodeResource(resources, R.drawable.box)
        boxOntarget = BitmapFactory.decodeResource(resources, R.drawable.box_on_target)
        ground = BitmapFactory.decodeResource(resources, R.drawable.gray_ground)
        target = BitmapFactory.decodeResource(resources, R.drawable.target)
        error = BitmapFactory.decodeResource(resources, R.drawable.error2)
        move_right = BitmapFactory.decodeResource(resources, R.drawable.move_right)
        setBackgroundResource(R.drawable.star_back)

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (model.getStateModel()) {
            drawLevelMessage(canvas)
            drawContenGame(canvas)
        } else {

            drawError(canvas)
        }

    }

    fun drawContenGame(canvas: Canvas) {
        var a = 0
        var x = 0
        var y = 200
        var width = 108
        var height = 140
        val desktop = model.getDesktop()

        for (i in 0 until desktop.size) {

            for (j in 0 until desktop[i].size) {
                canvas.drawBitmap(ground, null, Rect(x, y, x + width, y + height), paint)
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
    private fun drawLevelMessage(canvas: Canvas){
        paint.setColor(Color.parseColor("#cca300"))
        paint.textSize = 70F
        paint.setTypeface(Typeface.DEFAULT_BOLD)
        canvas.drawText("Level - " + model.getCurrenLevel(),350F,150F,paint)
    }


    private fun drawError(canvas: Canvas) {
        paint.setColor(Color.RED)
        paint.textSize = 100F
        paint.setTypeface(Typeface.DEFAULT_BOLD)
        canvas.drawText("Initialization Error !", 50F, 150F, paint)
        canvas.drawBitmap(error, null, Rect(0, 200, width - 10, height - 100), paint)

    }


    fun update() {
        invalidate()
    }

}


