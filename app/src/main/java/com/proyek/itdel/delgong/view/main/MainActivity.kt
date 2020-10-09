package com.proyek.itdel.delgong.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyek.itdel.delgong.R
import com.proyek.itdel.delgong.data.StoriesData
import com.proyek.itdel.delgong.model.local.Story
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val listFamousStory: ArrayList<Story> = arrayListOf()
    private lateinit var adapterFamousStory: FamousStoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        rvFamousStory.setHasFixedSize(true)
        listFamousStory.addAll(StoriesData.listData)
        shoRecyclerFamousStory()
    }


    private fun shoRecyclerFamousStory(){
        rvFamousStory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterFamousStory = FamousStoryAdapter(listFamousStory)
        rvFamousStory.adapter = adapterFamousStory
    }
}