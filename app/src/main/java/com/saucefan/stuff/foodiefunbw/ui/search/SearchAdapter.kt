package com.saucefan.stuff.foodiefunbw.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant
import com.saucefan.stuff.foodiefunbw.R

class  SearchAdapter(context: Context, val layout: Int, val chapter: List<FoodieRestaurant>, private val arguments: Bundle?) : ArrayAdapter<FoodieRestaurant>(context, layout, chapter) {


    override fun getCount(): Int {
        return chapter.size
    }
    override fun getItem(position: Int): FoodieRestaurant? {
        return chapter.get(position)
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var retView: View
        var vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (convertView == null) {
            retView = vi.inflate(layout, null)
        } else {
            retView = convertView

        }
        var chapterItem = getItem(position)
        val chapterName = retView.findViewById(R.id.tvSearch) as TextView
        chapterName.text = chapterItem!!.restName
        retView.setOnClickListener {




        }



        return retView


    }


}