package com.saucefan.stuff.foodiefunbw.ui.viewedit


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.R
import kotlinx.android.synthetic.main.review_recyclerview_item_row.view.*

class ViewReviewAdapter: androidx.recyclerview.widget.ListAdapter<FoodieEntry,ViewReviewAdapter.ReviewViewHolder>(DIFF_REVIEW_CALLBACK){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
            return ReviewViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.review_recyclerview_item_row, parent, false) as View)

        }



    //this is
    companion object {
        private val DIFF_REVIEW_CALLBACK = object : DiffUtil.ItemCallback<FoodieEntry>() {
            override fun areItemsTheSame(oldItem: FoodieEntry, newItem: FoodieEntry): Boolean {
                return oldItem.revId == newItem.revId
            }

            override fun areContentsTheSame(oldItem: FoodieEntry, newItem: FoodieEntry): Boolean {
                return oldItem.revId == newItem.revId &&
                        oldItem.restName == newItem.restName &&
                        oldItem.shortReview == newItem.shortReview &&
                        oldItem.cuisineType == newItem.cuisineType
            }
        }
    }

    class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val restName: TextView = view.review_name_view
        /* tv_cuisine_type.text
         tv_item_name.text
         tv_item_price.text
         tv_rating.text
         tv_review.text*/


    }
    fun getItemAt(position: Int): FoodieEntry {
        return getItem(position)
    }




    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val restList: FoodieEntry
        if (position != RecyclerView.NO_POSITION) {
            restList=getItemAt(position)
            holder.restName.text = restList.shortReview
        }


    }








}

