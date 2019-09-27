package com.saucefan.stuff.foodiefunbw.ui.viewedit


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.R
import com.saucefan.stuff.foodiefunbw.viewmodel.FoodieEntryViewModel
import kotlinx.android.synthetic.main.activity_restaurant.*
import kotlinx.android.synthetic.main.activity_reviews_by_rest.*

class ReviewsByRest : AppCompatActivity() {
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var viewModel: FoodieEntryViewModel
    lateinit var adapter: ViewReviewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         supportActionBar?.hide()
        setContentView(R.layout.activity_reviews_by_rest)


        viewModel = ViewModelProviders.of(this).get(FoodieEntryViewModel::class.java)
        // ViewModelProvider(this).get(FoodieEntryViewModel::class.java)
        setupRecyclerView(viewModel)

    }

    fun setupRecyclerView(viewModel: FoodieEntryViewModel) {
        recyclerView_review.layoutManager = LinearLayoutManager(this) // or grid or whatever
        recyclerView_review.setHasFixedSize(true) // this actually may need to be false

        var adapter = ViewReviewAdapter()

        recyclerView_review.adapter = adapter


        viewModel.returnReviewByRestName("mcdonalds").observe(this, Observer<List<FoodieEntry>> {
            updateRecyclerView(adapter, it as MutableList<FoodieEntry>)
        })

    }


    fun updateRecyclerView(adapter: ViewReviewAdapter, list: MutableList<FoodieEntry>) {
        adapter.submitList(list as List<FoodieEntry>)
        adapter.notifyDataSetChanged()
    }
}