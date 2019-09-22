package com.saucefan.stuff.foodiefunbw.Model

data class FoodieEntry(
    //prob the primary key for the room database
    val id:Int,
    //presumedly we have a similar primary key that corressponds to a url or an index number or something
    //on the back end
    val remoteIndex: String,
    //yep from 1970 or whatever
    val entryTime: Long,

    //actual stuff

    var foodEaten:String = "blank",
    var entryText:String = "blank",
    var rating:Int =1,
    var uhSomethingElse:String ="blank",

    //so this will be the list of assosiated Uris for images, List<String> can be passed into room without trouble, but wouldn't be hard to make this... like an adjacent table or something
    //will have to be nullable
    var imageLocal:List<String>? = null



)