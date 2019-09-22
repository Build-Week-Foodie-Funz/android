package com.saucefan.stuff.foodiefunbw.Model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class FoodieEntry(
    //prob the primary key for the room databas
        @PrimaryKey(autoGenerate = true)
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
    var imageLocal:List<String>? = null,

    //isDeleted will indicate to send a message to the server at next sync to delete remote copy
    var isDeleted:Boolean=false,
        //isUpdated is similar, update copy stored server side
    var isUpdated:Boolean=false





)