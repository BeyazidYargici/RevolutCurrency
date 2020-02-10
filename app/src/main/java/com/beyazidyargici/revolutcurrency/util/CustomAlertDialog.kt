package com.beyazidyargici.revolutcurrency.util

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.beyazidyargici.revolutcurrency.R
import kotlinx.android.synthetic.main.alert_dialog_layout.view.*

/**
 *  Custom dialog for network error
 *  Created by beyazid on 09/02/2020.
 */
class CustomAlertDialog constructor(private val activity : Context) {

    var isOpen = false

    fun createAlertDialog(){
        val builder = AlertDialog.Builder(activity)
        val inflater = LayoutInflater.from(activity)
        val dialog = inflater.inflate(R.layout.alert_dialog_layout,null)
        builder.setView(dialog);
        builder.setCancelable(false);
        val alertDialog = builder.create()
        if (!isOpen) {
            alertDialog.show()
            isOpen = true
        }
        dialog.dialog_btn_dismiss.setOnClickListener {
            alertDialog.dismiss()
            isOpen = false
        }
    }

}

interface DialogInterface{
    fun showDialog()
}
