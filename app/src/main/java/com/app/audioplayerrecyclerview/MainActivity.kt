package com.app.audioplayerrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf<PlayerModel>()
        list.add(PlayerModel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",false,false))
        list.add(PlayerModel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",false,false))
        list.add(PlayerModel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",false,false))
        list.add(PlayerModel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",false,false))
        list.add(PlayerModel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",false,false))
        list.add(PlayerModel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",false,false))
        list.add(PlayerModel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",false,false))
        list.add(PlayerModel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",false,false))
        list.add(PlayerModel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",false,false))
        list.add(PlayerModel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",false,false))
        list.add(PlayerModel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",false,false))
        list.add(PlayerModel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",false,false))
        list.add(PlayerModel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",false,false))
        list.add(PlayerModel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",false,false))
        list.add(PlayerModel("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",false,false))

        recyclerView.layoutManager  = LinearLayoutManager(this)

        recyclerView.adapter =  RecyclerViewAdapter(this,list)

    }
}
