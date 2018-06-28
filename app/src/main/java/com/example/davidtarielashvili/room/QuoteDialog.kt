package com.example.davidtarielashvili.room

import android.app.AlertDialog
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast

/**
 * Created by david.tarielashvili on 6/15/2018.
 */

class QuoteDialog(private val context: Context, private val quoteRepository: QuoteRepository) {

    fun addNewQuote(dialog_layout: Int) {

        val inflater = LayoutInflater.from(context)
        val subView = inflater.inflate(dialog_layout, null)
        val nameField = subView.findViewById<EditText>(R.id.enter_message)
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Add new quote")
        builder.setView(subView)
        builder.create()
        builder.setPositiveButton("ADD QUOTE") { dialog, which ->
            val message = nameField.text.toString()
            if (TextUtils.isEmpty(message)) {
                Toast.makeText(context, "Empty or invalid input", Toast.LENGTH_LONG).show()
            } else {
                //add new quote to database
                quoteRepository?.insert( Quote(message))

            }
        }
        builder.setNegativeButton("CANCEL") { dialog, which -> Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show() }
        builder.show()
    }
}
