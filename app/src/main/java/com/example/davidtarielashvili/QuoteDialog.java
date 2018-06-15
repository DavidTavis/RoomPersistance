package com.example.davidtarielashvili;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidtarielashvili.room.Quote;
import com.example.davidtarielashvili.room.QuoteDao;
import com.example.davidtarielashvili.room.QuoteRoomDatabase;
import com.example.davidtarielashvili.room.R;

/**
 * Created by david.tarielashvili on 6/15/2018.
 */

public class QuoteDialog {

    private Context context;

    public QuoteDialog(Context context) {
        this.context = context;
    }
    public void addNewQuote(int dialog_layout){

        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(dialog_layout, null);
        final EditText nameField = subView.findViewById(R.id.enter_message);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add new quote");
        builder.setView(subView);
        builder.create();
        builder.setPositiveButton("ADD QUOTE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String message = nameField.getText().toString();
                if(TextUtils.isEmpty(message)){
                    Toast.makeText(context, "Empty or invalid input", Toast.LENGTH_LONG).show();
                }
                else{
                    Quote content = new Quote(message);
                    //add new quote to database
                    QuoteDao quoteDao = QuoteRoomDatabase.getDatabase(context).quoteDao();
                    quoteDao.insert(content);
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}
