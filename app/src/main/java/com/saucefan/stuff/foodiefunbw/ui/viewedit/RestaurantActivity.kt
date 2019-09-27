package com.saucefan.stuff.foodiefunbw.ui.viewedit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.saucefan.stuff.foodiefunbw.R
import kotlinx.android.synthetic.main.activity_restaurant.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant
import com.saucefan.stuff.foodiefunbw.viewmodel.FoodieEntryViewModel
import kotlinx.android.synthetic.main.activity_restaurant.recyclerView_rest
import kotlinx.android.synthetic.main.activity_restaurant.view.*
import androidx.lifecycle.ViewModelProvider
import android.R



class RestaurantActivity : AppCompatActivity() {
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var viewModel: FoodieEntryViewModel
    lateinit var adapter: ViewRestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.saucefan.stuff.foodiefunbw.R.layout.activity_restaurant)
        viewModel = ViewModelProvider(this).get(FoodieEntryViewModel::class.java)


setupRecyclerView(viewModel)
        recyclerView_rest.apply {
            layoutManager = LinearLayoutManager(this@RestaurantActivity)
           /* adapter = ViewRestAdapter()*/

        }


    }
    fun setupRecyclerView(viewModel:FoodieEntryViewModel) {
        recyclerView_rest.layoutManager = LinearLayoutManager(this) // or grid or whatever
        recyclerView_rest.setHasFixedSize(true) // this actually may need to be false

        var adapter = ViewRestAdapter()

        recyclerView_rest.adapter = adapter


        viewModel..observe(this, Observer<List<fOODIEENTRY OR RESTOBJ>> {
            updateRecyclerView(adapter, it as MutableList<fOODIEENTRY OR RESTOBJ>)
        })

    }*/



    fun updateRecyclerView(adapter: ViewRestAdapter, list: MutableList<String>) {
        adapter.submitList(list as List<String>)
        adapter.notifyDataSetChanged()
    }
  /*  viewModel.returnAllItems().observe(this, Observer<List<FoodieEntry>> {
        updateRecyclerView(adapter, it as MutableList<FoodieEntry>)
    })
    viewModel.getRestListById(restobj,restid).observe(this, Observer<List<FoodieEntry>> {
        updateRecyclerView(adapter, it as MutableList<FoodieEntry>)
    })


    /*private lateinit var viewModel: FoodieEntryViewModel



    THIS GOES IN ONCREATE()
    viewModel = ViewModelProviders.of(this).get(FoodieEntryViewModel::class.java)
    setupRecyclerView(viewModel)





    fun setupRecyclerView(viewModel:FoodieEntryViewModel) {
        ~~RECYCLEVIEW ID~~.layoutManager = LinearLayoutManager(this) // or grid or whatever
        ~~RECYCLEVIEW ID~~.setHasFixedSize(true) // this actually may need to be false

        var adapter = ~~~RECYCLERVIEWCLASS~~()

        ~~RECYCLEVIEW ID~~.adapter = adapter


        viewModel.~~~APPROPRIATEVIEWMODEL FUNCTION THAT RETURNS LIVEDATA HERE(ID OR WHATEVER PARAMS HERE)~~~~.observe(this, Observer<List<fOODIEENTRY OR RESTOBJ>> {
            updateRecyclerView(adapter, it as MutableList<fOODIEENTRY OR RESTOBJ>)
        })

    }*/



    fun updateRecyclerView(adapter: ViewRestAdapter, list: MutableList<String>) {
        adapter.submitList(list as List<String>)
        adapter.notifyDataSetChanged()
    }*/
}
