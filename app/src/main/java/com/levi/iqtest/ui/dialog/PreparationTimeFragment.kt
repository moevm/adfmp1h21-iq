package com.levi.iqtest.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.levi.iqtest.R
import fr.tvbarthel.lib.blurdialogfragment.BlurDialogEngine


class PreparationTimeFragment(
    val onFinishCountDown: () -> Unit
) :
    DialogFragment() {
    var countDownTimer: CountDownTimer? = null
    private var mBlurEngine: BlurDialogEngine? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBlurEngine = BlurDialogEngine(activity)
        mBlurEngine?.setBlurRadius(2);
        mBlurEngine?.setDownScaleFactor(6f);
//        mBlurEngine?.debug(true);
        mBlurEngine?.setBlurActionBar(true);
        mBlurEngine?.setUseRenderScript(true);
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        mBlurEngine?.onDismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBlurEngine?.onDetach()
    }

    override fun onResume() {
        super.onResume()
        mBlurEngine?.onResume(retainInstance)
    }

    override fun onDestroyView() {
        if (getDialog() != null) {
            getDialog()?.setDismissMessage(null);
        }
        super.onDestroyView()
    }

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