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
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant
import com.saucefan.stuff.foodiefunbw.Model.room.EntryMockData.restList
import com.saucefan.stuff.foodiefunbw.R
import kotlinx.android.synthetic.main.activity_prefs.view.*
import kotlinx.android.synthetic.main.rest_recyclerview_item_row.view.*

class ViewRestAdapter: androidx.recyclerview.widget.ListAdapter<FoodieRestaurant,ViewRestAdapter.ViewHolder>(DIFF_CALLBACK){

    //this is
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FoodieRestaurant>() {
            override fun areItemsTheSame(oldItem: FoodieRestaurant, newItem: FoodieRestaurant): Boolean {
                return oldItem.restId == newItem.restId
            }

            override fun areContentsTheSame(oldItem: FoodieRestaurant, newItem: FoodieRestaurant): Boolean {
                return oldItem.restId == newItem.restId &&
                        oldItem.restName == newItem.restName &&
                        oldItem.restHours == newItem.restHours &&
                        oldItem.restRating == newItem.restRating
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val restName = view.rest_name_view
        /* tv_cuisine_type.text
         tv_item_name.text
         tv_item_price.text
         tv_rating.text
         tv_review.text*/


    }
    fun getItemAt(position: Int): FoodieRestaurant {
        return getItem(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.rest_recyclerview_item_row, parent, false) as View)

    }



    override fun onBindViewHolder(holder: ViewRestAdapter.ViewHolder, position: Int) {
        val restList: FoodieRestaurant
        if (position != RecyclerView.NO_POSITION) {
            restList=getItemAt(position)
            holder.restName.text = restList.restName
        }


    }








}

