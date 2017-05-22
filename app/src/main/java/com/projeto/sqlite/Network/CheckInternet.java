package com.projeto.sqlite.Network;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Andre on 22/05/2017.
 */

public class CheckInternet {
    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
