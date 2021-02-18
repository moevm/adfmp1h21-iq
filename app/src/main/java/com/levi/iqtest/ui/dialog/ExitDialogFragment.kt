package com.levi.iqtest.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ExitDialogFragment(val message: String, val onClickPositive: () -> Unit, val onclickNegative: () -> Unit) :
    DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(message)
                .setPositiveButton(
                    "Ok"
                ) { dialog, id ->
                    onClickPositive()
                }
                .setNegativeButton(
                    "Cancel"
                ) { dialog, id ->
                    onclickNegative()
                }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}