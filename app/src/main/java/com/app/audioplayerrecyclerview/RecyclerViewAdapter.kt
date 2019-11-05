package com.app.audioplayerrecyclerview

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception
import java.util.*

class RecyclerViewAdapter(
    context: AppCompatActivity,
    data: MutableList<PlayerModel>
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    val context: AppCompatActivity
    val data: MutableList<PlayerModel>

    val mediaPlayer: MediaPlayer


    init {
        this.context = context
        this.data = data
        this.mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        this.mediaPlayer.setOnCompletionListener {
            data.get(previousPostion).isLoading = false
            data.get(previousPostion).isPlaying = false
            notifyItemChanged(previousPostion)
            it.reset()


        }
        this.mediaPlayer.setOnPreparedListener {
            it.start()
            data.get(previousPostion).isLoading = false
            data.get(previousPostion).isPlaying = true
            notifyItemChanged(previousPostion)
        }
    }

    val PLAYING = 101
    val LOADING = 103
    val NORMAL = 102
    var previousPostion = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == PLAYING)
            ViewHolder(inflater.inflate(R.layout.row_stop, parent, false))
        else if (viewType == LOADING)
            ViewHolder(inflater.inflate(R.layout.row_loading, parent, false))
        else
            ViewHolder(inflater.inflate(R.layout.row_start, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.play.setOnClickListener {
            if (holder.itemViewType == PLAYING) {
                pauseMedia(position)
            } else if (holder.itemViewType == NORMAL) {
                if (previousPostion != -1)
                    pauseMedia(previousPostion)

                previousPostion = position

                playMedia(position)


            }

        }


        if (holder.itemViewType == PLAYING) {
            holder.seekBar.isEnabled = true
            holder.seekBar.max = mediaPlayer.duration / 1000
            val timer = Timer()
            timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    try {
                        holder.seekBar.setProgress(mediaPlayer.getCurrentPosition() / 1000)
                    } catch (e: Exception) {

                    }

                }
            }, 0, 1000)

            holder.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    if (p2)
                        mediaPlayer.seekTo(p1 * 1000)
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }

            })

        } else if (holder.itemViewType == NORMAL || holder.itemViewType == LOADING) {
            holder.seekBar.setOnTouchListener { p0, p1 -> true }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun getItemViewType(position: Int): Int {
        return if (data.get(position).isPlaying)
            PLAYING
        else if (data.get(position).isLoading)
            LOADING
        else
            NORMAL
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var play: TextView = view.findViewById(R.id.button)
        var seekBar: SeekBar = view.findViewById(R.id.seekBar)
    }


    fun playMedia(
        pos: Int
    ) {
        if (mediaPlayer.isPlaying) {
            pauseMedia(pos)
        }
        mediaPlayer.reset()
        mediaPlayer.setDataSource(context, Uri.parse(data.get(pos).audioURL))
        data.get(pos).isLoading = true
        notifyItemChanged(pos)
        mediaPlayer.prepareAsync()


    }

    fun pauseMedia(pos: Int) {
        data.get(pos).isPlaying = false
        data.get(pos).isLoading = false
        notifyItemChanged(pos)
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop()
            mediaPlayer.reset()

        }
    }
}

