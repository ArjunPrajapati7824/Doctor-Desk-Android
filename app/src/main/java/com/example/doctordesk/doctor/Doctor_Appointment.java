package com.example.doctordesk.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityDoctorAppointmentBinding;
import com.example.doctordesk.patient.models.DoctorModel;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Doctor_Appointment extends AppCompatActivity {
    private ActivityDoctorAppointmentBinding binding;
    private PreferenceManager preferencesManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDoctorAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferencesManager =new PreferenceManager(getApplicationContext());


        ArrayList <ModelPatientList> PatientsArray=new ArrayList<>();

        Doctor_Appointment_Adapter adapter=new Doctor_Appointment_Adapter(PatientsArray);

        binding.BnViewDoc.setSelectedItemId(R.id.Appointment);

        binding.DoctorAppointmentsRCV.setLayoutManager(new LinearLayoutManager(Doctor_Appointment.this));


        Date currentDate = new Date();

// Create a SimpleDateFormat object to format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy", Locale.getDefault());

// Format the date as a string
        String formattedDate = dateFormat.format(currentDate);
        FirebaseFirestore database= FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_APPOINTMENTS).whereEqualTo(Constants.KEY_DOCTOR_ID,preferencesManager.getString(Constants.KEY_DOCTOR_ID))
                .whereEqualTo(Constants.KEY_APPOINTMENT_STATUS,"Pending")
//                .whereGreaterThan(Constants.KEY_APPOINTMENT_DATE,"22-5-2023")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        if(list.size() == 0)
                        {
                            binding.showText.setVisibility(View.VISIBLE);
                        }
                        for(DocumentSnapshot d:list){
                            ModelPatientList doctorModel=d.toObject(ModelPatientList.class);
                            PatientsArray.add(doctorModel);
                        }
                        adapter.notifyDataSetChanged();

                    }
                });


        binding.DoctorAppointmentsRCV.setAdapter(adapter);

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
                        return true;
                    case R.id.message:
                        startActivity(new Intent(getApplicationContext(), Doctor_Message.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }
}