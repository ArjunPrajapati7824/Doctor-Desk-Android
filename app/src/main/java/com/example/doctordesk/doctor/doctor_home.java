package com.example.doctordesk.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.doctordesk.R;
import com.example.doctordesk.patient.FragMyProfile;
import com.example.doctordesk.patient.FragPatientAppointment;
import com.example.doctordesk.patient.FragPatientDoctorSearch;
import com.example.doctordesk.patient.FragPatientMyDoctor;
import com.example.doctordesk.patient.FragTreatmentHistory;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class doctor_home extends AppCompatActivity {

    BottomNavigationView BnView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        BnView=findViewById(R.id.BnViewDoc);//Find Bottom Navigation BAr form XML File

        BnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            //Same as On Click Listener that show the fragment That Selected from tHe navigation view
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();// Take Id from Menu File In form of Int to make easy comperision

                switch (id) {

                    case R.id.MyPtient:
                        LoadFragment(new Doctor_MyPatient(),false);
                        break;

                    case R.id.Appointment:
                        LoadFragment(new Doctor_Appointment(),false);
                        break;

                    case R.id.MyProfile:
                        LoadFragment(new Doctor_Profile(),true);
                        break;

                    case R.id.message:
                        LoadFragment(new Doctor_Message(),false);
                        break;



                }

                return true;
            }
        });
        BnView.setSelectedItemId(R.id.MyProfile);
    }

    public void LoadFragment(Fragment fragment, boolean flag){

        //this method Load the fragment By Using Fragment Manager and Fragment Transaction
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace( R.id.ContainerDoc,fragment);//Container is Id Of frame Layout from XML file Patient Home Page
        // it will add the Selected file on frameLayout
        ft.commit();// commit the Above transaction



    }
}