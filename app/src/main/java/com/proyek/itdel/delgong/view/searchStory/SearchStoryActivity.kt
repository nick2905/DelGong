package com.proyek.itdel.delgong.view.searchStory

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyek.itdel.delgong.R
import com.proyek.itdel.delgong.data.StoriesData
import com.proyek.itdel.delgong.model.local.Story
import kotlinx.android.synthetic.main.activity_search_story.*


class SearchStoryActivity : AppCompatActivity() {
    private lateinit var adapterSearch: SearchStoryAdapter
    private var listStory: ArrayList<Story> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_story)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //actionBar!!.title = ""
        supportActionBar?.setDisplayShowTitleEnabled(false)

        listStory.addAll(StoriesData.allStories)

        adapterSearch = SearchStoryAdapter(this, listStory)
        rvSearchStory.adapter = adapterSearch
        rvSearchStory.layoutManager = LinearLayoutManager(this)



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater

        inflater.inflate(R.menu.option_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)

        //Auto Search Click
        val searchMenuItem = menu.findItem(R.id.search)
        searchMenuItem.expandActionView()

        searchMenuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
                onBackPressed()
                return true
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            /*
            Gunakan method ini ketika search selesai atau OK
             */
            override fun onQueryTextSubmit(query: String): Boolean {
                if(TextUtils.isEmpty(query)){
                    adapterSearch.filter("")
                }else{
                    adapterSearch.filter(query)
                }
                //Toast.makeText(this@SearchStoryActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
             */
            override fun onQueryTextChange(newText: String): Boolean {
                if(TextUtils.isEmpty(newText)){
                    adapterSearch.filter("")
                }else{
                    adapterSearch.filter(newText)
                }
                return true
            }
        })
        return true
    }
}