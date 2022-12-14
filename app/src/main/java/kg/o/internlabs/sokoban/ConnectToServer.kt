import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket


class ConnectToServer : Thread {
    private var desktop: String?
    private val level: Int

    constructor(level: Int) {
        this.level = level
        desktop = null
    }


    fun go() {
        start()
        try {
            join(500)
        } catch (e: Exception) {
            println(e)
        }
    }


    override fun run() {
        try {
            val socket: Socket = Socket("194.152.37.7", 5547)
            if (socket.isConnected) {
                val output = socket.getOutputStream()
                val out: PrintWriter = PrintWriter(output)
                out.println(level)
                out.flush()
                val input = socket.getInputStream()
                val inputStreamReader = InputStreamReader(input, "UTF-8")
                val getRespond = BufferedReader(inputStreamReader)
                val text = getRespond.readLine()
                desktop = text
            }

        } catch (e: IOException) {
            println(e)
        }

    }


    fun getMap(): String? {
        return desktop

    }

}


