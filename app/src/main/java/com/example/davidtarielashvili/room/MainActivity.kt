package com.example.davidtarielashvili.room

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.example.davidtarielashvili.room.di.component.AppComponent
import com.example.davidtarielashvili.room.di.component.DaggerAppComponent

import com.example.davidtarielashvili.room.di.module.AppModule
import com.example.davidtarielashvili.room.di.module.RoomModule
import javax.inject.Inject

 class MainActivity : AppCompatActivity() {

    private var tvQuote: TextView? = null
    private var currentQuote: CurrentQuote? = null

    @Inject
    lateinit  var quoteRepository: QuoteRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val component: AppComponent by lazy {
            DaggerAppComponent
                    .builder()
//                .appModule(AppModule(application))
                    .roomModule(RoomModule(application))
                    .build()
        }

        component.inject(this)

//        val quoteRepository: QuoteRepository? = component.quoteRepository()


        tvQuote = findViewById(R.id.tv_quote)

        val quotes = applicationContext.resources.getStringArray(R.array.array_quotes)

        for (strQuote in quotes) {
            quoteRepository?.insert(Quote(strQuote))
        }

        val addBtn = findViewById<ImageButton>(R.id.add_btn)
        addBtn.setOnClickListener { view ->
            val dialog = QuoteDialog(this,quoteRepository!!)
            dialog.addNewQuote(R.layout.dialog_layout)
        }

        quoteRepository?.allQuote?.observe(this, Observer { quoteList ->
            val quoteLast = quoteList!![quoteList.size - 1]
            currentQuote = CurrentQuote(quoteLast.quote, quoteLast.uid)
            tvQuote!!.text = quoteLast.quote

        })

        val nextBtn = findViewById<ImageButton>(R.id.next_btn)
        nextBtn.setOnClickListener { view ->
            val quoteLast = quoteRepository!!.findByID(currentQuote!!.uid)
            currentQuote = CurrentQuote(quoteLast.quote, quoteLast.uid)
            tvQuote!!.text = quoteLast.quote
        }

    }

}
