package com.example.davidtarielashvili.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by david.tarielashvili on 6/13/2018.
 */

@Database(entities = [(Quote::class)], version = 1)
abstract class QuoteRoomDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

}
