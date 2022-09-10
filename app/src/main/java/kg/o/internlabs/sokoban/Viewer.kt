package kg.o.internlabs.sokoban

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Viewer : AppCompatActivity {
    private var canvas: CanvasSokoban?
    private val model: Model

    constructor() {
        val controller: Controller = Controller(this)
        model = controller.getModel()
        canvas = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        canvas = CanvasSokoban(this, model)
        setContentView(canvas)

        println("I am viewer object")
    }

}