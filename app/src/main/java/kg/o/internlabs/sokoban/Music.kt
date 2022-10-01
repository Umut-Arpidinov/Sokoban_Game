package kg.o.internlabs.sokoban

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool


class Music(context: Context) {

    private val soundPool: SoundPool
    private var mediaPlayer: MediaPlayer
    private var stepSound: Int = 0
    private var boxOnTargetSound: Int = 0
    private var finsishLevel: Int = 0
    private var connectionError: Int = 0

    init {
        soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        stepSound = soundPool.load(context, R.raw.pushbox, 1)
        mediaPlayer = MediaPlayer.create(context, R.raw.theme_song)
        boxOnTargetSound = soundPool.load(context, R.raw.success, 1)
        finsishLevel = soundPool.load(context, R.raw.sucesslong, 1)
        connectionError = soundPool.load(context, R.raw.windows_xp_error, 1)
        println("I am music ")
    }

    fun playStepSound() {
        soundPool.play(stepSound, 1F, 1F, 0, 0, 2F)
    }

    fun playSong() {
        mediaPlayer.start()
        mediaPlayer.setVolume(0.5f, 0.5f)
    }

    fun playSuccess() {
        soundPool.play(boxOnTargetSound, 1F, 1F, 0, 0, 1F)
    }

    fun stopSong() {
        mediaPlayer.pause()
    }

    fun playFinsishLevel() {
        soundPool.play(finsishLevel, 1F, 1F, 0, 0, 1F)
    }

    fun playError() {
        soundPool.play(connectionError, 1F, 1F, 0, 0, 1F)
    }
}



