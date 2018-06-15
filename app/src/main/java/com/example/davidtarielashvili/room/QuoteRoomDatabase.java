package com.example.davidtarielashvili.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by david.tarielashvili on 6/13/2018.
 */

@Database(entities = {Quote.class}, version = 1)
public abstract class QuoteRoomDatabase extends RoomDatabase {

    public abstract QuoteDao quoteDao();

    private static QuoteRoomDatabase INSTANCE;


    public static QuoteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuoteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuoteRoomDatabase.class, "quote_database")
                            .allowMainThreadQueries()
                            .build();

                }
            }
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
