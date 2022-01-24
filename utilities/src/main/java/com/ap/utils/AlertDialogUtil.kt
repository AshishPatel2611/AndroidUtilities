package com.ap.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

object AlertDialogUtil {

    fun showOneButtonAlertDialog(
        context: Context,
        title: String,
        message: String,
        positiveButton: String,
        onOneButtonAlertDialogClickListener: OnOneButtonAlertDialogClickListener
    ): AlertDialog {

        val alertDialog = AlertDialog.Builder(context).create()

        if (title.isNotEmpty())
            alertDialog.setTitle(title)
        if (message.isNotEmpty())
            alertDialog.setMessage(message)

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, positiveButton) { dialog, which ->
            onOneButtonAlertDialogClickListener.onPositiveButtonClicked(alertDialog)
        }


        return alertDialog
    }

    interface OnOneButtonAlertDialogClickListener {

        fun onPositiveButtonClicked(alertDialog: AlertDialog)

    }

    fun showTwoButtonAlertDialog(
        context: Context,
        title: String,
        message: String,
        positiveButton: String,
        negativeButton: String,
        onTwoButtonAlertDialogClickListener: OnTwoButtonAlertDialogClickListener
    ): AlertDialog {


        val alertDialog = AlertDialog.Builder(context).create()

        if (title.length > 0)
            alertDialog.setTitle(title)
        if (message.length > 0)
            alertDialog.setMessage(message)

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, positiveButton) { dialog, which ->

            onTwoButtonAlertDialogClickListener.onPositiveButtonClicked(alertDialog)
        }
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, negativeButton) { dialog, which ->

            onTwoButtonAlertDialogClickListener.onNegativeButtonClicked(alertDialog)

        }

        return alertDialog
    }

    interface OnTwoButtonAlertDialogClickListener {

        fun onPositiveButtonClicked(alertDialog: AlertDialog)

        fun onNegativeButtonClicked(alertDialog: AlertDialog)
    }


    fun showThreeButtonAlertDialog(
        context: Context,
        title: String,
        message: String,
        positiveButton: String,
        negativeButton: String,
        neutralButton: String,
        onThreeButtonAlertDialogClickListener: OnThreeButtonAlertDialogClickListener
    ): AlertDialog {


        val alertDialog = AlertDialog.Builder(context).create()

        if (title.length > 0)
            alertDialog.setTitle(title)
        if (message.length > 0)
            alertDialog.setMessage(message)

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, positiveButton) { dialog, which ->

            onThreeButtonAlertDialogClickListener.onPositiveButtonClicked(alertDialog)
        }
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, negativeButton) { dialog, which ->

            onThreeButtonAlertDialogClickListener.onNegativeButtonClicked(alertDialog)

        }
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, neutralButton) { dialog, which ->

            onThreeButtonAlertDialogClickListener.onNeutralButtonClicked(alertDialog)

        }

        return alertDialog
    }

    interface OnThreeButtonAlertDialogClickListener {

        fun onPositiveButtonClicked(alertDialog: AlertDialog)

        fun onNegativeButtonClicked(alertDialog: AlertDialog)

        fun onNeutralButtonClicked(alertDialog: AlertDialog)
    }

    fun createCustomAlertDialog(context: Context, customView: View): AlertDialog {

        val alertDialog = AlertDialog.Builder(context).create()

        alertDialog.setView(customView)

        return alertDialog
    }

    fun setFullScreenDialog(context: Context, themeResId: Int): Dialog {
        // the content
        val root = RelativeLayout(context)
        root.layoutParams =
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        // creating the fullscreen dialog
        val dialog = Dialog(context, themeResId)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        return dialog
    }


    fun showDialogFragment(activity: AppCompatActivity, fragment: DialogFragment) {

        try {
            val ft = activity.supportFragmentManager.beginTransaction()
            val prev =
                activity.supportFragmentManager.findFragmentByTag(fragment::class.java.simpleName)
            if (prev != null) {
                ft.remove(prev)
            }
            ft.addToBackStack(null)
            fragment.show(ft, fragment::class.java.simpleName)
        } catch (e: Exception) {
            Log.e("AlertDialogUtil", "showDialogFragment : ${e.printStackTrace()} ")
        }
    }


}