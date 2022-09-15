package kg.o.internlabs.sokoban

class Model {
    private val viewer: Viewer
    private var indexX: Int
    private var indexY: Int
    private var desktop: Array<IntArray>
    private var x: Int
    private var y: Int


    constructor(viewer: Viewer) {
        this.viewer = viewer
        desktop = arrayOf(
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 1, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),



            )
        indexX = 2
        indexY = 3
        x = 0
        y = -10
        println("I am model object")
    }

    fun move(direction: String) {
        when (direction) {
            "Right" -> moveRight()
            "Left" -> moveLeft()
            "Down" -> moveDown()
            "Up" -> moveUp()
            else -> return

        }
        viewer.update()
    }

    private fun moveRight() {
        if(desktop[indexX][indexY+1] == 0){
            desktop[indexX][indexY] = 0
            indexY = indexY + 1
            desktop[indexX][indexY] = 1

            }


    }

    private fun moveLeft() {
        if(desktop[indexX][indexY-1] == 0){
            desktop[indexX][indexY] = 0
            indexY = indexY - 1
            desktop[indexX][indexY] = 1

        }


    }

    private fun moveDown() {
        if(desktop[indexX+1][indexY ] ==0) {
            desktop[indexX][indexY] = 0
            indexX = indexX + 1
            desktop[indexX][indexY] = 1
        }


    }

    private fun moveUp() {
        if (desktop[indexX-1][indexY]==0) {
            desktop[indexX][indexY] = 0
            indexX = indexX - 1
            desktop[indexX][indexY] = 1
        }


    }

    fun getDesktop(): Array<IntArray> {
        return desktop
    }

    fun getX(): Int {
        return x

    }

    fun getY(): Int {
        return y
    }

}