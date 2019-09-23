package com.saucefan.stuff.foodiefunbw.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken




@Entity (tableName = "Foodie_Table")
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


//TODO 30: LOOK INTO IF THIS IS A SERIOUS PERFORMANCE ISSUE OR IF WE CAN MANAGE IT
class Converters {
    @TypeConverter
    fun listToJson(value: List<String>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<String>? {

        val objects = Gson().fromJson(value, Array<String>::class.java) as Array<String>
        val list = objects.toList()
        return list
    }
}
/*

lighter weight alternative, consider launching a singleton of gson if necessary for above.
class StringListConverter {
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }
}*/
