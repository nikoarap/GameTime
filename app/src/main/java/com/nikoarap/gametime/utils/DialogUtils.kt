package com.nikoarap.gametime.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.nikoarap.gametime.utils.Constants.Companion.CONN_DIALOG_MESSAGE
import com.nikoarap.gametime.utils.Constants.Companion.CONN_DIALOG_OPTION_CANCEL
import com.nikoarap.gametime.utils.Constants.Companion.CONN_DIALOG_OPTION_DATA
import com.nikoarap.gametime.utils.Constants.Companion.CONN_DIALOG_OPTION_WIFI
import com.nikoarap.gametime.utils.Constants.Companion.CONN_DIALOG_TITLE

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
            builder.setTitle(CONN_DIALOG_TITLE)
            builder.setMessage(CONN_DIALOG_MESSAGE)
            builder.setCancelable(false)

            builder.setPositiveButton(CONN_DIALOG_OPTION_WIFI) { _, _ ->
                // Open Wi-Fi settings
                context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
            builder.setNegativeButton(CONN_DIALOG_OPTION_DATA) { _, _ ->
                // Open cellular data settings
                context.startActivity(Intent(Settings.ACTION_DATA_ROAMING_SETTINGS))
            }
            builder.setNeutralButton(CONN_DIALOG_OPTION_CANCEL) { dialog, _ ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()

            return dialog
        }
    }

}