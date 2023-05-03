package com.rehlat.rehlatsample.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar


class NetworkConnection {
    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkAvailable(
        context: Context,
        parentLayout: View
    ) {
        val internetConnected =
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
                getNetworkCapabilities(activeNetwork)?.run {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                } ?: false
            }

        if (!internetConnected) {
            Snackbar
                .make(parentLayout, "Network not available! Try reconnecting", Snackbar.LENGTH_LONG)
                .show()
        }
    }
}