package com.proyek.itdel.delgong.view.main.ageFragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proyek.itdel.delgong.R
import com.proyek.itdel.delgong.model.local.Story
import com.proyek.itdel.delgong.view.detailStory.DetailStoryActivity
import kotlinx.android.synthetic.main.item_story_categorize.view.*

class AgeAdapter(private val listAgeStory: List<Story>) :
    RecyclerView.Adapter<AgeAdapter.AgeViewHolder>() {
    class AgeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Story) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(item.imgPath)
                    .into(imgStory)
                titleStory.text = item.title
                authorStory.text = item.author
                itemView.setOnClickListener {
                    val intent = Intent(this.context, DetailStoryActivity::class.java)
                    intent.putExtra(DetailStoryActivity.EXTRA_STORY, item)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgeViewHolder =
        AgeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_story_categorize, parent, false
            )
        )

    override fun onBindViewHolder(holder: AgeViewHolder, position: Int) {
        holder.bind(listAgeStory[position])
    }

    override fun getItemCount(): Int = listAgeStory.size
}