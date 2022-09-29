import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket


import android.util.Log
import androidx.appcompat.app.AlertDialog
import kg.o.internlabs.sokoban.Levels
import kg.o.internlabs.sokoban.Viewer
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class ConnectToServer : Thread {
    private var desktop: String?
    private val level: Int

    constructor(level: Int) {
        this.level = level
        desktop = "default"
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
            println("In run")
            val socket: Socket = Socket("194.152.37.7", 5547)
            println(socket)
            val output = socket.getOutputStream()
            val out: PrintWriter = PrintWriter(output)
            out.println(level)
            out.flush()
            val input = socket.getInputStream()
            val inputStreamReader = InputStreamReader(input, "UTF-8")
            val getRespond = BufferedReader(inputStreamReader)
            val text = getRespond.readLine()
            desktop = text

        } catch (e: IOException) {

        }

    }


    fun getMap(): String? {
        return desktop

    }

}


