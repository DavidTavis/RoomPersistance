package com.example.davidtarielashvili.room

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

/**
 * Created by david.tarielashvili on 6/13/2018.
 */

class QuoteRepository(application: Application) {

    private val mQuoteDao: QuoteDao
    private val quotes: LiveData<List<Quote>>? = null

    val allQuote: LiveData<List<Quote>>
        get() = mQuoteDao.allQuote

    init {
        val db = QuoteRoomDatabase.getDatabase(application)
        mQuoteDao = db!!.quoteDao()
    }


    fun insert(quote: Quote) {
        insertAsyncTask(mQuoteDao).execute(quote)
    }


    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: QuoteDao) : AsyncTask<Quote, Void, Void>() {

        override fun doInBackground(vararg params: Quote): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

}
