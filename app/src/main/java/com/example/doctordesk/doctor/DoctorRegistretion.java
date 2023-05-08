package com.example.doctordesk.doctor;

import static android.view.View.INVISIBLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.doctordesk.databinding.ActivityDoctorRegistretionBinding;
import com.example.doctordesk.otpVerification;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DoctorRegistretion extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private ActivityDoctorRegistretionBinding binding;
    private PreferenceManager preferencesManager;

    public static boolean verify_otp_doctor=false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorRegistretionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseApp.initializeApp(DoctorRegistretion.this);
        mAuth = FirebaseAuth.getInstance();
//        LoginText=findViewById(R.id.RegLoginText);
//        DoctorRegister=findViewById(R.id.DoctorSignup);
//        phoneNumber=findViewById(R.id.DoctorPhoneNumber);

        binding.DoctorSignup.setText("Get Otp");
        preferencesManager = new PreferenceManager(this);
        setListeners();

    }

    private void setListeners() {


        binding.RegLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DoctorRegistretion.this, DoctorLogin.class);
                startActivity(i);
            }
        });

        binding.DoctorSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (Signup_Isvalid()) {

                if(Signup_Isvalid()){

                    binding.DoctorSignup.setVisibility(INVISIBLE);
                    binding.ProgressBar.setVisibility(View.VISIBLE);
                    PhoneAuthOptions options=PhoneAuthOptions.newBuilder(mAuth)
                            .setActivity(DoctorRegistretion.this)
                            .setPhoneNumber("+91"+binding.DoctorPhoneNumber.getText().toString())
                            .setTimeout(60l, TimeUnit.SECONDS)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                    binding.DoctorSignup.setVisibility(View.VISIBLE);
                                }
                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {

                                    binding.DoctorSignup.setVisibility(View.VISIBLE);
                                    binding.ProgressBar.setVisibility(INVISIBLE);
                                    Toast.makeText(DoctorRegistretion.this, "Please Check Your Connection", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(otp, forceResendingToken);

                                    verify_otp_doctor=true;
                                    binding.DoctorSignup.setVisibility(INVISIBLE);
                                    binding.ProgressBar.setVisibility(View.VISIBLE);
                                    SignUp();

                                    Intent intent = new Intent(DoctorRegistretion.this, otpVerification.class);
                                    intent.putExtra("mobile",binding.DoctorPhoneNumber.getText().toString());
                                    intent.putExtra("otp",otp);
                                    startActivity(intent);
                                    finish();

                                }
                            })
                            .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);
                }
                else{
                    binding.DoctorSignup.setVisibility(View.VISIBLE);
                    binding.ProgressBar.setVisibility(View.INVISIBLE);
                }

            }

        });

    }



            public void SignUp() {//sign up of doctor
                Loading(true);
                FirebaseFirestore firebaseFireStore = FirebaseFirestore.getInstance();
                HashMap<String, Object> user = new HashMap<>();
                //put data in database
                user.put(Constants.KEY_DOCTOR_NAME, binding.DoctorRegName.getText().toString());
                user.put(Constants.KEY_DOCTOR_PHONENUMBER, binding.DoctorPhoneNumber.getText().toString());
                user.put(Constants.KEY_CLINIC_NAME, binding.DoctorClinicName.getText().toString());
                user.put(Constants.KEY_CLINIC_ADDRESS, binding.DoctorClinicAddress.getText().toString());
                user.put(Constants.KEY_DOCTOR_REGISTRATION_NUMBER, binding.DoctorRegistrationNumber.getText().toString());
                user.put(Constants.KEY_SPECIALIZATION, binding.DoctorSpecialization.getText().toString());
                user.put(Constants.KEY_DOCTOR_PASSWORD, binding.DoctorRegPass1.getText().toString());
                firebaseFireStore.collection(Constants.KEY_COLLECTION_DOCTORS)//create collection name
                        .add(user)
                        .addOnSuccessListener(documentReference -> {
                            Loading(false);
//                   preferencesManager.putBoolean(Constants.KEY_IS_DOCTOR_SIGNED_IN,true);
//                   preferencesManager.putString(Constants.KEY_DOCTOR_ID, documentReference.getId());
//                   preferencesManager.putString(Constants.KEY_DOCTOR_NAME,binding.InputName.getText().toString());
//                            Intent intent = new Intent(getApplicationContext(), doctor_home.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            ShowToast("Verify otp");
//                            startActivity(intent);//if signup then go to mainactivity
//                            finish();

                        })
                        .addOnFailureListener(exception -> {
//                            Loading(false);
                            ShowToast(exception.getMessage());
                        });
            }

            private void Loading(boolean IsLoading) {
                if (IsLoading) {
                    binding.DoctorSignup.setVisibility(INVISIBLE);
                    binding.ProgressBar.setVisibility(View.VISIBLE);
                } else {
                    binding.ProgressBar.setVisibility(INVISIBLE);
                    binding.DoctorSignup.setVisibility(View.VISIBLE);
                }
            }


            private void ShowToast(String message) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }

            private boolean Signup_Isvalid() {//validation for all fields
                if (binding.DoctorRegName.getText().toString().trim().isEmpty()) {
                    ShowToast("Enter Name");
                    return false;
                } else if (binding.DoctorPhoneNumber.getText().toString().trim().isEmpty()) {
                    ShowToast("Enter Number");
                    return false;
                } else if (binding.DoctorPhoneNumber.getText().toString().length() != 10) {
                    ShowToast("Enter Valid Mobile Number");
                    return false;
                } else if (binding.DoctorClinicName.getText().toString().trim().isEmpty()) {
                    ShowToast("Enter Clinic Name");
                    return false;
                } else if (binding.DoctorClinicAddress.getText().toString().trim().isEmpty()) {
                    ShowToast("Confirm Address");
                    return false;
                } else if (binding.DoctorRegistrationNumber.getText().toString().trim().isEmpty()) {
                    ShowToast("Enter Registration Number");
                    return false;
                } else if (binding.DoctorRegistrationNumber.getText().toString().length() != 12) {
                    ShowToast("Enter valid 12 digit Registration Number");
                    return false;
                } else if (binding.DoctorSpecialization.getText().toString().trim().isEmpty()) {
                    ShowToast("Enter Your Specialization");
                    return false;
                } else if (binding.DoctorRegPass1.getText().toString().trim().isEmpty()) {
                    ShowToast("Enter Password");
                    return false;
                } else if (binding.DoctorRegPass2.getText().toString().trim().isEmpty()) {
                    ShowToast("Enter Confirm Password");
                    return false;
                } else if (!binding.DoctorRegPass1.getText().toString().equals(binding.DoctorRegPass2.getText().toString())) {
                    ShowToast("Password must be same");
                    return false;
                } else
                    return true;
            }


    }