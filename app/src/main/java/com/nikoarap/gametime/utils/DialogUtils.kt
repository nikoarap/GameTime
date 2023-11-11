package com.nikoarap.gametime.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.nikoarap.gametime.R

class DialogUtils {

    companion object {

        /**
         * Creates a dialog prompting the user to check their connectivity settings.
         * The dialog provides options to open Wi-Fi settings, cellular data settings, or cancel the operation.
         * @return An [AlertDialog] instance representing the connectivity dialog.
         */
        fun Context.createConnectivityDialog(): AlertDialog {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.conn_dialog_title))
            builder.setMessage(getString(R.string.conn_dialog_msg))
            builder.setCancelable(false)

            builder.setPositiveButton(getString(R.string.conn_dialog_option_wifi)) { _, _ ->
                // Open Wi-Fi settings
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
            builder.setNegativeButton(getString(R.string.conn_dialog_option_data)) { _, _ ->
                // Open cellular data settings
                startActivity(Intent(Settings.ACTION_DATA_ROAMING_SETTINGS))
            }
            builder.setNeutralButton(getString(R.string.conn_dialog_option_cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            return builder.create()
        }
    }
}