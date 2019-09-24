package com.saucefan.stuff.foodiefunbw.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


//almost assuredly will be removed whole sale, kept to maintain compatability for now

class UserDatabaseHelper (context: Context): SQLiteOpenHelper(context, dbname, factory, version){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table user (id integer primary key autoincrement," +
        "name varchar(30), password varchar(20))")




    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertUserData(name: String, password: String){
        val db: SQLiteDatabase = writableDatabase
        val values: ContentValues = ContentValues()
        values.put("name", name)
        values.put("password", password)


        db.insert("user", null, values)
        db.close()
    }

    fun userInputCheck(name: String, password: String): Boolean{
        val db = writableDatabase
        val query = "select * from user where name = '$name' and password = '$password'"
        val cursor = db.rawQuery(query, null)
        if(cursor.count<=0){
            cursor.close()
            return false
        }

        cursor.close()
        return true
    }


    companion object{
        internal val dbname = "userDB"
        internal val factory = null
        internal val version = 1
    }
}