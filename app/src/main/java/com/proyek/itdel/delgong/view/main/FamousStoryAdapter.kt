package com.proyek.itdel.delgong.view.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proyek.itdel.delgong.R
import com.proyek.itdel.delgong.model.local.Story
import com.proyek.itdel.delgong.view.detailStory.DetailStoryActivity
import com.proyek.itdel.delgong.view.play.PlayingStory
import kotlinx.android.synthetic.main.item_famous_story.view.*

class FamousStoryAdapter(private val listFamousStory: List<Story>) :
    RecyclerView.Adapter<FamousStoryAdapter.FamousStoryViewHolder>() {
    class FamousStoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Story) {
            with(itemView) {
                //glide
                Glide.with(itemView.context)
                    .load(item.imgPath)
                    .into(itemImgStory)
                storyTitle.text = item.title
                itemView.setOnClickListener {
                    val intent = Intent(this.context, DetailStoryActivity::class.java)
                    intent.putExtra(DetailStoryActivity.EXTRA_STORY, item)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FamousStoryViewHolder = FamousStoryViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_famous_story, parent, false
        )
    )

    override fun onBindViewHolder(holder: FamousStoryViewHolder, position: Int) {
        holder.bind(listFamousStory[position])
    }

    override fun getItemCount(): Int = listFamousStory.size

}