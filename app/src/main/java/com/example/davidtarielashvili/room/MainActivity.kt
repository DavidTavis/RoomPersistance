package com.example.davidtarielashvili.room

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView

import com.example.davidtarielashvili.CurrentQuote
import com.example.davidtarielashvili.QuoteDialog

class MainActivity : AppCompatActivity() {

    private var tvQuote: TextView? = null
    private var currentQuote: CurrentQuote? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvQuote = findViewById(R.id.tv_quote)

        val quotes = applicationContext.resources.getStringArray(R.array.array_quotes)
        val repository = QuoteRepository(application)

        for (strQuote in quotes) {
            repository.insert(Quote(strQuote))
        }

        val addBtn = findViewById<ImageButton>(R.id.add_btn)
        addBtn.setOnClickListener { view ->
            val dialog = QuoteDialog(this)
            dialog.addNewQuote(R.layout.dialog_layout)
        }

        val quoteDao = QuoteRoomDatabase.getDatabase(applicationContext)!!.quoteDao()

        quoteDao.allQuote.observe(this, Observer { quoteList ->
            val quoteLast = quoteList!![quoteList.size - 1]
            currentQuote = CurrentQuote(quoteLast.quote, quoteLast.uid)
            tvQuote!!.text = quoteLast.quote

        })

        val nextBtn = findViewById<ImageButton>(R.id.next_btn)
        nextBtn.setOnClickListener { view ->
            val quoteLast = quoteDao.findByID(currentQuote!!.uid)
            currentQuote = CurrentQuote(quoteLast.quote, quoteLast.uid)
            tvQuote!!.text = quoteLast.quote
        }

    }

}
