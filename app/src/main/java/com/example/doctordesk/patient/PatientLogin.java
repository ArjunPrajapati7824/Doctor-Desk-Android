package com.example.doctordesk.patient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityPatientLoginBinding;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class PatientLogin extends AppCompatActivity {

    private ActivityPatientLoginBinding binding;
    private PreferenceManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPatientLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferencesManager =new PreferenceManager(getApplicationContext());

        if(preferencesManager.getBoolean(Constants.KEY_IS_PATIENT_SIGNED_IN)){
            Intent intent =new Intent(getApplicationContext(),Patient_MyProfile.class);
            startActivity(intent);
            finish();
        }
        setListeners();


    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners(){


        binding.showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });

        binding.PatientLoginPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                Drawable drawable = binding.PatientLoginPass.getCompoundDrawables()[DRAWABLE_RIGHT];

                if (drawable != null && event.getAction() == MotionEvent.ACTION_UP && event.getRawX() >= (binding.PatientLoginPass.getRight() - drawable.getBounds().width())) {
                    togglePasswordVisibility();
                    return true;
                }
                return false;
       }
    });

        binding.RegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PatientLogin.this, PatientRegister.class);
                startActivity(i);
            }
        });

        binding.PatientLogin.setOnClickListener(view -> {
            if(isValidSignInDetails())
                SignIn();

        });


    }

    private void togglePasswordVisibility() {
        if (binding.PatientLoginPass.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            // Show password
            binding.PatientLoginPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            binding.showPassword.setImageResource(R.drawable.ic_visibility_off);
        } else {
            // Hide password
            binding.PatientLoginPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            binding.showPassword.setImageResource(R.drawable.ic_visibility);
        }

        // Move cursor to the end of the text
        binding.PatientLoginPass.setSelection(binding.PatientLoginPass.getText().length());
    }
    private void SignIn(){
        Loading(true);

        FirebaseFirestore database= FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_PATIENTS)
                .whereEqualTo(Constants.KEY_PATIENT_PHONE_NUMBER,binding.PatientLoginNumber.getText().toString())
                .whereEqualTo(Constants.KEY_PATIENT_PASSWORD,binding.PatientLoginPass.getText().toString())
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful() && task.getResult()!=null && task.getResult().getDocuments().size()>0){
                        DocumentSnapshot documentSnapshot=task.getResult().getDocuments().get(0);

                        preferencesManager.putBoolean(Constants.KEY_IS_PATIENT_SIGNED_IN,true);
                        preferencesManager.putString(Constants.KEY_PATIENT_ID,documentSnapshot.getId());
                        preferencesManager.putString(Constants.KEY_PATIENTS_NAME,documentSnapshot.getString(Constants.KEY_PATIENTS_NAME));
                        preferencesManager.putString(Constants.KEY_PATIENT_GENDER,documentSnapshot.getString(Constants.KEY_PATIENT_GENDER));
                        preferencesManager.putString(Constants.KEY_PATIENT_PHONE_NUMBER,documentSnapshot.getString(Constants.KEY_PATIENT_PHONE_NUMBER));
                        preferencesManager.putString(Constants.KEY_PATIENT_CITY,documentSnapshot.getString(Constants.KEY_PATIENT_CITY));
                        preferencesManager.putString(Constants.KEY_PATIENT_BLOOD_GROUP,documentSnapshot.getString(Constants.KEY_PATIENT_BLOOD_GROUP));
                        preferencesManager.putString(Constants.KEY_PATIENT_AGE,documentSnapshot.getString(Constants.KEY_PATIENT_AGE));
                        preferencesManager.putString(Constants.KEY_PATIENT_WEIGHT,documentSnapshot.getString(Constants.KEY_PATIENT_WEIGHT));
                        Intent i_login=new Intent(getApplicationContext(), Patient_MyProfile.class);
                        i_login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i_login);
                    }else {
                        Loading(false);
                        ShowToast("Invalid Mobile number Or  password");
                    }

                });
    }
    private void Loading(boolean isLoading) {
        if (isLoading) {
            binding.PatientLogin.setVisibility(View.INVISIBLE);
            binding.ProgressBar.setVisibility(View.VISIBLE);
        } else {
            binding.PatientLogin.setVisibility(View.VISIBLE);
            binding.ProgressBar.setVisibility(View.INVISIBLE);

        }

    }


    private void ShowToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private Boolean isValidSignInDetails(){
        if(binding.PatientLoginNumber.getText().toString().trim().isEmpty()){
            ShowToast("Enter Mobile Number");
            return false;
        }else if (binding.PatientLoginNumber.getText().toString().length() != 10) {
            ShowToast("Enter Valid Mobile Number");
            return false;
        } else if (binding.PatientLoginPass.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Password");
            return false;
        }else {
            return true;
        }

    }
}