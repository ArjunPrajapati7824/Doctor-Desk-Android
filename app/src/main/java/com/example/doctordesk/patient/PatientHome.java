package com.example.doctordesk.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

public class PatientHome extends AppCompatActivity {
BottomNavigationView BnView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        BnView=findViewById(R.id.BnView);//Find Bottom Navigation BAr form XML File

        BnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            //Same as On Click Listener that show the fragment That Selected from tHe navigation view
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();// Take Id from Menu File In form of Int to make easy comperision

                switch (id) {

                    case R.id.MyDoctor:
                        LoadFragment(new FragPatientMyDoctor(),false);
                        break;

                    case R.id.SearchDoctor:
                        LoadFragment(new FragPatientDoctorSearch(),false);
                        break;

                    case R.id.MyProfile:
                        LoadFragment(new FragMyProfile(),true);
                        break;

                    case R.id.History:
                        LoadFragment(new FragTreatmentHistory(),false);
                        break;

                    case R.id.PatientAppointment:
                        LoadFragment(new FragPatientAppointment(),false);
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
        ft.replace( R.id.Container,fragment);//Container is Id Of frame Layout from XML file Patient Home Page
        // it will add the Selected file on frameLayout
        ft.commit();// commit the Above transaction



    }
}