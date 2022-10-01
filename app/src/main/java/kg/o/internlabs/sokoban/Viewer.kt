package kg.o.internlabs.sokoban

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog


class Viewer : AppCompatActivity {
    private val controller: Controller
    private var canvas: CanvasSokoban?
    private val model: Model
    private lateinit var music: Music

    constructor() {
        controller = Controller(this)
        model = controller.getModel()
        canvas = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        canvas = CanvasSokoban(this, model)
        music = Music(this)
        setContentView(canvas)
        canvas?.setOnTouchListener(controller)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.level1 -> controller.chooseLevel(1)
            R.id.level2 -> controller.chooseLevel(2)
            R.id.level3 -> controller.chooseLevel(3)
            R.id.level4 -> controller.chooseLevel(4)
            R.id.level5 -> controller.chooseLevel(5)
            R.id.level6 -> controller.chooseLevel(6)
            R.id.level7 -> controller.chooseLevel(7)
            R.id.level8 -> controller.chooseLevel(8)
            R.id.level9 -> controller.chooseLevel(9)
            R.id.startmusic -> music.playSong()
            R.id.stopmusic -> music.stopSong()

        }
        return super.onOptionsItemSelected(item)

    }

    fun showNoConnectionError() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Sorry, couldn't connect to the server.\nTry it later.")
            .setCancelable(false)
            .setPositiveButton(
                "Ok",
                DialogInterface.OnClickListener { dialog, which -> controller.sendToModel("connection") })
        val alert = dialogBuilder.create()
        alert.setTitle("Connection Error")
        alert.show()
        music.stopSong()
        music.playError()
    }

    fun showWindDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Congratulations! You WON!")
            .setCancelable(false)
            .setPositiveButton(
                "Next level",
                DialogInterface.OnClickListener { dialog, which -> controller.sendToModel("update") })
        val alert = dialogBuilder.create()
        alert.setTitle("End of the level")
        alert.show()
        music.playSuccess()
    }

    fun showEndOfGame() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Congratulations! You solved all levels!\nPress ok to restart.")
            .setCancelable(false)
            .setPositiveButton(
                "Ok",
                DialogInterface.OnClickListener { dialog, which -> controller.sendToModel("end") })
        val alert = dialogBuilder.create()
        alert.setTitle("End of game")
        alert.show()
        music.stopSong()
        music.playFinsishLevel()
    }

    fun update() {
        canvas?.update()
    }


}

