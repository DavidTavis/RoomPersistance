package com.example.davidtarielashvili.room.di.module

import android.app.Application
import android.arch.persistence.room.Room

import com.example.davidtarielashvili.room.QuoteDao
import com.example.davidtarielashvili.room.QuoteRepository
import com.example.davidtarielashvili.room.QuoteRoomDatabase

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by david.tarielashvili on 6/25/2018.
 */

@Module
class RoomModule(mApplication: Application) {

    private val roomDatabase: QuoteRoomDatabase

    init {
        roomDatabase = Room.databaseBuilder(mApplication, QuoteRoomDatabase::class.java, "quote_database").allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    internal fun quoteRoomDatabase(): QuoteRoomDatabase {
        return roomDatabase
    }

    @Singleton
    @Provides
    internal fun quoteDao(roomDatabase: QuoteRoomDatabase): QuoteDao {
        return roomDatabase.quoteDao()
    }

    @Singleton
    @Provides
    internal fun quoteRepository(quoteDao: QuoteDao): QuoteRepository {
        return QuoteRepository(quoteDao)
    }

}
