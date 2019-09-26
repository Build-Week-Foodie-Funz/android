package com.saucefan.stuff.foodiefunbw

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant
import com.saucefan.stuff.foodiefunbw.ui.viewedit.EditRestFrag
import com.saucefan.stuff.foodiefunbw.ui.viewedit.EditReviewFrag
import com.saucefan.stuff.foodiefunbw.ui.viewedit.ViewRestFrag
import com.saucefan.stuff.foodiefunbw.ui.viewedit.ViewReviewFrag


//I don't know if we'll actually need this, but lets keep this file to functions that are
// generalizable (i.e. take paramaters, do not need state, function anytime there's run)
// stuff like

/*
*
*           fun fromTime(long:Long):String {
*                   do some stuff to turn this long into a proper date
*               return string }
*
*
*
*       should be a classless file of generalizable methods
* */


// these functions create appropriate fragments, review or retaurant based on
// container where they should appear,
// ID of object you wish to view
// and context of the activity
fun editReviewFragment(container:Int,reviewID:Int,context: Context):String {

    val tag = "reviewFrag$reviewID"
    val reviewFrag = EditReviewFrag.newInstance(1)
    val ft = (context as AppCompatActivity).supportFragmentManager
            .beginTransaction()
            // 2
            .add(container, reviewFrag, tag)
            // 3
            .addToBackStack("review$reviewID") //presumedly re'd name it the id or perhaps "rest$Id"
            .commit()
    return tag
}

fun editRestFrag(container:Int,restID:Int,context:Context):String  {
    val tag = "restFrag$restID"
    val restFrag = EditRestFrag.newInstance(1)
    val ft = (context as AppCompatActivity).supportFragmentManager
            .beginTransaction()
            // 2
            .add(container, restFrag, tag)
            // 3
            .addToBackStack("rest$restID") //presumedly re'd name it the id or perhaps "rest$Id"
            .commit()
    return tag

}


fun displayReviewFragment(container:Int,reviewID:Int,context: Context):String {

    val tag = "reviewFrag$reviewID"
    val reviewFrag = ViewReviewFrag.newInstance(1)
    val ft = (context as AppCompatActivity).supportFragmentManager
            .beginTransaction()
            // 2
            .add(container, reviewFrag, tag)
            // 3
            .addToBackStack("review$reviewID") //presumedly re'd name it the id or perhaps "rest$Id"
            .commit()
    return tag
}

fun displayRestFrag(container:Int,restID:Int,context:Context):String  {
    val tag = "restFrag$restID"
    val restFrag = ViewRestFrag.newInstance(1)
    val ft = (context as AppCompatActivity).supportFragmentManager
            .beginTransaction()
            // 2
            .add(container, restFrag, tag)
            // 3
            .addToBackStack("rest$restID") //presumedly re'd name it the id or perhaps "rest$Id"
            .commit()
    return tag

}

data class User  (
        val id:String, //presumedly this is the id assigned by server at registration
        var location:String, //location string
        val username:String, //user chosen username
        var password:String, // same but for a password
        var email:String, //why is there an email and a username?
        var restaurant: List<FoodieRestaurant>

/*
* I got a bad feeling about this spec...
*
* {
  "authority": [
    {
      "authority": "string"
    }
  ],
  "location": "string",
  "password": "string",
  "passwordNoEncrypt": "string",
  "photo": "string",
  "restaurant": "Some Restaurant, Some Restaurant,...",
  "useremails": [
    {
      "useremail": "string",
      "useremailid": 0
    }
  ],
  "userid": 0,
  "username": "string",
  "userroles": [
    {
      "role": {
        "name": "string",
        "roleid": 0,
        "userroles": [
          null
        ]
      }
    }
  ]
}
Parameter content type

*
* */
)