package com.saucefan.stuff.foodiefunbw.ui.viewedit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant
import com.saucefan.stuff.foodiefunbw.R
import kotlinx.android.synthetic.main.activity_prefs.view.*
import kotlinx.android.synthetic.main.rest_recyclerview_item_row.view.*

class ViewRestAdapter(context: Context, val restList: List<FoodieRestaurant>): RecyclerView.Adapter<ViewRestAdapter.ViewHolder>(){


    val context = context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val restName = view.rest_name_view
        /* tv_cuisine_type.text
         tv_item_name.text
         tv_item_price.text
         tv_rating.text
         tv_review.text*/


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_view_rest, parent, false) as View)
    }


    override fun getItemCount(): Int {
        return restList.size
    }

    override fun onBindViewHolder(holder: ViewRestAdapter.ViewHolder, position: Int) {
        val restList: FoodieRestaurant = restList[position]
        holder.restName.text = restList.restName

    }








}

