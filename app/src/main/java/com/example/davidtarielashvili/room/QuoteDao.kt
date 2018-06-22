package com.example.davidtarielashvili.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by david.tarielashvili on 6/13/2018.
 */

@Dao
interface QuoteDao {

    @get:Query("SELECT * FROM quote_table")
    val allQuote: LiveData<List<Quote>>

    @Query("SELECT * FROM quote_table WHERE uid IN (:quoteIds)")
    fun loadAllByIds(quoteIds: IntArray): LiveData<List<Quote>>

    @Query("SELECT * FROM quote_table WHERE quote LIKE :quote LIMIT 1")
    fun findByName(quote: String): Quote

    @Query("SELECT * FROM quote_table WHERE uid LIKE :uid LIMIT 1")
    fun findByID(uid: Int): Quote

    @Insert
    fun insert(quote: Quote)

    @Insert
    fun insertAll(vararg quotes: Quote)

    @Delete
    fun delete(quote: Quote)

}
