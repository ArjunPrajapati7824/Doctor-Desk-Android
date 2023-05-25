package com.example.doctordesk.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityDoctorMyPatientBinding;
import com.example.doctordesk.doctor.Model.myPatientsModel;
import com.example.doctordesk.patient.Patient_MyDoctor;
import com.example.doctordesk.patient.adapters.myDoctorAdapter;
import com.example.doctordesk.patient.models.myAppointmentDoctorModel;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Doctor_MyPatient extends AppCompatActivity {
    ActivityDoctorMyPatientBinding binding;

    ArrayList<myPatientsModel> myPatientsArray;
    myPatientsAdapter adapter;
    private PreferenceManager preferencesManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDoctorMyPatientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferencesManager =new PreferenceManager(getApplicationContext());

        myPatientsArray=new ArrayList<>();

        adapter=new myPatientsAdapter(myPatientsArray);

        binding.myPatientRecyclerView.setLayoutManager(new LinearLayoutManager(Doctor_MyPatient.this));

        my_Patients();



        binding.BnViewDoc.setSelectedItemId(R.id.MyPtient);
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
                        return true;
                    case R.id.Appointment:
                        startActivity(new Intent(getApplicationContext(), Doctor_Appointment.class));
                        overridePendingTransition(0 ,0);
                        finish();
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

    private void my_Patients(){
        FirebaseFirestore database = FirebaseFirestore.getInstance();

        database.collection(Constants.KEY_COLLECTION_APPOINTMENTS).whereEqualTo(Constants.KEY_DOCTOR_ID,preferencesManager.getString(Constants.KEY_DOCTOR_ID))
                .whereEqualTo(Constants.KEY_APPOINTMENT_STATUS,"Accept")
                .get()

                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        if(list.size() == 0)
                        {
                            binding.showTextPatient.setVisibility(View.VISIBLE);
                        }
                        for(DocumentSnapshot d:list){
                            myPatientsModel myPatientsModel =d.toObject(myPatientsModel.class);
                            myPatientsArray.add(myPatientsModel);
                        }
                        binding.myPatientRecyclerView.setAdapter(new myPatientsAdapter(myPatientsArray));


                    }
                });

    }
}