package com.example.davidtarielashvili.room.di.component

import android.app.Application
import com.example.davidtarielashvili.room.*

import com.example.davidtarielashvili.room.di.module.AppModule
import com.example.davidtarielashvili.room.di.module.RoomModule

import javax.inject.Singleton

import dagger.Component

/**
 * Created by david.tarielashvili on 6/25/2018.
 */

@Singleton
@Component(dependencies = arrayOf(), modules = arrayOf(AppModule::class, RoomModule::class))
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(dialog: QuoteDialog)

    fun quoteDao(): QuoteDao

    fun quoteDatabase(): QuoteRoomDatabase

    fun quoteRepository(): QuoteRepository

    fun application(): Application

}
