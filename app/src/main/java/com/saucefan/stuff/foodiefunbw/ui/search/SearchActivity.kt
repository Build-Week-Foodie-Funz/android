package com.saucefan.stuff.foodiefunbw.ui.search

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.RoomDatabase
import com.saucefan.stuff.foodiefunbw.FoodieFun
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant
import com.saucefan.stuff.foodiefunbw.Model.room.EntryDatabase
import com.saucefan.stuff.foodiefunbw.Model.room.EntryMockData
import com.saucefan.stuff.foodiefunbw.Model.room.RoomDao
import com.saucefan.stuff.foodiefunbw.R
import com.saucefan.stuff.foodiefunbw.viewmodel.FoodieEntryViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SearchActivity : AppCompatActivity() {

    private var entryDatabase: EntryDatabase? = null
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val actionBar = supportActionBar
        actionBar!!.title = "Click the icon to search by restaurant"
        entryDatabase = EntryDatabase.getInstance(this)!!


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_searchview, menu)
        val searchItem = menu!!.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView
        searchView.setSubmitButtonEnabled(true)
        searchView.setQueryHint("Search Entries By Resturant")
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                getNamesFromDb(newText)
                return true
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                getNamesFromDb(query)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
    private fun getNamesFromDb(searchText: String) {
        val searchTextQuery = "%$searchText%"
        entryDatabase!!.RoomDao().getRestName(searchTextQuery).observe(this@SearchActivity, object : Observer<List<FoodieRestaurant>>{
            override fun onChanged(chapter: List<FoodieRestaurant>?) {
                if (chapter == null) {
                    return
                }
                val adapter = SearchAdapter(
                    this@SearchActivity,
                    R.layout.search_item,
                    chapter
                )
                lvSearchResult.adapter = adapter
            }
        })
    }}


