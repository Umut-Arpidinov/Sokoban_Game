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

    init {
        soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        stepSound = soundPool.load(context, R.raw.pushbox, 1)
        mediaPlayer = MediaPlayer.create(context, R.raw.theme_song)
        boxOnTargetSound = soundPool.load(context, R.raw.success, 1)
    }

    fun playStepSound() {
        soundPool.play(stepSound, 1F, 1F, 0, 0, 2F)
    }

    fun playSong() {
        mediaPlayer.start()
        mediaPlayer.setVolume(0.5f, 0.5f)
    }

    fun playSuccess() {
        soundPool.play(boxOnTargetSound, 1F, 1F, 0, 0, 2F)
    }

    fun stopSong(){
        mediaPlayer.pause()
    }


}
