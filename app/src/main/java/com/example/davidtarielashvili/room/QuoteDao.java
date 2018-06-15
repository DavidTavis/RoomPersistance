package com.example.davidtarielashvili.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by david.tarielashvili on 6/13/2018.
 */

@Dao
public interface QuoteDao {

    @Query("SELECT * FROM quote_table")
    LiveData<List<Quote>> getAllQuote();

    @Query("SELECT * FROM quote_table WHERE uid IN (:quoteIds)")
    LiveData<List<Quote>> loadAllByIds(int[] quoteIds);

    @Query("SELECT * FROM quote_table WHERE quote LIKE :quote LIMIT 1")
    Quote findByName(String quote);

    @Insert
    void insert(Quote quote);

    @Insert
    void insertAll(Quote... quotes);

    @Delete
    void delete(Quote quote);

}
