package com.saucefan.stuff.foodiefunbw

import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant


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