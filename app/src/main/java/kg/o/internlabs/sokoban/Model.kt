package kg.o.internlabs.sokoban

class Model {
    private val viewer: Viewer
    private var indexX: Int
    private var indexY: Int
    private var desktop: Array<IntArray>
    private var x: Int
    private var y: Int
    private lateinit var arrayOfIndexes: Array<IntArray>


    constructor(viewer: Viewer) {
        this.viewer = viewer
        desktop = arrayOf(
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
            intArrayOf(2, 0, 0, 0, 4, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 1, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 4, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 4, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),


            )
        indexX = 2
        indexY = 3
        initialization()
        x = 0
        y = -10
        println("I am model object")
    }

    private fun initialization() {
        var countFour = 0
        for (i in 0 until desktop.size) {
            for (j in 0 until desktop[i].size) {
                if (desktop[i][j] == 4) {
                    countFour = countFour + 1
                }
            }
        }
        arrayOfIndexes = Array(2) { IntArray(countFour) { 0 } }
        for (i in 0 until arrayOfIndexes.size) {

            for (j in 0 until arrayOfIndexes[i].size) {
                print("${arrayOfIndexes[i][j]}  ")
            }
            println()
        }
        println()
        var t = 0
        for (i in 0 until desktop.size) {
            for (j in 0 until desktop[i].size) {
                if (desktop[i][j] == 4) {
                    arrayOfIndexes[0][t] = i
                    arrayOfIndexes[1][t] = j
                    t = t + 1
                }
            }
        }
        for (i in 0 until arrayOfIndexes.size) {

            for (j in 0 until arrayOfIndexes[i].size) {
                print("${arrayOfIndexes[i][j]}  ")
            }
            println()
        }
        println()


    }

    fun move(direction: String) {
        when (direction) {
            "Right" -> moveRight()
            "Left" -> moveLeft()
            "Down" -> moveDown()
            "Up" -> moveUp()
            else -> return

        }
        check()
        printDesktop()
        viewer.update()
    }

    private fun check() {

        for (j in 0 until arrayOfIndexes[0].size) {
            var x = arrayOfIndexes[0][j]
            var y = arrayOfIndexes[1][j]
            println("${j + 1}  $x  $y")
            if (desktop[x][y] == 0) {
                desktop[x][y] = 4
                return
            }
        }
        println()
    }


    private fun moveRight() {
        if (desktop[indexX][indexY + 1] == 0 || desktop[indexX][indexY + 1] == 4) {
            desktop[indexX][indexY] = 0
            indexY = indexY + 1
            desktop[indexX][indexY] = 1

        }


    }

    private fun moveLeft() {
        if (desktop[indexX][indexY - 1] == 0 || desktop[indexX][indexY - 1] == 4) {
            desktop[indexX][indexY] = 0
            indexY = indexY - 1
            desktop[indexX][indexY] = 1

        }


    }

    private fun moveDown() {
        if (desktop[indexX + 1][indexY] == 0 || desktop[indexX + 1][indexY] == 4) {
            desktop[indexX][indexY] = 0
            indexX = indexX + 1
            desktop[indexX][indexY] = 1
        }


    }

    private fun moveUp() {
        if (desktop[indexX - 1][indexY] == 0 || desktop[indexX - 1][indexY] == 4) {
            desktop[indexX][indexY] = 0
            indexX = indexX - 1
            desktop[indexX][indexY] = 1
        }


    }

    private fun printDesktop() {
        for (i in 0 until desktop.size) {

            for (j in 0 until desktop[i].size) {
                print(desktop[i][j])
            }
            println()
        }
        println()
        println()
        println("---------------------------------")
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