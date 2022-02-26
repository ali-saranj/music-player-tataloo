package com.example.musicplayertataloo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var musics:ArrayList<Music>
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        musics = ArrayList()

        val queue = Volley.newRequestQueue(this)
        val url = "https://raw.githubusercontent.com/ali-saranj/music-player-tataloo/master/README.md"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val jsonArray = JSONArray(response)
                for (a in 0 until jsonArray.length()){
                    musics.add(Music(jsonArray.getJSONObject(a).getString("name"),jsonArray.getJSONObject(a).getString("image"),jsonArray.getJSONObject(a).getString("sound")))
                    startActivity(Intent(this,MenuActivity::class.java))
                    finish()
                }
            },
            { Toast.makeText(applicationContext, "Check Internet", Toast.LENGTH_SHORT).show() })
        queue.add(stringRequest)
    }
}