package com.example.davidtarielashvili;

/**
 * Created by david.tarielashvili on 6/15/2018.
 */

public class CurrentQuote {
    private String quote;
    private int uid;

    public CurrentQuote(String quote, int uid) {
        this.quote = quote;
        this.uid = uid;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
