package com.saucefan.stuff.foodiefunbw.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.saucefan.stuff.foodiefunbw.DataBase.UserDatabaseHelper
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*


class LoginActivity : AppCompatActivity() { //Ronnie changed xml name to activity login

    //check keystore for auto token
    //if auth token, skip login

    //else login, on true redirect to dashboard

    lateinit var handler : UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.saucefan.stuff.foodiefunbw.R.layout.activity_login)

        handler = UserDatabaseHelper(this)

        showHome()


        textView_signup.setOnClickListener {
            showRegistration()

        }

        textView_signIn.setOnClickListener {
            showLogIn()
        }

        button_save.setOnClickListener {
            handler.insertUserData(name.text.toString(), password.text.toString())
            showHome()

        }

        button_signin.setOnClickListener {
           if(handler.userInputCheck(login_name.text.toString(), login_password.text.toString())){
               startActivity(Intent(this, DashboardActivity::class.java))
               finish()

           } else{
               Toast.makeText(this, "Username or password is incorrect", Toast.LENGTH_SHORT).show()
           }

        }







    }

    private fun showRegistration(){

        //Shows registration layout
        //Hides login layout
        registration_layout.visibility = View.VISIBLE
        home_ll.visibility = View.GONE

    }

    private fun showLogIn(){
        registration_layout.visibility = View.GONE
        login_layout.visibility = View.VISIBLE
    }

    private fun showHome(){
        registration_layout.visibility = View.GONE
        login_layout.visibility = View.VISIBLE

    }
}
