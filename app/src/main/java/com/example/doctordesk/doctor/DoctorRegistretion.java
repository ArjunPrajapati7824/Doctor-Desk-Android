package com.example.doctordesk.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityDoctorRegistretionBinding;
import com.example.doctordesk.utilities.PreferenceManager;

public class DoctorRegistretion extends AppCompatActivity {
    Button DoctorRegister;
    TextView LoginText;

    private ActivityDoctorRegistretionBinding binding;
    private PreferenceManager preferencesManager;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDoctorRegistretionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferencesManager=new PreferenceManager(this);
        setListeners();
    }

    private void setListeners(){
        binding.DoctorSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Signup_Isvalid()){
                    SignUp();
                }
//                Intent i = new Intent(DoctorRegistretion.this,doctor_home.class);
//                startActivity(i);
            }
        });

        binding.RegLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DoctorRegistretion.this,DoctorLogin.class);
                startActivity(i);
            }
        });
    }

    private void SignUp(){//sign up of doctor
        Loading(true);
        FirebaseFirestore firebaseFireStore= FirebaseFirestore.getInstance();
        HashMap<String,Object> user= new HashMap<>();
        //put data in database
        user.put(Constants.KEY_NAME,binding.InputName.getText().toString());
        user.put(Constants.KEY_PHONENUMBER,binding.SUInputNumber.getText().toString());
        user.put(Constants.KEY_PASSWORD,binding.SUPassword.getText().toString());
        user.put(Constants.KEY_IMAGE,EncodedImage);
        firebaseFireStore.collection(Constants.KEY_COLLECTION_USERS)//create collection name
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    Loading(false);
                    preferencesManager.putBoolean(Constants.KEY_IS_SIGNED_IN,true);
                    preferencesManager.putString(Constants.KEY_USER_ID, documentReference.getId());
                    preferencesManager.putString(Constants.KEY_NAME,binding.InputName.getText().toString());
                    preferencesManager.putString(Constants.KEY_IMAGE,EncodedImage);
                    Intent intent=new Intent(getApplicationContext(),SignInActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    ShowToast("You can Login");
                    startActivity(intent);//if signup then go to mainactivity
                    finish();

                })
                .addOnFailureListener(exception ->{

                    Loading(false);
                    ShowToast(exception.getMessage());
                });

    }

    private void Loading(boolean IsLoading) {
        if(IsLoading){
            binding.DoctorSignup.setVisibility(View.INVISIBLE);
            binding.ProgressBar.setVisibility(View.VISIBLE);
        }
        else {
            binding.ProgressBar.setVisibility(View.INVISIBLE);
            binding.DoctorSignup.setVisibility(View.VISIBLE);
        }
    }


    private void ShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean Signup_Isvalid(){//validation for all fields
        if(binding.DoctorRegName.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Name");
            return false;
        }else if (!Patterns.PHONE.matcher(binding.DoctorPhoneNumber.getText().toString()).matches()) {
            ShowToast("Enter Valid Mobile Number");
            return false;
        }else if (binding.DoctorPhoneNumber.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Number");
            return false;
        }
        else if(binding.DoctorClinicName.getText().toString().trim().isEmpty()){
            ShowToast("Enter Clinic Name");
            return false;
        }else if(binding.DoctorClinicAddress.getText().toString().trim().isEmpty()) {
            ShowToast("Confirm Address");
            return false;
        }else if(!binding.DoctorRegistrationNumber.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Registration Number");
            return false;
        }else if(!binding.DoctorSpecialization.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Your Specialization");
            return false;
        }else if(!binding.DoctorRegPass1.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Password");
            return false;
        }else if(!binding.DoctorRegPass2.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Confirm Password");
            return false;
        }else if(!binding.DoctorRegPass1.getText().toString().equals(binding.DoctorRegPass2.getText().toString())) {
            ShowToast("Password must be same");
            return false;
        }
        else
            return true;
    }




}