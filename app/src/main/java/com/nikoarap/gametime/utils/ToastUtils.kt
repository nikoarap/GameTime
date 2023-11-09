package com.nikoarap.gametime.utils

import android.content.Context
import android.widget.Toast
import com.nikoarap.gametime.R

class ToastUtils {

    companion object {
        fun showErrorToast(context: Context) {
            Toast.makeText(context, context.resources.getString(R.string.error_toast_msg), Toast.LENGTH_SHORT).show()
        }
    }

}