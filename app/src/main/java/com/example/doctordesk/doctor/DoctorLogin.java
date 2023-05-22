package com.example.doctordesk.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.doctordesk.MainActivity;
import com.example.doctordesk.databinding.ActivityDoctorLoginBinding;
import com.example.doctordesk.patient.Patient_MyProfile;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DoctorLogin extends AppCompatActivity {


    ActivityDoctorLoginBinding binding;
    private PreferenceManager preferencesManager;

    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDoctorLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferencesManager =new PreferenceManager(getApplicationContext());

        if(preferencesManager.getBoolean(Constants.KEY_IS_DOCTOR_SIGNED_IN)){
            Intent intent =new Intent(getApplicationContext(), Doctor_Profile.class);
            startActivity(intent);
            finish();
        }

        setListeners();
    }

    private void setListeners(){


        binding.RegisterText.setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(),DoctorRegistretion.class)));

        binding.DoctorLogin.setOnClickListener(view -> {
            if(isValidSignInDetails())
                SignIn();
        });
    }
    private void SignIn(){
        Loading(true);
        FirebaseFirestore database= FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_DOCTORS)
                .whereEqualTo(Constants.KEY_DOCTOR_PHONENUMBER,binding.DoctorLoginNumber.getText().toString())
                .whereEqualTo(Constants.KEY_DOCTOR_PASSWORD,binding.DoctorLoginPass.getText().toString())
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful() && task.getResult()!=null && task.getResult().getDocuments().size()>0){
                        DocumentSnapshot documentSnapshot=task.getResult().getDocuments().get(0);

                        //Code fpr Getting data from Database And Sending to Profile
                        //Start here


                        preferencesManager.putBoolean(Constants.KEY_IS_DOCTOR_SIGNED_IN,true);
                        preferencesManager.putString(Constants.KEY_DOCTOR_ID,documentSnapshot.getId());
                        preferencesManager.putString(Constants.KEY_DOCTOR_NAME,documentSnapshot.getString(Constants.KEY_DOCTOR_NAME));
                        preferencesManager.putString(Constants.KEY_CLINIC_NAME,documentSnapshot.getString(Constants.KEY_CLINIC_NAME));
                        preferencesManager.putString(Constants.KEY_DOCTOR_PHONENUMBER,documentSnapshot.getString(Constants.KEY_DOCTOR_PHONENUMBER));
                        preferencesManager.putString(Constants.KEY_CLINIC_ADDRESS,documentSnapshot.getString(Constants.KEY_CLINIC_ADDRESS));
                        preferencesManager.putString(Constants.KEY_DOCTOR_REGISTRATION_NUMBER,documentSnapshot.getString(Constants.KEY_DOCTOR_REGISTRATION_NUMBER));
                        preferencesManager.putString(Constants.KEY_SPECIALIZATION,documentSnapshot.getString(Constants.KEY_SPECIALIZATION));
                        Intent i_login=new Intent(getApplicationContext(),Doctor_Profile.class);
                        i_login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i_login);
                    }else {
                        Loading(false);
                        ShowToast("Invalid Mobile number and password");
                    }

                });
    }
    private void Loading(boolean isLoading) {
        if (isLoading) {
            binding.DoctorLogin.setVisibility(View.INVISIBLE);
            binding.ProgressBar.setVisibility(View.VISIBLE);
        } else {
            binding.DoctorLogin.setVisibility(View.VISIBLE);
            binding.ProgressBar.setVisibility(View.INVISIBLE);

        }

    }


        private void ShowToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private Boolean isValidSignInDetails(){
        if(binding.DoctorLoginNumber.getText().toString().trim().isEmpty()){
            ShowToast("Enter Mobile Number");
            return false;
        }else if (binding.DoctorLoginNumber.getText().toString().length() != 10) {
            ShowToast("Enter Valid Mobile Number");
            return false;
        } else if (binding.DoctorLoginPass.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Password");
            return false;
        }else {
            return true;
        }

    }

}