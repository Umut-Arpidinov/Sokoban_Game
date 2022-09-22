package kg.o.internlabs.sokoban

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.view.View
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStream
import java.io.InputStreamReader

class Levels {
    private var prefixFileName: String
    private var endFileName: String
    private var level: Int
    private val viewer: Viewer

    constructor(viewer: Viewer) {
        this.viewer = viewer
        prefixFileName = "level"
        endFileName = ".sok"
        level = 1
    }


    fun nextLevel(): Array<IntArray> {
        var desktop: Array<IntArray>
        when (level) {
            1 -> desktop = getFirstLevel()
            2 -> desktop = getSecondLevel()
            3 -> desktop = getThirdLevel()
            4 -> desktop = loadLevelFromFile(prefixFileName + level + endFileName, viewer)
            5 ->  desktop = loadLevelFromFile(prefixFileName + level + endFileName, viewer)
            6 -> desktop = loadLevelFromFile(prefixFileName + level + endFileName, viewer)
            else -> {
                level = 1
                desktop = getFirstLevel()
            }

        }
        level = level + 1
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
        var array: CharArray = CharArray(size)
        try {
            var unicode: Int
            var index = 0
            while (fileInputStream.read().also { unicode = it } != -1) {
                val symbol = unicode.toChar()
                if ('0' <= symbol && symbol <= '4') {
                    array[index] = symbol
                    index = index + 1
                } else if (symbol == '\n') {
                    array[index] = 'A'
                    index += 1
                }

            }
            if (array[index] != '\n') {
                array[index] = 'A'
                index = index + 1
            }
            text = String(array, 0, index)
            return convertToArray(text)
            fileInputStream.close()

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        return convertToArray(text)
    }

    private fun convertToArray(text: String):Array<IntArray> {
        var row = 0
        for (i in 0 until text.length) {
            val symbol = text[i]
            if (symbol == 'A') {
                row = row + 1
            }
        }
        val array = Array(row,{IntArray(row)})
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
                array[row]!![column] = number
                column = column + 1
            }
        }
        return array

    }


    fun getLevel(): Int {
        return level - 1
    }
}