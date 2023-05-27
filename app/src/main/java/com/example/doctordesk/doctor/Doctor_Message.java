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

        preferencesManager=new PreferenceManager(getApplicationContext());




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore database=FirebaseFirestore.getInstance();
                database.collection(Constants.KEY_COLLECTION_APPOINTMENTS)
                        .whereEqualTo(Constants.KEY_DOCTOR_ID,preferencesManager.getString(Constants.KEY_DOCTOR_ID))
                        .whereEqualTo(Constants.KEY_APPOINTMENT_STATUS,"Accept")
                        .get()
                        .addOnCompleteListener(task -> {
                            if(task.isSuccessful() && task.getResult()!=null ){
                                for(QueryDocumentSnapshot queryDocumentSnapshot:task.getResult()) {
                                    MessageModel patientMessage = new MessageModel();
                                    patientMessage.mobileNumber= queryDocumentSnapshot.getString(Constants.KEY_APPOINTMENT_PHONE_NUMBER);
                                    mobile.add(patientMessage.mobileNumber);
                                }

                                if(mobile.toString() != null){
                                    SmsManager sms = SmsManager.getDefault();
                                    Log.d("Num", "onClick: "+mobile);

                                    for (String phoneNumber : mobile) {
                                        Log.i("Numbers", "onClick: "+phoneNumber);
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

}