package com.proyek.itdel.delgong.view.main.ageFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.proyek.itdel.delgong.R
import com.proyek.itdel.delgong.data.StoriesData
import com.proyek.itdel.delgong.model.local.Story
import kotlinx.android.synthetic.main.fragment_second.view.*

class SecondFragment : Fragment() {

    private val listMediumStory: ArrayList<Story> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.rvMediumStories.setHasFixedSize(true)
        listMediumStory.addAll(StoriesData.allStories.filter { it.type == "medium" })
        showData(view)
    }

    private fun showData(view: View) {
        view.rvMediumStories.layoutManager = GridLayoutManager(this.context, 2)
        val listMediumAdapter = AgeAdapter(listMediumStory)
        view.rvMediumStories.adapter = listMediumAdapter
    }
}