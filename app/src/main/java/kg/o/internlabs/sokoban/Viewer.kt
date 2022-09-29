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
        music.playSong()
        println("I am viewer object")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
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
            R.id.stopmusic ->music.stopSong()

        }
        return super.onOptionsItemSelected(item)

    }
    fun showNoConnectionError(){
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage("Couldn't connect to server")
        dialog.setCancelable(false)
        dialog.setPositiveButton("Return to first level",DialogInterface.OnClickListener{
            dialog, which ->  finish()
        })
    }

    fun showWindDialog(){
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("")
    }


    fun update() {
        canvas?.update()
    }


}

