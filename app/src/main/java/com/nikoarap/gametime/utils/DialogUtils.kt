package com.nikoarap.gametime.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.nikoarap.gametime.R

/**
 * A utility class for creating and displaying dialogs.
 */
class DialogUtils {

    companion object {

        /**
         * Shows a connectivity dialog with options to enable Wi-Fi, cellular data, or cancel.
         *
         * @param context       The context in which to display the dialog.
         * @return              The created AlertDialog.
         */
        fun showConnectivityDialog(context: Context): AlertDialog {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(context.resources.getString(R.string.conn_dialog_title))
            builder.setMessage(context.resources.getString(R.string.conn_dialog_msg))
            builder.setCancelable(false)

            builder.setPositiveButton(context.resources.getString(R.string.conn_dialog_option_wifi)) { _, _ ->
                // Open Wi-Fi settings
                context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
            builder.setNegativeButton(context.resources.getString(R.string.conn_dialog_option_data)) { _, _ ->
                // Open cellular data settings
                context.startActivity(Intent(Settings.ACTION_DATA_ROAMING_SETTINGS))
            }
            builder.setNeutralButton(context.resources.getString(R.string.conn_dialog_option_cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            return builder.create()
        }
    }

}