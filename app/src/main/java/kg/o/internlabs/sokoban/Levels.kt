package kg.o.internlabs.sokoban

class Levels {
    private var level: Int

    constructor() {
        level = 1
    }

    fun nextLevel(): Array<IntArray> {
        var desktop: Array<IntArray>
        when (level) {
            1 -> desktop = getFirstLevel()
            2 -> desktop = getSecondLevel()
            3 -> desktop = getThirdLevel()
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

    fun getLevel(): Int {
        return level - 1
    }
}