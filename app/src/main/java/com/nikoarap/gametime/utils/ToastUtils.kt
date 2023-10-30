package com.nikoarap.gametime.utils

import android.content.Context
import android.widget.Toast
import com.nikoarap.gametime.utils.Constants.Companion.ERROR_TOAST_MESSAGE

class ToastUtils {

    companion object {
        fun showErrorToast(context: Context) {
            Toast.makeText(context, ERROR_TOAST_MESSAGE, Toast.LENGTH_SHORT).show()
        }
    }

}