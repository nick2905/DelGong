package com.proyek.itdel.delgong.view.main.ageFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyek.itdel.delgong.R
import com.proyek.itdel.delgong.data.StoriesData
import com.proyek.itdel.delgong.model.local.Story
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment : Fragment() {

    private val listFirstStory: ArrayList<Story> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.rvShortStories.setHasFixedSize(true)
        listFirstStory.addAll(StoriesData.allStories.filter { it.type == "short" })
        showData(view)
    }

    private fun showData(view: View){
        view.rvShortStories.layoutManager = GridLayoutManager(this.context, 2)
        val listFirstAdapter = AgeAdapter(listFirstStory)
        view.rvShortStories.adapter = listFirstAdapter
    }
}