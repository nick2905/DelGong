package com.proyek.itdel.delgong.view.play

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.proyek.itdel.delgong.R
import com.proyek.itdel.delgong.model.local.PartStory
import kotlinx.android.synthetic.main.activity_playing_story.*
import kotlinx.coroutines.Runnable
import kotlin.math.ceil
import kotlin.math.roundToInt
import kotlin.math.roundToLong


class PlayingStory : AppCompatActivity(), Runnable {
    var mediaPlayer = MediaPlayer()
    var wasPlaying = false
    private lateinit var nameFileAudio: String
    //private var nameFileAudio = "mama_antelope/mama_antelope.mp3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing_story)

        var story = intent.getParcelableArrayListExtra<PartStory>("listPartStory")!!
        var index = intent.getIntExtra("indexStory", 1)
        var author = intent.getStringExtra("authorPartStory")
        var title = intent.getStringExtra("titlePathStory")

        val listIndexTotal = story.size

        nameFileAudio = story[index - 1].pathAudio
        txtStoryPart.text = story[index - 1].storiesValue
        imgPartStory.setImageResource(story[index - 1].imgPartPath)

        authorNamePart.text = author
        txtTitleStory.text = title

        fab.setOnClickListener {
            playSong()
        }

        imageButton.setOnClickListener {
            onBackPressed()
            finish()
        }

        previousBtn.setOnClickListener {
            if (index == 1) {
                Toast.makeText(this@PlayingStory, "This is the first part", Toast.LENGTH_SHORT)
                    .show()
            } else {
                index -= 1
                val intent = Intent(this, PlayingStory::class.java)
                intent.putParcelableArrayListExtra("listPartStory", story)
                intent.putExtra("indexStory", index)
                intent.putExtra("titlePathStory", title)
                intent.putExtra("authorPartStory", author)
                startActivity(intent)
                this.overridePendingTransition(0, 0);
                finish()
            }
        }

        nextBtn.setOnClickListener {
            if (index == listIndexTotal) {
                Toast.makeText(this@PlayingStory, "This is the last part", Toast.LENGTH_SHORT)
                    .show()
            } else {
                index += 1
                val intent = Intent(this, PlayingStory::class.java)
                intent.putParcelableArrayListExtra("listPartStory", story)
                intent.putExtra("indexStory", index)
                intent.putExtra("titlePathStory", title)
                intent.putExtra("authorPartStory", author)
                startActivity(intent)
                this.overridePendingTransition(0, 0);
                finish()
            }
        }


        seekBar?.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                seekBarHint.visibility = View.VISIBLE
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromTouch: Boolean) {
                //seekBarHintEnd.text = seekBar.max
                //seekBarHint.visibility = View.VISIBLE
                seekBarHint.visibility = View.INVISIBLE
                val x = ceil(progress / 1000f.toDouble()).toInt()

                if (x < 10) seekBarHint.text = "0:0$x" else seekBarHint.text = "0:$x"

                val percent = progress / seekBar.max.toDouble()
                val offset = seekBar.thumbOffset
                val seekWidth = seekBar.width
                val value = (percent * (seekWidth - 2 * offset)).roundToInt().toInt()
                val labelWidth = seekBarHint.width
                seekBarHint.x = (offset + seekBar.x + value - (percent * offset).roundToLong()
                        - (percent * labelWidth / 2).roundToInt())

                if (progress > 0 && !mediaPlayer.isPlaying) {
                    clearMediaPlayer()
                    fab.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@PlayingStory,
                            android.R.drawable.ic_media_play
                        )
                    )
                    this@PlayingStory.seekBar!!.progress = 0
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.seekTo(seekBar.progress)
                }
            }
        })

    }

    private fun playSong() {
        try {
            if (mediaPlayer.isPlaying) {
                clearMediaPlayer()
                seekBar!!.progress = 0
                wasPlaying = true
                fab!!.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@PlayingStory,
                        android.R.drawable.ic_media_play
                    )
                )
            }
            if (!wasPlaying) {
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer()
                }
                fab!!.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@PlayingStory,
                        R.drawable.baseline_stop_white_48dp
                    )
                )
                val descriptor = assets.openFd(nameFileAudio)
                mediaPlayer.setDataSource(
                    descriptor.fileDescriptor,
                    descriptor.startOffset,
                    descriptor.length
                )
//                var totalTime = createTimeLabel(mediaPlayer.duration)
//                seekBarHintEnd.text = totalTime

                descriptor.close()
                mediaPlayer.prepare()
                mediaPlayer.setVolume(0.5f, 0.5f)
                mediaPlayer.isLooping = false
                seekBar!!.max = mediaPlayer.duration
                mediaPlayer.start()
                Thread(this).start()
            }
            wasPlaying = false
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun run() {
        var currentPosition = mediaPlayer.currentPosition
        val total = mediaPlayer.duration
        while (mediaPlayer.isPlaying && currentPosition < total) {
            currentPosition = try {
                Thread.sleep(1000)
                mediaPlayer.currentPosition
            } catch (e: InterruptedException) {
                return
            } catch (e: Exception) {
                return
            }
            seekBar!!.progress = currentPosition
        }
    }

    fun createTimeLabel(duration: Int): String? {
        var timeLabel: String? = ""
        val min = duration / 1000 / 60
        val sec = duration / 1000 % 60
        timeLabel += "$min:"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec
        return timeLabel
    }

    override fun onDestroy() {
        super.onDestroy()
        clearMediaPlayer()
    }

    private fun clearMediaPlayer() {
        mediaPlayer.stop()
        mediaPlayer.reset()
        //mediaPlayer = null
    }
}