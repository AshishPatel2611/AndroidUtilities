package com.ap.utils

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

object SnackBarUtils {

    enum class SnackBarType {
        SUCCESS,
        WARNING,
        ERROR
    }

    fun success(context: AppCompatActivity, view: View, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.success))

        // change snackbar text color
        val snackbarTextId = R.id.snackbar_text
        val textView = snackbar.view.findViewById(snackbarTextId) as TextView
        textView.setTextColor(ContextCompat.getColor(context, R.color.black))

        setSnackBarOnTop(context, snackbar)

        snackbar.show()


    }

    fun warning(context: AppCompatActivity, view: View, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.warning))
        // change snackbar text color
        val snackbarTextId = R.id.snackbar_text
        val textView = snackbar.view.findViewById(snackbarTextId) as TextView
        textView.setTextColor(ContextCompat.getColor(context, R.color.black))

        setSnackBarOnTop(context, snackbar)
        snackbar.show()
    }


    fun error(context: AppCompatActivity, view: View, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.error))
        // change snackbar text color
        val snackbarTextId = R.id.snackbar_text
        val textView = snackbar.view.findViewById(snackbarTextId) as TextView
        textView.setTextColor(ContextCompat.getColor(context, R.color.black))

        setSnackBarOnTop(context, snackbar)
        snackbar.show()
    }

    private fun setSnackBarOnTop(context: AppCompatActivity, snackBar: Snackbar) {

        try {

            val params = (snackBar.view.layoutParams) as FrameLayout.LayoutParams

            params.gravity = Gravity.TOP

            Log.i("SnackbarUtils", "setSnackBarOnTop : ${context.supportActionBar} ")

            if (context.supportActionBar != null) {
                val tv = TypedValue()
                if (context.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {

                    val actionBarHeight = TypedValue.complexToDimensionPixelSize(
                        tv.data,
                        context.resources.displayMetrics
                    )

                    Log.i(
                        "SnackbarUtils",
                        "setSnackBarOnTop : ${context.supportActionBar} :  $actionBarHeight"
                    )

                    params.topMargin = actionBarHeight
                }
            }

            snackBar.view.layoutParams = params
            snackBar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE

        } catch (e: ClassCastException) {
            Log.e("SnackbarUtils", "setSnackBarOnTop : ${e.printStackTrace()} ")

            val params = FrameLayout.LayoutParams(snackBar.view.layoutParams)
            params.gravity = Gravity.TOP

            snackBar.view.layoutParams = params
            snackBar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    fun snackbarWithAction(
        context: Context,
        view: View,
        message: String,
        buttonText: String,
        callback: View.OnClickListener
    ) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction(
            buttonText,
            callback
        )
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.black))
        snackbar.setActionTextColor(Color.GREEN)
        // change snackbar text color
        val snackbarTextId = R.id.snackbar_text
        val textView = snackbar.view.findViewById(snackbarTextId) as TextView
        textView.setTextColor(ContextCompat.getColor(context, (R.color.white)))

        snackbar.show()
    }

    /* fun showToast(context: Context, message: String) {
         Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
     }

     fun showLongToast(context: Context, message: String) {
         Toast.makeText(context, message, Toast.LENGTH_LONG).show()
     }
*/
    /* fun showSuccessToast(context: Context, message: String, toast_success: View) {
         val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
         toast.setText(message)
         toast.view = toast_success
         toast_success.txtToast.setText(message)
         toast.duration = Toast.LENGTH_SHORT
         toast.show()
     }

     fun showErrorToast(context: Context, message: String, toast_error: View) {
         val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
         toast.setText(message)
         toast.view = toast_error
         toast_error.txtToast.setText(message)
         toast.duration = Toast.LENGTH_SHORT
         toast.show()
     }*/
}