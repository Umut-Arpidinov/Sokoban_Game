package kg.o.internlabs.sokoban

class Model {
    private val viewer: Viewer
    private var indexX: Int
    private var indexY: Int
    private var desktop: Array<IntArray>
    private lateinit var arrayOfIndexes: Array<IntArray>
    private var stateModel: Boolean
    private var playerDirection = "stay"
    private var levels: Levels
    private var level: Int = 1


    constructor(viewer: Viewer) {
        this.viewer = viewer
        indexY = 0
        indexX = 0
        stateModel = true
        levels = Levels(viewer)
        desktop = levels.nextLevel(level)
        initialization()
        println("I am model ")

    }


    private fun initialization() {
        var countFour = 0
        var countOne = 0
        var countThree = 0
        for (i in 0 until desktop.size) {
            for (j in 0 until desktop[i].size) {
                if (desktop[i][j] == 1) {
                    countOne = countOne + 1
                    indexX = i
                    indexY = j
                } else if (desktop[i][j] == 4) {
                    countFour = countFour + 1
                } else if (desktop[i][j] == 3) {
                    countThree = countThree + 1

                }

            }
        }
        if (countOne != 1 || (countThree != countFour) || countThree <= 0 || countFour <= 0) {
            stateModel = false
            return
        }
        arrayOfIndexes = Array(2) { IntArray(countFour) { 0 } }
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
        viewer.update()
        won()


    }

    fun won() {
        var won = true
        for (i in 0 until arrayOfIndexes[0].size) {
            var x = arrayOfIndexes[0][i]
            var y = arrayOfIndexes[1][i]
            if (desktop[x][y] != 7) {
                won = false
                break
            }
        }
        if (won) {
            viewer.showWindDialog()
            playerDirection = "stay"
            level = level + 1
            newLevel(level)
            initialization()
            viewer.update()

        }
    }

    private fun check() {
        for (j in 0 until arrayOfIndexes[0].size) {
            var x = arrayOfIndexes[0][j]
            var y = arrayOfIndexes[1][j]
            if (desktop[x][y] == 0) {
                desktop[x][y] = 4

            }
        }
        println()
    }

    private fun moveRight() {
        if (desktop[indexX][indexY + 1] == 3 ||desktop[indexX][indexY + 1] == 7 ) {
            if (desktop[indexX][indexY + 2] == 0) {
                desktop[indexX][indexY + 1] = 0
                desktop[indexX][indexY + 2] = 3
                playerDirection = "right"

            }
            if (desktop[indexX][indexY + 2] == 4) {
                desktop[indexX][indexY + 1] = 0
                desktop[indexX][indexY + 2] = 7
                playerDirection = "right"


            }


        }

        if (desktop[indexX][indexY + 1] == 0 || desktop[indexX][indexY + 1] == 4) {
            desktop[indexX][indexY] = 0
            indexY = indexY + 1
            desktop[indexX][indexY] = 1
            playerDirection = "right"


        }


    }

    private fun moveLeft() {
        if (desktop[indexX][indexY - 1] == 3 || desktop[indexX][indexY - 1] == 7) {
            if (desktop[indexX][indexY - 2] == 0 ) {
                desktop[indexX][indexY - 1] = 0
                desktop[indexX][indexY - 2] = 3
                playerDirection = "left"
            }
            if (desktop[indexX][indexY - 2] == 4){
                desktop[indexX][indexY - 1] = 0
                desktop[indexX][indexY - 2] = 7
                playerDirection = "left"
            }

        }
        if (desktop[indexX][indexY - 1] == 0 || desktop[indexX][indexY - 1] == 4) {
            desktop[indexX][indexY] = 0
            indexY = indexY - 1
            desktop[indexX][indexY] = 1
            playerDirection = "left"


        }


    }

    private fun moveDown() {
        if (desktop[indexX + 1][indexY] == 3 || desktop[indexX + 1][indexY] == 7) {
            if (desktop[indexX + 2][indexY] == 0) {
                desktop[indexX + 1][indexY] = 0
                desktop[indexX + 2][indexY] = 3
                playerDirection = "down"
            }
            if(desktop[indexX + 2][indexY] == 4){
                desktop[indexX + 1][indexY] = 0
                desktop[indexX + 2][indexY] = 7
                playerDirection = "down"
            }


        }
        if (desktop[indexX + 1][indexY] == 0 || desktop[indexX + 1][indexY] == 4) {
            desktop[indexX][indexY] = 0
            indexX = indexX + 1
            desktop[indexX][indexY] = 1
            playerDirection = "down"

        }


    }

    private fun moveUp() {
        if (desktop[indexX - 1][indexY] == 3 || desktop[indexX - 1][indexY] == 7) {
            if (desktop[indexX - 2][indexY] == 0) {
                desktop[indexX - 1][indexY] = 0
                desktop[indexX - 2][indexY] = 3
                playerDirection = "up"
            }
            if(desktop[indexX - 2][indexY] == 4)  {
                desktop[indexX - 1][indexY] = 0
                desktop[indexX - 2][indexY] = 7
                playerDirection = "up"
            }


        }
        if (desktop[indexX - 1][indexY] == 0 || desktop[indexX - 1][indexY] == 4) {
            desktop[indexX][indexY] = 0
            indexX = indexX - 1
            desktop[indexX][indexY] = 1
            playerDirection = "up"

        }


    }

    fun newLevel(level: Int) {
        desktop = levels.nextLevel(level)
        this.level = level
        initialization()
        viewer.update()
    }


    fun getStateModel(): Boolean {
        return stateModel
    }

    fun getDesktop(): Array<IntArray> {
        return desktop
    }

    fun getIconDirection(): String {
        return playerDirection
    }


    fun getCurrenLevel(): Int {
        return level
    }



}
