package com.saucefan.stuff.foodiefunbw.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.viewmodel.FoodieEntryViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() { //Ronnie changed xml name to activity login

    //check keystore for auto token
    //if auth token, skip login

    //else login, on true redirect to dashboard
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.saucefan.stuff.foodiefunbw.R.layout.activity_login)

        textView_signup.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
            finish()
        }
        var foodieViewModel = ViewModelProviders.of(this).get(FoodieEntryViewModel::class.java)
        var tempCollect = mutableListOf<FoodieEntry>()
         login.setOnClickListener {
            GlobalScope.launch {
                val collections = foodieViewModel.returnAllItems()


                val m = "m"
            }
        }



    }
}
