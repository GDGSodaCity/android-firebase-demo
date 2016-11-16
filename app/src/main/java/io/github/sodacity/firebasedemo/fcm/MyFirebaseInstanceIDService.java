package io.github.sodacity.firebasedemo.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Project: GDGSodacityFirebaseDemo
 * Package: io.github.sodacity.firebasedemo.fcm
 * Created by drew.heavner on 11/16/16.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseInstanceIDSer";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed Token: " + refreshToken);

        /*
         * Send the new Cloud Messaging token to the server so that your service can use it
         * to send push notifications to your phone
         */
        sendRegistrationToServer(refreshToken);

    }

    private void sendRegistrationToServer(String token){
        // TODO: Send token to your server to receive push messages
        // ....
    }

}
