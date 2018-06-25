package com.example.davidtarielashvili.room.di.module

import android.app.Application

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by david.tarielashvili on 6/25/2018.
 */

@Module
class AppModule(private val app: Application) {
    @Provides @Singleton
    fun provideApplication() = app
}
