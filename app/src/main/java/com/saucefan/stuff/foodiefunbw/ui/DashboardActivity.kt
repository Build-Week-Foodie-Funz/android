package com.saucefan.stuff.foodiefunbw.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.FragmentManager
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.R
import com.saucefan.stuff.foodiefunbw.ui.search.SearchActivity
import com.saucefan.stuff.foodiefunbw.ui.viewedit.AddRestaurantActivity
import com.saucefan.stuff.foodiefunbw.ui.viewedit.RestaurantActivity
import com.saucefan.stuff.foodiefunbw.ui.viewedit.ViewRestFrag
import com.saucefan.stuff.foodiefunbw.ui.viewedit.ViewReviewFrag
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {


    //dasboard just moves us around the app, the more minimal the better

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        img_search.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        img_restaurant.setOnClickListener {
            val intent = Intent(this, RestaurantActivity::class.java)
            startActivity(intent)
        }

        img_add.setOnClickListener {
            val intent = Intent(this, AddRestaurantActivity::class.java)
            startActivity(intent)
        }


    }
}
