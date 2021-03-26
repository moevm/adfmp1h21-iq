package com.levi.iqtest.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import com.levi.iqtest.R


class PreparationTimeFragment(
    val onFinishCountDown: () -> Unit
) :
    DialogFragment() {
    var countDownTimer: CountDownTimer? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater;

            val customView: View = inflater.inflate(R.layout.dialog_countdown, null)
            builder.setView(customView)
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
        dialog.setCanceledOnTouchOutside(false)

        countDownTimer = object : CountDownTimer(4500, 1500) {
            override fun onFinish() {
                dismiss()
                onFinishCountDown()
            }

            override fun onTick(millisUntilFinished: Long) {
                dialog.findViewById<TextView>(R.id.txtMessage).text =(millisUntilFinished / 1500 +1).toString()
            }
        }
        countDownTimer?.start()
        return dialog
    }

}