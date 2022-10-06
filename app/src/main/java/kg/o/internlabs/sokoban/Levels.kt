package kg.o.internlabs.sokoban

import ConnectToServer
import android.content.Context
import java.io.FileNotFoundException
import java.io.InputStream


class Levels {
    private var prefixFileName: String
    private var endFileName: String
    private val viewer: Viewer
    private var desktop: String?
    private var level: Int = 1

    constructor(viewer: Viewer) {
        this.viewer = viewer
        prefixFileName = "level"
        endFileName = ".sok"
        desktop = "default"
        println("I am level")
    }

    fun nextLevel(level: Int): Array<IntArray> {
        var desktop: Array<IntArray>
        when (level) {
            1 -> {
                desktop = filterZeros(getFirstLevel())
                this.level = level
            }
            2 -> {
                desktop = filterZeros(getSecondLevel())
                this.level = level
            }
            3 -> {
                desktop = filterZeros(getThirdLevel())
                this.level = level
            }
            4 -> {
                desktop =
                    filterZeros(loadLevelFromFile(prefixFileName + level + endFileName, viewer))
                this.level = level

            }
            5 -> {
                desktop =
                    filterZeros(loadLevelFromFile(prefixFileName + level + endFileName, viewer))
                this.level = level
            }
            6 -> {
                desktop =
                    filterZeros(loadLevelFromFile(prefixFileName + level + endFileName, viewer))
                this.level = level
            }
            7 -> {

                if (loadLevelFromServer(7) == null) {
                    desktop = filterZeros(getFirstLevel())
                    this.level = 1
                } else {
                    desktop =
                        filterZeros(loadLevelFromServer(level)!!)
                    this.level = level
                }

            }
            8 -> {
                if (loadLevelFromServer(7) == null) {
                    desktop = filterZeros(getFirstLevel())
                    this.level = 1
                } else {
                    desktop =
                        filterZeros(loadLevelFromServer(level)!!)
                    this.level = level
                }

            }

            9 -> {
                if (loadLevelFromServer(7) == null) {
                    desktop = filterZeros(getFirstLevel())
                    this.level = 1
                } else {
                    desktop =
                        filterZeros(loadLevelFromServer(level)!!)
                    this.level = level
                }

            }
            else -> {
                desktop = filterZeros(getFirstLevel())
            }

        }
        return desktop
    }

    private fun getFirstLevel(): Array<IntArray> {
        var array = arrayOf(
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 3, 4, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 1, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
        )
        return array
    }

    private fun getSecondLevel(): Array<IntArray> {
        var array = arrayOf(
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
            intArrayOf(2, 0, 0, 4, 4, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 3, 3, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 1, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
        )
        return array
    }

    private fun getThirdLevel(): Array<IntArray> {
        var array = arrayOf(
            intArrayOf(0, 0, 2, 2, 2, 2, 2, 0, 0, 0),
            intArrayOf(2, 2, 2, 0, 0, 0, 2, 0, 0, 0),
            intArrayOf(2, 0, 1, 0, 0, 0, 2, 0, 0, 0),
            intArrayOf(2, 2, 2, 0, 3, 4, 2, 0, 0, 0),
            intArrayOf(2, 4, 2, 2, 3, 0, 2, 0, 0, 0),
            intArrayOf(2, 0, 2, 0, 4, 0, 2, 2, 0, 0),
            intArrayOf(2, 3, 0, 0, 3, 3, 4, 2, 0, 0),
            intArrayOf(2, 0, 0, 0, 4, 0, 0, 2, 0, 0),
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 0, 0),
        )
        return array
    }

    fun loadLevelFromFile(filename: String, context: Context): Array<IntArray> {
        var text = ""

        var fileInputStream: InputStream = context.assets.open(filename)
        var size = fileInputStream.available()
        var array: CharArray? = CharArray(size+1)
        try {
            var unicode: Int
            var index = 0
            while (fileInputStream.read().also { unicode = it } != -1) {
                val symbol = unicode.toChar()
                if ('0' <= symbol && symbol <= '4') {
                    array!![index] = symbol
                    index = index + 1
                } else if (symbol == '\n') {
                    array!![index] = 'A'
                    index += 1
                }

            }
            if (array!![index] != '\n') {
                array!![index] = 'A'
                index = index + 1
            }
            text = String(array, 0, index)
            array = null
            fileInputStream.close()
            return convertToArray(text)


        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        return convertToArray(text)
    }

    private fun convertToArray(text: String): Array<IntArray> {

        var row = 0
        for (i in 0 until text.length) {
            val symbol = text[i]
            if (symbol == 'A') {
                row = row + 1
            }
        }
        val array = Array(row, { IntArray(row) })
        var column = 0
        var indexRow = 0
        for (i in 0 until text.length) {
            val symbol = text[i]
            if (symbol == 'A') {
                array[indexRow] = IntArray(column)
                indexRow = indexRow + 1
                column = 0
            } else {
                column = column + 1
            }
        }
        row = 0
        column = 0
        for (i in 0 until text.length) {
            val symbol = text[i]
            if (symbol == 'A') {
                row = row + 1
                column = 0
            } else {
                val number = ("" + symbol).toInt()
                array[row][column] = number
                column = column + 1
            }
        }
        return array

    }

    private fun filterZeros(array: Array<IntArray>): Array<IntArray> {
        for (i in 0 until array.size) {
            for (j in 0 until array[i].size) {
                if (array[i][j] == 2) {
                    break
                } else {
                    array[i][j] = 5
                }
                for (i in 0 until array.size) {
                    for (j in array[i].size - 1 downTo 0) {
                        if (array[i][j] == 2) {
                            break
                        } else {
                            array[i][j] = 5
                        }
                    }
                }
            }
        }
        return array
    }


    private fun loadLevelFromServer(level: Int): Array<IntArray>? {
        val server: ConnectToServer = ConnectToServer(level)
        server.go()
        desktop = server.getMap()
        if (desktop != null) {
            return convertToArray(desktop!!)
        } else {
            viewer.showNoConnectionError()
            return null
        }

    }


    fun getCurrenLevel(): Int {
        return level
    }

}