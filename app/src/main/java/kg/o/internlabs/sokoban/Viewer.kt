package kg.o.internlabs.sokoban

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Viewer : AppCompatActivity {
    private val controller: Controller
    private var canvas: CanvasSokoban?
    private val model: Model

    constructor() {
        controller = Controller(this)
        model = controller.getModel()
        canvas = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        canvas = CanvasSokoban(this, model)
        setContentView(canvas)
        canvas?.setOnTouchListener(controller)

        println("I am viewer object")
    }


    fun update() {
        canvas?.update()
    }


}