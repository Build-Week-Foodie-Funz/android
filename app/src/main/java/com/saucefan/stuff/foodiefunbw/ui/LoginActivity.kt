package com.saucefan.stuff.foodiefunbw.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saucefan.stuff.foodiefunbw.R

class LoginActivity : AppCompatActivity() {

    //check keystore for auto token
    //if auth token, skip login

    //else login, on true redirect to dashboard
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
