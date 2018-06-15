package com.example.davidtarielashvili.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by david.tarielashvili on 6/13/2018.
 */

@Entity(tableName = "quote_table")
public class Quote {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo
    private String quote;

    public Quote(String quote) {
        this.quote = quote;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
