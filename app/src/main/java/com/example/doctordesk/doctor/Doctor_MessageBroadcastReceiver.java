package com.example.doctordesk.doctor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class Doctor_MessageBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent intent) {
        Bundle msgBundle = intent.getExtras();
        SmsMessage message[];
        String messageString = "";
        Toast.makeText(arg0, "In Broadcast Receive", Toast.LENGTH_SHORT).show();
        if(msgBundle != null) {
            Object[] pdus = (Object[]) msgBundle.get("pdus");
            message = new SmsMessage[1];

            message[0] = SmsMessage.createFromPdu((byte[])pdus[0]);
            messageString += "SMS from " + message[0].getOriginatingAddress();
            messageString += " :";
            messageString += message[0].getMessageBody().toString();
            messageString += "\n";
        }
        Toast.makeText(arg0, messageString, Toast.LENGTH_SHORT).show();
    }
}
