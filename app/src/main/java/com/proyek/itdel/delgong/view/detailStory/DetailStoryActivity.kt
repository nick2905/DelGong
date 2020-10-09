package com.proyek.itdel.delgong.view.detailStory

import android.content.Context
import android.content.res.TypedArray
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyek.itdel.delgong.R
import com.proyek.itdel.delgong.model.local.PartStory
import com.proyek.itdel.delgong.model.local.Story
import kotlinx.android.synthetic.main.activity_detail_story.*
import java.lang.reflect.Field


class DetailStoryActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_STORY = "extra_story"
    }

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_story)

        val story = intent.getParcelableExtra(EXTRA_STORY) as Story
        val dataPartStory: List<PartStory> = story.partStory

        viewAdapter = DetailStoryAdapter(story, dataPartStory)
        viewManager = LinearLayoutManager(this)


        itemImgStory.setImageResource(story.imgPath)
        textView2.text = story.title
        txtAuthor.text = story.author

        //Toast.makeText(this, pathListAudio, Toast.LENGTH_SHORT).show()

        rvChaptersStory.apply{
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = viewManager
            var itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(getDrawable(R.drawable.divider)!!)
            addItemDecoration(itemDecoration)
        }
    }
}