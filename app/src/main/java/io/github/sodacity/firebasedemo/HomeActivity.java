package io.github.sodacity.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class HomeActivity extends AppCompatActivity {

    private FirebaseRemoteConfig remoteConfig;
    private TextView remoteMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle(R.string.activity_home);

        Button forceCrash = (Button) findViewById(R.id.action_crash);
        forceCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseCrash.log("We are about to crash, hang on!!!");
                throw new RuntimeException("We have crashed!! Uh-Oh");
            }
        });

        remoteConfig = FirebaseRemoteConfig.getInstance();

        // Setup remote config
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        remoteConfig.setConfigSettings(configSettings);

        remoteMessage = (TextView) findViewById(R.id.remote_message);
    }

    @Override
    protected void onResume() {
        super.onResume();

        fetchConfiguration();

    }

    void fetchConfiguration(){
        displayWelcomeMessage();

        long cacheExpiration = 3600; // 1 hour in seconds.
        // If in developer mode cacheExpiration is set to 0 so each fetch will retrieve values from
        // the server.
        if (remoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        // Potentially refresh from server
        remoteConfig.fetch(cacheExpiration).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                remoteConfig.activateFetched();
                displayWelcomeMessage();
            }
        });
    }

    void displayWelcomeMessage(){
        remoteMessage.setText(remoteConfig.getString("demo_message"));
    }
}
