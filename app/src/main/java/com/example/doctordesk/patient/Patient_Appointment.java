package com.example.doctordesk.patient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityPatientAppointmentBinding;
import com.example.doctordesk.doctor.Doctor_Appointment;
import com.example.doctordesk.doctor.Doctor_Appointment_Adapter;
import com.example.doctordesk.doctor.Doctor_Message;
import com.example.doctordesk.doctor.Doctor_MyPatient;
import com.example.doctordesk.doctor.ModelPatientList;
import com.example.doctordesk.patient.adapters.DoctorListAdapter;
import com.example.doctordesk.patient.adapters.My_Appointment_Adapter;
import com.example.doctordesk.patient.models.DoctorModel;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.doctordesk.patient.models.myAppointmentDoctorModel;


import java.util.ArrayList;
import java.util.List;

public class Patient_Appointment extends AppCompatActivity {
    ActivityPatientAppointmentBinding binding;

    ArrayList <myAppointmentDoctorModel> appointmentArray;
    My_Appointment_Adapter adapter;
    private PreferenceManager preferencesManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferencesManager =new PreferenceManager(getApplicationContext());

        appointmentArray=new ArrayList<>();

      adapter=new My_Appointment_Adapter(appointmentArray);
        binding.BnView.setSelectedItemId(R.id.Appointment);

        binding.myAppointmentRecyclerView.setLayoutManager(new LinearLayoutManager(Patient_Appointment.this));

        my_Doctors();

        binding.BnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.PMyProfile:
                        startActivity(new Intent(getApplicationContext(), Patient_MyProfile.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                    case R.id.SearchDoctor:
                        startActivity(new Intent(getApplicationContext(), Patient_DoctorSearch.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;


                    case R.id.PAppointment:
                        return true;
                    case R.id.MyDoctor:
                        startActivity(new Intent(getApplicationContext(), Patient_MyDoctor.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                }
                return false;
            }
        });

    }

    public void my_Doctors() {


        FirebaseFirestore database = FirebaseFirestore.getInstance();
//        database.collection(Constants.KEY_COLLECTION_APPOINTMENTS).document()





//        database.collection(Constants.KEY_COLLECTION_APPOINTMENTS)
//                .whereEqualTo(Constants.KEY_PATIENT_ID, preferencesManager.getString(Constants.KEY_PATIENT_ID))
//                .whereEqualTo(Constants.KEY_APPOINTMENT_STATUS, "true")
//                .get()
//                .addOnSuccessListener(queryDocumentSnapshots -> {
//                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
//                        for(DocumentSnapshot d:list){
//                            myAppointmentDoctorModel doctorModel=d.toObject(myAppointmentDoctorModel.class);
//                            appointmentArray.add(doctorModel);
//                        }
//
//                        binding.myAppointmentRecyclerView.setAdapter(new My_Appointment_Adapter(appointmentArray));
//
//                })
//                .addOnFailureListener(e -> {
//                    Toast.makeText(this, "no data ", Toast.LENGTH_SHORT).show();
//                });


//        database.collection(Constants.KEY_COLLECTION_APPOINTMENTS).whereEqualTo(Constants.KEY_PATIENT_ID,preferencesManager.getString(Constants.KEY_PATIENT_ID))
//                .whereEqualTo(Constants.KEY_APPOINTMENT_STATUS,"true")
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
//                        for(DocumentSnapshot d:list){
//                            myAppointmentDoctorModel doctorModel=d.toObject(myAppointmentDoctorModel.class);
//                            appointmentArray.add(doctorModel);
//                        }
//
//                        binding.myAppointmentRecyclerView.setAdapter(new My_Appointment_Adapter(appointmentArray));
//
//                    }
//                });

        database.collection(Constants.KEY_COLLECTION_APPOINTMENTS).whereEqualTo(Constants.KEY_PATIENT_ID,preferencesManager.getString(Constants.KEY_PATIENT_ID))
//                .whereEqualTo(Constants.KEY_APPOINTMENT_STATUS,"True")
                .get()

                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){
                            myAppointmentDoctorModel doctorModel=d.toObject(myAppointmentDoctorModel.class);
                            appointmentArray.add(doctorModel);
                        }
                        binding.myAppointmentRecyclerView.setAdapter(new My_Appointment_Adapter(appointmentArray));


                    }
                });

    }
    }