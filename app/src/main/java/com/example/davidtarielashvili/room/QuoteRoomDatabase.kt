package com.example.davidtarielashvili.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by david.tarielashvili on 6/13/2018.
 */

@Database(entities = [(Quote::class)], version = 2)
abstract class QuoteRoomDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    companion object {

        private var INSTANCE: QuoteRoomDatabase? = null


        fun getDatabase(context: Context): QuoteRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(QuoteRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                QuoteRoomDatabase::class.java, "quote_database")
                                .allowMainThreadQueries()
                                .build()

                    }
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
