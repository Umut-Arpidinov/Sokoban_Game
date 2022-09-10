package kg.o.internlabs.sokoban

class Controller {
    private val model: Model

    constructor(viewer: Viewer) {
        model = Model(viewer)
        println("I am controller object")
    }

    fun getModel(): Model {
        return model
    }
}