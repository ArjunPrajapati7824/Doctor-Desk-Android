package com.example.doctordesk.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityPatientDoctorSearchBinding;
import com.example.doctordesk.databinding.ActivityPatientMyDoctorBinding;
import com.example.doctordesk.patient.adapters.My_Appointment_Adapter;
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

public class Patient_MyDoctor extends AppCompatActivity {
private ActivityPatientMyDoctorBinding binding;

    ArrayList<myAppointmentDoctorModel> appointmentArray;
    myDoctorAdapter adapter;
    private PreferenceManager preferencesManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityPatientMyDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferencesManager =new PreferenceManager(getApplicationContext());


        appointmentArray=new ArrayList<>();

        adapter=new myDoctorAdapter(appointmentArray);

        binding.BnView.setSelectedItemId(R.id.MyDoctor);

        binding.myDoctorRecyclerView.setLayoutManager(new LinearLayoutManager(Patient_MyDoctor.this));

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
                        startActivity(new Intent(getApplicationContext(), Patient_Appointment.class));
                        overridePendingTransition(0 ,0);
                        finish();
                        return true;
                    case R.id.MyDoctor:

                        return true;
                }
                return false;
            }
        });
    }

    private void my_Doctors(){
        FirebaseFirestore database = FirebaseFirestore.getInstance();

        database.collection(Constants.KEY_COLLECTION_APPOINTMENTS).whereEqualTo(Constants.KEY_PATIENT_ID,preferencesManager.getString(Constants.KEY_PATIENT_ID))
                .whereEqualTo(Constants.KEY_APPOINTMENT_STATUS,"Accept")
                .get()

                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){
                            myAppointmentDoctorModel doctorModel=d.toObject(myAppointmentDoctorModel.class);
                            appointmentArray.add(doctorModel);
                        }
                        binding.myDoctorRecyclerView.setAdapter(new myDoctorAdapter(appointmentArray));


                    }
                });

    }
}