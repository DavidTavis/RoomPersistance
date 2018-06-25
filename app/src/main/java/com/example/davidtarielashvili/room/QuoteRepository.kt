package com.example.davidtarielashvili.room

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import javax.inject.Inject

/**
 * Created by david.tarielashvili on 6/13/2018.
 */

class QuoteRepository @Inject constructor(private val quoteDao: QuoteDao) {

    private val quotes: LiveData<List<Quote>>? = null

    val allQuote: LiveData<List<Quote>>
        get() = quoteDao.allQuote

    fun insert(quote: Quote) {
        insertAsyncTask(quoteDao).execute(quote)
    }

    fun findByID(uid: Int): Quote{
        return quoteDao.findByID(uid)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: QuoteDao) : AsyncTask<Quote, Void, Void>() {

        override fun doInBackground(vararg params: Quote): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

}
