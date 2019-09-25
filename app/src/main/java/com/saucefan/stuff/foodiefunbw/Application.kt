package com.saucefan.stuff.foodiefunbw

import android.app.Application
import androidx.room.RoomDatabase
import com.saucefan.stuff.foodiefunbw.Model.room.EntryDatabase
import com.saucefan.stuff.foodiefunbw.Model.room.EntryMockData
import com.saucefan.stuff.foodiefunbw.Model.room.RoomDao
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import timber.log.Timber


class MyDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return String.format(
            "[C:%s] [M:%s] [L:%s]",
            super.createStackElementTag(element),
            element.methodName,
            element.lineNumber
        )
    }
}

class FoodieFun : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(MyDebugTree())
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        EntryDatabase.destroyInstance()
    }
}


}
