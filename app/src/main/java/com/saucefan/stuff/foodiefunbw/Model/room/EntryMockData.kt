package com.saucefan.stuff.foodiefunbw.Model.room

import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry

object  EntryMockData {
        var entryList = mutableListOf<FoodieEntry>()
        var stupidList = listOf<String>("asdfa","adfasdfas")
        init{

            entryList.add(
                    FoodieEntry(
                            0,
                            "1",
                            1,
                            "sauce",
                            "fooods additoionalysff",
                            3,
                            "  something else ",
                            stupidList,
                            false,
                            false))

            entryList.add(
                    FoodieEntry(0,
                            " ",
                            1,
                            "fooods ",
                            "another thing",
                            1,
                            "asdfasdf",
                            stupidList,
                            false,
                            false))


            entryList.add(
                    FoodieEntry(
                            0,
                            "1",
                            1,
                            "sauce",
                            "i miss zoloft so much",
                            3,
                            "  other stuff ",
                            stupidList,
                            false,
                            false))

            entryList.add(
                    FoodieEntry(0,
                            "remote index",
                            1,
                            "fooods additoionaly ",
                            "another thing",
                            1,
                            "asdfasdf",
                            stupidList,
                            false,
                            false))











        }

}
