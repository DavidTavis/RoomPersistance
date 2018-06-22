package com.example.davidtarielashvili.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by david.tarielashvili on 6/13/2018.
 */

@Entity(tableName = "quote_table")
data class Quote(@field:ColumnInfo var quote: String?) {

    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

}
