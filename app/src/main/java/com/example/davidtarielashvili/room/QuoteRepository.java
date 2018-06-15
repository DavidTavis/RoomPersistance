package com.example.davidtarielashvili.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by david.tarielashvili on 6/13/2018.
 */

public class QuoteRepository {

    private QuoteDao mQuoteDao;
    private LiveData<List<Quote>> quotes;

    public QuoteRepository(Application application) {
        QuoteRoomDatabase db = QuoteRoomDatabase.getDatabase(application);
        mQuoteDao = db.quoteDao();
    }

    public LiveData<List<Quote>> getAllQuote() {
        return mQuoteDao.getAllQuote();
    }


    public void insert (Quote quote) {
        new insertAsyncTask(mQuoteDao).execute(quote);
    }


    private static class insertAsyncTask extends AsyncTask<Quote, Void, Void> {

        private QuoteDao mAsyncTaskDao;

        insertAsyncTask(QuoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Quote... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
