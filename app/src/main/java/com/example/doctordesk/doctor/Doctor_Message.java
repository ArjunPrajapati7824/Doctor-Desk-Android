package com.example.doctordesk.doctor;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityDoctorMessageBinding;
import com.example.doctordesk.doctor.Doctor_Appointment;
import com.example.doctordesk.doctor.Doctor_MyPatient;
import com.example.doctordesk.doctor.Doctor_Profile;
import com.example.doctordesk.doctor.Model.MessageModel;

import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class Doctor_Message extends AppCompatActivity {

    ActivityDoctorMessageBinding binding;
    EditText WriteMessage;
    Button btn;
    String messageString="";
    TextView t1;
    ArrayList<String> mobile=new ArrayList<String>();
    private PreferenceManager preferencesManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDoctorMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WriteMessage=binding.DoctorSendMessage;
        btn=binding.DoctorMessageButton;




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore database=FirebaseFirestore.getInstance();
                database.collection(Constants.KEY_COLLECTION_PATIENTS)
                        .get()
                        .addOnCompleteListener(task -> {
                            if(task.isSuccessful() && task.getResult()!=null ){
                                for(QueryDocumentSnapshot queryDocumentSnapshot:task.getResult()) {
                                    MessageModel patientMessage = new MessageModel();
                                    patientMessage.mobileNumber= queryDocumentSnapshot.getString(Constants.KEY_PATIENT_PHONE_NUMBER);
                                    mobile.add(patientMessage.mobileNumber);
                                }

                                if(mobile.toString() != null){
                                    SmsManager sms = SmsManager.getDefault();
                                    Log.d("Num", "onClick: "+mobile);

                                    for (String phoneNumber : mobile) {
                                        Log.i("Numbersssssssssss", "onClick: "+phoneNumber);
                                        sms.sendTextMessage(phoneNumber, null, WriteMessage.getText().toString(), null, null);
                                    }
                                    Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_SHORT).show();
                                    WriteMessage.setText("");



                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Message Not Sent", Toast.LENGTH_SHORT).show();
                                }

                            }else {
                                Toast.makeText(Doctor_Message.this, "Can't fetch data", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });

//        registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                // TODO Auto-generated method stub
//                Bundle msgBundle = intent.getExtras();
//                SmsMessage message[];
//
//                Toast.makeText(context, "In Broadcast Receive", Toast.LENGTH_SHORT).show();
//                if(msgBundle != null) {
//
//                    Object[] pdus = (Object[]) msgBundle.get("pdus");
//                    message = new SmsMessage[1];
//
//                    message[0] = SmsMessage.createFromPdu((byte[])pdus[0]);
//
//                    messageString += "SMS from " + message[0].getOriginatingAddress();
//                    messageString += " :";
//                    messageString += message[0].getMessageBody().toString();
//                    messageString += "\n";
//                }
////                Toast.makeText(context, messageString, Toast.LENGTH_SHORT).show();
//
//            }
//
//        }, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
//
        binding.BnViewDoc.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.MyProfile:
                        startActivity(new Intent(getApplicationContext(), Doctor_Profile.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                    case R.id.MyPtient:
                        startActivity(new Intent(getApplicationContext(), Doctor_MyPatient.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                    case R.id.Appointment:
                        startActivity(new Intent(getApplicationContext(), Doctor_Appointment.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                    case R.id.message:

                        return true;
                }
                return false;
            }
        });

    }

    public void getUsers(){

}
}