package com.proyek.itdel.delgong.view.detailStory

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proyek.itdel.delgong.R
import com.proyek.itdel.delgong.model.local.PartStory
import com.proyek.itdel.delgong.model.local.Story
import com.proyek.itdel.delgong.view.play.PlayingStory
import kotlinx.android.synthetic.main.item_part_story.view.*

class DetailStoryAdapter(private val detailStory: Story, private val stories: List<PartStory>) :
    RecyclerView.Adapter<DetailStoryAdapter.DetailViewHolder>() {

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: PartStory, partId : String) {
            with(itemView) {
                partName.text = partId

                itemView.setOnClickListener {
                    val intent = Intent(this.context, PlayingStory::class.java)
                    intent.putParcelableArrayListExtra("listPartStory", detailStory.partStory)
                    intent.putExtra("indexStory", item.indexStory)
                    intent.putExtra("titlePathStory", detailStory.title)
                    intent.putExtra("authorPartStory", detailStory.author)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder =
        DetailViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_part_story, parent, false
            )
        )

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(stories[position], "Part ${position+1}")
    }

    override fun getItemCount(): Int = stories.size
}