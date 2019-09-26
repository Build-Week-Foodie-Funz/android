package com.saucefan.stuff.foodiefunbw.ui.viewedit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.saucefan.stuff.foodiefunbw.R
import kotlinx.android.synthetic.main.activity_restaurant.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant
import kotlinx.android.synthetic.main.activity_restaurant.recyclerView_rest
import kotlinx.android.synthetic.main.activity_restaurant.view.*

class RestaurantActivity : AppCompatActivity() {
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var viewModel: FoodieRestaurant
    lateinit var adapter: ViewRestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)




        recyclerView_rest.apply {
            layoutManager = LinearLayoutManager(this@RestaurantActivity)
           /* adapter = ViewRestAdapter()*/

        }


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
