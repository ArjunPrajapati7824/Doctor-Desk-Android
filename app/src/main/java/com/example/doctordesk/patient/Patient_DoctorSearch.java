package com.example.doctordesk.patient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityPatientDoctorSearchBinding;
import com.example.doctordesk.patient.adapters.DoctorListAdapter;
import com.example.doctordesk.patient.models.DoctorModel;
import com.example.doctordesk.utilities.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Patient_DoctorSearch extends AppCompatActivity {
    ActivityPatientDoctorSearchBinding binding;


    FirebaseFirestore db;

    DoctorListAdapter doctorListAdapter;

    ArrayList<DoctorModel> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPatientDoctorSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db=FirebaseFirestore.getInstance();
        setListeners();

        getDoctors();


    }



    public void getDoctors(){

        arrayList=new ArrayList<>();
        arrayList.clear();
        db.collection(Constants.KEY_COLLECTION_DOCTORS).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error==null){
                    List<DoctorModel> data =value.toObjects(DoctorModel.class);
                    arrayList.addAll(data);
                    binding.searchdr.setLayoutManager(new LinearLayoutManager(Patient_DoctorSearch.this));
                    binding.searchdr.setAdapter(new DoctorListAdapter(Patient_DoctorSearch.this,arrayList));
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_doctor,menu);
        MenuItem menuItem =menu.findItem(R.id.search);
        SearchView searchView= (SearchView) menuItem.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                doctorSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                doctorSearch(s);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    //find doctore using search
    private void doctorSearch(String str){

        db.collection(Constants.KEY_COLLECTION_DOCTORS).whereEqualTo(Constants.KEY_DOCTOR_NAME,str)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot doc:task.getResult()){
                            DoctorModel doctorModel=new DoctorModel(doc.getString(Constants.KEY_DOCTOR_NAME),
                                    doc.getString(Constants.KEY_CLINIC_NAME),
                                    doc.getString(Constants.KEY_CLINIC_ADDRESS),
                                    doc.getString(Constants.KEY_SPECIALIZATION),
                                    doc.getString(Constants.KEY_DOCTOR_ID));
                            arrayList.clear();
                            arrayList.add(doctorModel);
                        }
                        doctorListAdapter=new DoctorListAdapter(Patient_DoctorSearch.this,arrayList);
                        binding.searchdr.setAdapter(doctorListAdapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    private void setListeners() {
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
                        return true;
                    case R.id.PAppointment:
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
    }

}