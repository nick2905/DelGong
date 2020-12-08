package com.proyek.itdel.delgong.view.searchStory

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proyek.itdel.delgong.R
import com.proyek.itdel.delgong.model.local.Story
import com.proyek.itdel.delgong.view.detailStory.DetailStoryActivity
import kotlinx.android.synthetic.main.item_search_story.view.*
import java.util.*
import kotlin.collections.ArrayList

class SearchStoryAdapter(private val context: Context, private val storyList: ArrayList<Story>) :
    RecyclerView.Adapter<SearchStoryAdapter.SearchStoryViewHolder>() {
    var tempModelList = ArrayList<Story>()

    class SearchStoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(story: Story) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(story.imgPath)
                    .into(imgSearchCover)
                txtTitleSearchStory.text = story.title
                txtSearchAuthor.text = story.author
                itemView.setOnClickListener {
                    val intent = Intent(this.context, DetailStoryActivity::class.java)
                    intent.putExtra(DetailStoryActivity.EXTRA_STORY, story)
                    context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchStoryViewHolder =
        SearchStoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_search_story, parent, false
            )
        )

    override fun onBindViewHolder(holder: SearchStoryViewHolder, position: Int) {
        holder.bind(storyList[position])
    }

    override fun getItemCount(): Int = storyList.size

    fun filter(charText: String) {
        var charText = charText
        charText = charText.toLowerCase(Locale.getDefault())
        if (tempModelList.size < 1) {
            tempModelList.addAll(storyList)
        }
        storyList.clear()
        if (charText.isEmpty()) {
            storyList.addAll(tempModelList)
        } else {
            for (model in tempModelList) {
                if (model.title.toLowerCase(Locale.getDefault())
                        .contains(charText)
                ) {
                    storyList.add(model)
                } else if (model.title.toLowerCase(Locale.getDefault())
                        .contains(charText)
                ) {
                    storyList.add(model)
                }
            }
        }
        notifyDataSetChanged()
    }
}