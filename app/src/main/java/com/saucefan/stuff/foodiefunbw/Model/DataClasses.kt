package com.saucefan.stuff.foodiefunbw.Model

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.google.gson.Gson
import com.saucefan.stuff.foodiefunbw.User

//user should only have one entry but defining it as a data class,
//should help keep use honest and make sure nothing is missed


@Entity (tableName = "restaurant_table")
data class FoodieRestaurant(
        //primary key local
        @PrimaryKey(autoGenerate = true)
        var restId:Int =0,
        @ColumnInfo(name = "rest_id")
        var remoteIndex:String, //remote "restid"
        @ColumnInfo(name = "recent_visit")
        var recentVisit: String, // as per spec, MM/DD/YYYY, should be easy enough to parse or even search through
        @ColumnInfo(name = "rest_hours")
        var restHours:String,             // "resthours": "8:00AM - 9:00PM",
        @ColumnInfo(name = "rest_location")
        var restLocation:String, // "restlocation": "Philadelphia, PA",
        @ColumnInfo(name = "rest_name")
        var restName:String ="", // "restname": "Burger King", (foriegn key  in review obj. resturant name)
        @ColumnInfo(name = "rest_rating")
        var restRating:String="", // "restrating": "9/10",
        @ColumnInfo(name = "rest_photos")
        var restPhotos:String? = null, // "restphotos": "http://www.fnstatic.co.uk/images/content/recipe/three-egg-omelette.jpeg, http://www.fnstatic.co.uk/images/content/recipe/three-egg-omelette.jpeg",

        // for the sake of the db we will be ignoring these often redudant objects
        //they exist here primarily:
        //1:for reference
        //2:if in later code writing they are convenient, they can be used



        @Ignore var restReviews:List<FoodieEntry>? = null, // recusive (self referential? whatever) definition in api, may be usefull at some point to have this capable of taking on reviews, but we do not want to store them like this"reviews": "Some Review, Some Review,...",
        @Ignore var user: User? = null//a fully formed resturant under the api at current spec will contain a user object, however all objects for an android instance should belong to one users, hence the database will ignore this complicated data type to avoid unecessary processing and work
){
    constructor() : this(0, "", "", "", "", "", "", null,null,null)
}



@Entity (tableName = "review_table")
data class FoodieEntry(

        @PrimaryKey(autoGenerate = true)
         var revId:Int,
        @ColumnInfo(name = "cuisine_type")
        var cuisineType:String? ="", // "cuisinetype": "American", resturant type?
        @ColumnInfo(name = "item_price")
        var itemPrice:Float =0f,// "itemprice": 10,
        @ColumnInfo(name = "rating")
        var rating:String ="1", ///"itemrating": "9/10",
        @ColumnInfo(name = "menu_item_name")
        var menuItemName:String = "blank", //"menuitemname": "Three Egg Omelete",
        @ColumnInfo(name = "photo_menu")
        var photoMenu:String = "",


        @Ignore var restaurant:FoodieRestaurant? =null, // recusive (self referential? whatever) definition in api, may be usefull at some point to have this capable of taking on reviews, but we do not want to store them like this"reviews": "Some Review, Some Review,...",

        var remoteIndex: String = "", //"reviewid": 1,

        @ColumnInfo(name = "rest_name")
        var restName:String? ="",

        @ColumnInfo(name = "short_review")
        var shortReview:String = "blank" //"shortreview": "Fantastic Food!"


){
    constructor() : this( 0,"", 0f, "", "", "", null, "0")
}

/*
*
* {
  "cuisinetype": "American",
  "itemprice": 10,
  "itemrating": "9/10",
  "menuitemname": "Three Egg Omelete",
  "photomenu": "http://www.fnstatic.co.uk/images/content/recipe/three-egg-omelette.jpeg",
  "restaurant": {
    "recentvisit": "07/15/2019",
    "resthours": "8:00AM - 9:00PM",
    "restid": 1,
    "restlocation": "Philadelphia, PA",
    "restname": "Burger King",
    "restphotos": "http://www.fnstatic.co.uk/images/content/recipe/three-egg-omelette.jpeg, http://www.fnstatic.co.uk/images/content/recipe/three-egg-omelette.jpeg",
    "restrating": "9/10",
    "reviews": "Some Review, Some Review,...",
    "user": "Some Restaurant, Some Restaurant,..."
  },
  "reviewid": 1,
  "shortreview": "Fantastic Food!"
}
*
* */

//TODO 30: LOOK INTO IF THIS IS A SERIOUS PERFORMANCE ISSUE OR IF WE CAN MANAGE IT
class Converters {
    @TypeConverter
    fun listToJson(value: List<String>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String?): List<String>? {
        var list:List<String>
    if (value!=null) {
        val objects = Gson().fromJson(value, Array<String>::class.java) as Array<String>

         list = objects.toList()
    }
        else {
         list = listOf(" "," ")
    }
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
