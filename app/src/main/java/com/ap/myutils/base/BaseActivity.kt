package com.ap.myutils.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import java.util.*


abstract class BaseActivity<VB : ViewBinding>(private val Inflate: (LayoutInflater) -> VB) :
    AppCompatActivity() {
    private val TAG = "BaseActivity"

    val rootView: VB by lazy { Inflate(layoutInflater) }

    val String.toast: Unit
        get() {
            Toast.makeText(this@BaseActivity, this, Toast.LENGTH_SHORT).show()
        }

    val String.toastLong: Unit
        get() {
            Toast.makeText(this@BaseActivity, this, Toast.LENGTH_SHORT).show()
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(rootView.root)

    }

  /*  fun showSnackBar(view: View, msg: String, type: SnackBarUtils.SnackBarType) {
        when (type) {

            SnackBarUtils.SnackBarType.SUCCESS -> {
                SnackBarUtils.success(this@BaseActivity, view, msg)
            }
            SnackBarUtils.SnackBarType.WARNING -> {
                SnackBarUtils.warning(this@BaseActivity, view, msg)
            }
            SnackBarUtils.SnackBarType.ERROR -> {
                SnackBarUtils.error(this@BaseActivity, view, msg)
            }
        }
    }*/


    /*  var progressDialog: AlertDialog? = null

      fun showProgress(msg: String, cancelable: Boolean) {
          //showProgressDialog(this, "", msg, cancelable)
          if (progressDialog != null && progressDialog!!.isShowing) {
              progressDialog!!.dismiss()
          }

          progressDialog = ProgressDialogWithCustomLoader.showProgressDialog(
              this,
              "",
              msg,
              cancelable,
              R.string.bottom_sheet_behavior
          )
          progressDialog!!.show()
      }

      fun hideProgress() {
          //dismissProgressDialog()
          if (progressDialog != null && progressDialog!!.isShowing) {
              progressDialog!!.dismiss()
          }
      }*/

}






