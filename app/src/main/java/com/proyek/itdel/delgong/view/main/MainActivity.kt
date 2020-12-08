package com.proyek.itdel.delgong.view.main

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.proyek.itdel.delgong.R
import com.proyek.itdel.delgong.data.StoriesData
import com.proyek.itdel.delgong.model.local.Story
import com.proyek.itdel.delgong.view.main.ageFragment.MainAgeAdapter
import com.proyek.itdel.delgong.view.searchStory.SearchStoryActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val titlesCustom = arrayOf("Short", "Medium", "Long")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabCustom.tabGravity = TabLayout.GRAVITY_FILL
        tabCustom.tabTextColors = ColorStateList.valueOf(Color.parseColor("#333333"))

        searchHome.setOnClickListener {
            startActivity(Intent(this, SearchStoryActivity::class.java))
        }

        init()

    }


    private fun init() {
        viewPagerAge.adapter = MainAgeAdapter(this)

        TabLayoutMediator(
            tabCustom,
            viewPagerAge,
        )
        { tab, position ->
            tab.text = titlesCustom[position]
        }.attach()
    }
}