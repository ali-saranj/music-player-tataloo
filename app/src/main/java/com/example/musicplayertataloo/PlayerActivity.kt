package com.example.musicplayertataloo

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class PlayerActivity : AppCompatActivity() {

    lateinit var mediaPlayer: MediaPlayer
    lateinit var btn_play_pause: ImageView
    lateinit var image_music: ImageView
    lateinit var btn_next: ImageView
    lateinit var btn_befor: ImageView
    lateinit var tv_name_music: TextView
    lateinit var tv_derecshen: TextView
    lateinit var tv_progres: TextView
    lateinit var music_controler: SeekBar
    var progres = 0
    var darecshen = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        findID()

        Picasso.get().load("https://ingoogle.ir/wp-content/uploads/2021/11/Amir-Tataloo-Gedaei.jpg").into(image_music)

        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(this, Uri.parse("https://dl.ingoogle.ir/tataloo/1/Amir%20Tataloo%20-%20Gedaei%20128.mp3?_=1"))
        mediaPlayer.prepare()
        darecshen = mediaPlayer.duration.toInt()
        tv_derecshen.text = "${(darecshen/60000)} : ${(darecshen/600)%100}"
        music_controler.max = darecshen

        Thread(Runnable {
            while (true){
              music_controler.progress = mediaPlayer.currentPosition
                if (mediaPlayer.isPlaying){
                    btn_play_pause.setImageResource(R.drawable.play)
                }else{
                    btn_play_pause.setImageResource(R.drawable.pause)
                }
                Thread.sleep(1000)
            }
        }).start()

        music_controler.setOnSeekBarChangeListener(object: OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                progres =p1
//                tv_progres.text = "${((progres)/60000)} : ${((progres)/60000)%100}"

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                mediaPlayer.seekTo(progres)
                mediaPlayer.pause()
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
                mediaPlayer.seekTo(progres)
                mediaPlayer.start()
            }
        })

        btn_play_pause.setOnClickListener {
            if (mediaPlayer.isPlaying){
                mediaPlayer.pause()
                btn_play_pause.setImageResource(R.drawable.play)
            }else{
                mediaPlayer.start()
                btn_play_pause.setImageResource(R.drawable.pause)
            }
            it.startAnimation(AnimationUtils.loadAnimation(applicationContext,android.R.anim.fade_in))
        }

        btn_next.setOnClickListener {
            progres+=10000
            mediaPlayer.seekTo(progres)
            it.startAnimation(AnimationUtils.loadAnimation(applicationContext,android.R.anim.fade_in))
        }

        btn_befor.setOnClickListener {
            progres -= 10000
            mediaPlayer.seekTo(progres)
            it.startAnimation(AnimationUtils.loadAnimation(applicationContext, android.R.anim.fade_in))
        }

    }

    private fun findID() {
        btn_befor = findViewById(R.id.btn_befor)
        btn_next = findViewById(R.id.btn_next)
        btn_play_pause = findViewById(R.id.btn_play_pause)
        music_controler = findViewById(R.id.music_controler)
        tv_name_music = findViewById(R.id.tv_name_music)
        tv_derecshen = findViewById(R.id.tv_drecshen)
        tv_progres = findViewById(R.id.tv_progres)
        image_music = findViewById(R.id.image_music)
    }
}


