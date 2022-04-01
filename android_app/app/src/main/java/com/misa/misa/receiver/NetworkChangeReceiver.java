package com.misa.misa.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.misa.misa.utils.InternetUtils;
import com.misa.misa.utils.OnNetworkListener;

public class NetworkChangeReceiver extends BroadcastReceiver {

    OnNetworkListener onNetworkListener;

    public void setOnNetworkListener(OnNetworkListener onNetworkListener) {
        this.onNetworkListener = onNetworkListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!InternetUtils.isNetworkConnected(context)) {
            onNetworkListener.onNetworkDisconnected();
        } else {
            onNetworkListener.onNetworkConnected();
        }
    }
}
