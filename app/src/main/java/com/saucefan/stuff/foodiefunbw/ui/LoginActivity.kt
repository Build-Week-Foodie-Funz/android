package com.saucefan.stuff.foodiefunbw.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() { //Ronnie changed xml name to activity login

    //check keystore for auto token
    //if auth token, skip login

    //else login, on true redirect to dashboard
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.saucefan.stuff.foodiefunbw.R.layout.activity_login)
        //just rotates the wave effect



    }
}
