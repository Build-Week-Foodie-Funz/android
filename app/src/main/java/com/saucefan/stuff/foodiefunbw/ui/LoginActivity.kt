package com.saucefan.stuff.foodiefunbw.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.saucefan.stuff.foodiefunbw.DataBase.UserDatabaseHelper
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.viewmodel.FoodieEntryViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() { //Ronnie changed xml name to activity login

    //check keystore for auto token
    //if auth token, skip login

    //else login, on true redirect to dashboard

    lateinit var handler : UserDatabaseHelper
    private lateinit var foodieEntryViewModel: FoodieEntryViewModel

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
        var foodieEntryViewModel = ViewModelProviders.of(this).get(FoodieEntryViewModel::class.java)

        button_signin.setOnClickListener {
           if(handler.userInputCheck(login_name.text.toString(), login_password.text.toString())){
               startActivity(Intent(this, DashboardActivity::class.java))
               finish()

           } else{
               Toast.makeText(this, "Username or password is incorrect", Toast.LENGTH_SHORT).show()

                   foodieEntryViewModel.insertItem(FoodieEntry(
                           0,
                           "4american4",
                           46.466f,
                           "41/41",
                           "4one plate of 4one",
                           "url here",
                           null,
                           "3",
                           "this 4one4",
                           "this place 4bad"
                   ))
               }
           }


         /*   val itsa = foodieEntryViewModel.returnAllItems()

        for (i in 0 until itsa.value?.size as Int) {
            val temp = itsa.value

        }*/

     //   foodieEntryViewModel.returnAllItems().observe(this,
    //            Observer<List<FoodieEntry>> { Toast.makeText(this,foodieEntryViewModel.allEntrees.value!![0].restName,Toast.LENGTH_LONG) .show()
     //   })



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
