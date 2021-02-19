package com.levi.iqtest.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment

class TimeoutDialogFragment(val message: String, val onClickPositive: () -> Unit) :
    DialogFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        dialog?.setCanceledOnTouchOutside(true)
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDismiss(dialog: DialogInterface) {
        onClickPositive()
        super.onDismiss(dialog)
    }

    override fun onCancel(dialog: DialogInterface) {
        onClickPositive()
        super.onCancel(dialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(message)
                .setPositiveButton(
                    "Ok"
                ) { dialog, id ->
                    Log.i("Debug", "positive")
                    onClickPositive()
                }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}