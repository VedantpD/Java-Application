package com.example.javaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallConfig;
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallFragment;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;
import java.util.HashSet;



public class MainActivity extends AppCompatActivity {

    private Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCall = findViewById(R.id.btnCall);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Check if your app has notification access
            if (!isNotificationServiceEnabled()) {
                // If not, open the notification access settings for your app
                Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
                startActivity(intent);
            }
        }
        //addCallFragment();
        initCallInviteFragment();
        setClicks();
    }

    private void setClicks() {
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String targetUserID = "vedant_ID" ; // The ID of the user you want to call.
                String targetUserName = "vedant"; // The username of the user you want to call.
                Context context = getApplicationContext(); // Android context.

                ZegoSendCallInvitationButton button = new ZegoSendCallInvitationButton(context);
                button.setIsVideoCall(false);
                button.setResourceID("zego_uikit_call"); // Please fill in the resource ID name that has been configured in the ZEGOCLOUD's console here.
                button.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID,targetUserName)));
                button.performClick();
            }
        });

    }

    private void initCallInviteFragment() {
        Application application = getApplication(); // Android's application context
        long appID = 841776771;   // yourAppID
        String appSign = "3beba938b8d23e3b3c8f52ea4d8cfe08c68002de936db5b60935b9c261e7d95d";  // yourAppSign
        String userID = "ashray_ID"; // yourUserID, userID should only contain numbers, English characters, and '_'.
        String userName = "ashray";   // yourUserName

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();

        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
    }

    public void addCallFragment() {
        long appID = 841776771;
        String appSign = "3beba938b8d23e3b3c8f52ea4d8cfe08c68002de936db5b60935b9c261e7d95d";

        String callID = "vedant_call_ID";
        String userID = "vedant_ID";
        String userName = "vedant";

        // You can also use GroupVideo/GroupVoice/OneOnOneVoice to make more types of calls.
        ZegoUIKitPrebuiltCallConfig config = ZegoUIKitPrebuiltCallConfig.oneOnOneVideoCall();

        ZegoUIKitPrebuiltCallFragment fragment = ZegoUIKitPrebuiltCallFragment.newInstance(
                appID, appSign, callID, userID, userName,config);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow();
    }
    private boolean isNotificationServiceEnabled() {
        // Check if your app has notification access
        String packageName = getPackageName();
        String flat = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        return flat != null && flat.contains(packageName);
    }
}