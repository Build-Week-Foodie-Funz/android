package com.saucefan.stuff.foodiefunbw.Model.room

import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant

object EntryMockData {
    var entryList = mutableListOf<FoodieEntry>(
            FoodieEntry(
                    0,
                    "american2",
                    2.22f,
                    "2/2",
                    "2 plate of 2",
                    "url here",
                    null,
                    "1",
                    "this 2",
                    "this place 2 bad"
            ),
            FoodieEntry(
                    0,
                    "american3",
                    3.66f,
                    "3/3",
                    "3 plate of 3",
                    "url here",
                    null,
                    "2",
                    "this 3",
                    "this place3 bad"
            ),
            FoodieEntry(
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
            )


    )
    var stupidList = listOf<String>("asdfa", "adfasdfas")
    var restList = mutableListOf<FoodieRestaurant>(
            FoodieRestaurant(
                    0,
                    "0",
                    "09/10/1985",
                    "9-5",
                    "greenwitches,constant",
                    "this one place",
                    "6 out 2",
                    stupidList),
            FoodieRestaurant(
                    0,
                    "2",
                    "09/10/1985",
                    "9-522",
                    "entry 2 location",
                    "this one place",
                    "2/2",
                    stupidList),
            FoodieRestaurant(
                    0,
                    "3",
                    "09/10/1985",
                    "9-522",
                    "entry 3 location",
                    "that 3 place",
                    "3 / 3",
                    stupidList),
            FoodieRestaurant(
                    0,
                    "4",
                    "09/10/1985",
                    "9-522",
                    "entry 4 locationt",
                    "this 4 place",
                    "4 out 4",
                    stupidList)

    )


}


