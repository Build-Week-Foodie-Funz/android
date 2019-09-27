package com.saucefan.stuff.foodiefunbw.ui.viewedit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saucefan.stuff.foodiefunbw.R

class AddRestaurantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_add_restaurant)
    }
}
