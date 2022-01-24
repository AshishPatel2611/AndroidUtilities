package com.ap.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.ap.utils.databinding.DialogProgressWithContentBinding

object ProgressDialogWithCustomLoader {

    var alertDialog: AlertDialog? = null

    fun showProgressDialog(
        context: Context,
        title: String,
        message: String,
        isCancelable: Boolean, themeResId: Int
    ): AlertDialog {
        dismissProgressDialog()
        createProgressDialog(context, title, message, isCancelable, themeResId)
        alertDialog!!.show()

        return alertDialog!!
    }

    private fun createProgressDialog(
        context: Context,
        title: String,
        message: String,
        isCancelable: Boolean, themeResId: Int
    ) {

        val dialogBuilder = AlertDialog.Builder(context, themeResId)

        val dialogView =
            DialogProgressWithContentBinding.inflate(LayoutInflater.from(context))

        dialogBuilder.setView(dialogView.root)

        if (title.isEmpty()) {
            dialogView.txtProgressTitle.visibility = View.GONE
        } else {
            dialogView.txtProgressTitle.visibility = View.VISIBLE
            dialogView.txtProgressTitle.text = title
        }
        if (message.isEmpty()) {
            dialogView.txtProgressMessage.visibility = View.GONE
        } else {
            dialogView.txtProgressMessage.visibility = View.VISIBLE
            dialogView.txtProgressMessage.text = message
        }

        dialogBuilder.setView(dialogView.root)
        dialogBuilder.setCancelable(isCancelable)
        alertDialog = dialogBuilder.create()
        alertDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }


    fun dismissProgressDialog() {
        if (alertDialog != null) {
            if (alertDialog!!.isShowing)
                alertDialog!!.dismiss()
            alertDialog = null
        }
    }


}
