package com.saucefan.stuff.foodiefunbw.Model.room

import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant
import com.saucefan.stuff.foodiefunbw.R
import kotlinx.android.synthetic.main.activity_registration.view.*

object EntryMockData {
    var entryList = mutableListOf(
            FoodieEntry(
                    0,
                    "china2",
                    2.22f,
                    "2/2",
                    "2 plate of 2",
                    "url here",
                    null,
                    "1",
                    "mcdonalds",
                    "this place 2 bad"
            ),
            FoodieEntry(
                    0,
                    "japana",
                    3.66f,
                    "3/3",
                    "3 plate of 3",
                    "url here",
                    null,
                    "2",
                    "mcdonalds",
                    "this place3 bad"
            ),
            FoodieEntry(
                    0,
                    "notaican",
                    46.466f,
                    "41/41",
                    "4one plate of 4one",
                    "url here",
                    null,
                    "3",
                    "mcdonalds",
                    "this place 4bad"
            ),
            FoodieEntry(
                    0,
                    "china2",
                    2.22f,
                    "2/2",
                    "2 plate of 2",
                    "url here",
                    null,
                    "1",
                    "my friends house (he has eggs a lot)",
                    "this place 2 bad"
            ),
            FoodieEntry(
                    0,
                    "japana",
                    3.66f,
                    "3/3",
                    "3 plate of 3",
                    "url here",
                    null,
                    "2",
                    "the other mcdonalds",
                    "this place3 bad"
            ),
            FoodieEntry(
                    0,
                    "notaican",
                    46.466f,
                    "41/41",
                    "4one plate of 4one",
                    "url here",
                    null,
                    "3",
                    "my friends house (he has eggs a lot)",
                    "this place 4bad"
            ),
            FoodieEntry(
                    0,
                    "china2",
                    2.22f,
                    "2/2",
                    "2 plate of 2",
                    "url here",
                    null,
                    "1",
                    "the subway where they were rude to me",
                    "this place 2 bad"
            ),
            FoodieEntry(
                    0,
                    "japana",
                    3.66f,
                    "3/3",
                    "3 plate of 3",
                    "url here",
                    null,
                    "2",
                    "the other mcdonalds",
                    "this place3 bad"
            ),
            FoodieEntry(
                    0,
                    "notaican",
                    46.466f,
                    "41/41",
                    "4one plate of 4one",
                    "url here",
                    null,
                    "3",
                    "the subway where they were rude to me",
                    "this place 4bad"
            )

    )
    var stupidList = R.drawable.btn_shape_capsule.toString()
    var restList = mutableListOf<FoodieRestaurant>(
            FoodieRestaurant(
                    0,
                    "0",
                    "09/10/1985",
                    "9-5",
                    "greenwitches,constant",
                    "mcdonalds",
                    "6 out 2",
                    stupidList),
            FoodieRestaurant(
                    0,
                    "2",
                    "09/10/1985",
                    "9-522",
                    "entry 2 location",
                    "the other mcdonalds",
                    "2/2",
                    stupidList),
            FoodieRestaurant(
                    0,
                    "3",
                    "09/10/1985",
                    "9-522",
                    "entry 3 location",
                    "the subway where they were rude to me",
                    "3 / 3",
                    stupidList),
            FoodieRestaurant(
                    0,
                    "4",
                    "09/10/1985",
                    "9-522",
                    "entry 4 locationt",
                    "my friends house (he has eggs a lot)",
                    "4 out 4",
                    stupidList)

    )


}


