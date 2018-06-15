package com.example.davidtarielashvili.room;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.davidtarielashvili.CurrentQuote;
import com.example.davidtarielashvili.QuoteDialog;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuote;
    private CurrentQuote currentQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuote = findViewById(R.id.tv_quote);

        String[] quotes = getApplicationContext().getResources().getStringArray(R.array.array_quotes);
        QuoteRepository repository = new QuoteRepository(getApplication());

        for (String strQuote : quotes) {
            repository.insert(new Quote(strQuote));
        }

        ImageButton addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            QuoteDialog dialog = new QuoteDialog(this);
            dialog.addNewQuote(R.layout.dialog_layout);
        });

        QuoteDao quoteDao = QuoteRoomDatabase.getDatabase(getApplicationContext()).quoteDao();
        quoteDao.getAllQuote().observe(this, (List<Quote> quoteList) -> {
            Quote quoteLast = quoteList.get(quoteList.size() - 1);
            currentQuote = new CurrentQuote(quoteLast.getQuote(),quoteLast.getUid());
            tvQuote.setText(quoteLast.getQuote());
        });

        ImageButton nextBtn = findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(view -> {
            Quote quoteLast = quoteDao.findByID(currentQuote.getUid());
            currentQuote = new CurrentQuote(quoteLast.getQuote(),quoteLast.getUid());
            tvQuote.setText(quoteLast.getQuote());
        });


    }

}
