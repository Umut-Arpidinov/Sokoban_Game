package kg.o.internlabs.sokoban

import android.view.View
import kg.o.internlabs.sokoban.R


class CanvasSokoban : View {
    private val model: Model

    constructor(viewer: Viewer, model: Model) : super(viewer) {
        this.model = model
        setBackgroundResource(R.drawable.black_background)
        println("I am CanvasSokoban")


    }
}