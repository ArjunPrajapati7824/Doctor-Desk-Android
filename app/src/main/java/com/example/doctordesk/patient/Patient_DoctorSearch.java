package com.example.doctordesk.patient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.doctordesk.Patientmodels.DoctorModel;
import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityPatientDoctorSearchBinding;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Patient_DoctorSearch extends AppCompatActivity {
    ActivityPatientDoctorSearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientDoctorSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.BnView.setSelectedItemId(R.id.SearchDoctor);

        binding.BnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.MyProfile:
                        startActivity(new Intent(getApplicationContext(), Patient_MyProfile.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                    case R.id.SearchDoctor:
                        return true;
                    case R.id.History:
                        startActivity(new Intent(getApplicationContext(), Patient_TreatmentHistory.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;

                    case R.id.Appointment:
                        startActivity(new Intent(getApplicationContext(), Patient_Appointment.class));
                        overridePendingTransition(0 ,0);
                        finish();
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

    }}