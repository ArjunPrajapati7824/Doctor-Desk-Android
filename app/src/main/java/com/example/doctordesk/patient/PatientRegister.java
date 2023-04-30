package com.example.doctordesk.patient;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctordesk.R;
import com.example.doctordesk.doctor.DoctorRegistretion;
import com.example.doctordesk.otpVerification;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PatientRegister extends AppCompatActivity {
    Button PatientSingUp;
    TextView  LoginText;
    EditText phoneNumber;
    private FirebaseAuth mAuth;
    ProgressBar processbar_sending_otp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);
        LoginText=findViewById(R.id.LoginText);
        PatientSingUp=findViewById(R.id.PatientSingin);
        mAuth = FirebaseAuth.getInstance();
        phoneNumber=findViewById(R.id.PatientPhoneNumber);


        LoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PatientRegister.this, PatientLogin.class);
                startActivity(i);
            }
        });

        PatientSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!phoneNumber.getText().toString().trim().isEmpty())
                {
                    if((phoneNumber.getText().toString().trim()).length() == 10)
                    {

//                        DoctorRegister.setVisibility(View.INVISIBLE);
                        PhoneAuthOptions options=PhoneAuthOptions.newBuilder(mAuth)
                                .setActivity(PatientRegister.this)
                                .setPhoneNumber("+91"+phoneNumber.getText().toString())
                                .setTimeout(60l, TimeUnit.SECONDS)
                                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                                        processbar_sending_otp.setVisibility(View.VISIBLE);
//                                        DoctorRegister.setVisibility(View.INVISIBLE);
                                    }


                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
//                                        processbar_sending_otp.setVisibility(View.VISIBLE);
//                                        DoctorRegister.setVisibility(View.INVISIBLE);
                                        Toast.makeText(PatientRegister.this, "Please Check Your Connection", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        super.onCodeSent(otp, forceResendingToken);
//                                        processbar_sending_otp.setVisibility(View.VISIBLE);
//                                        DoctorRegister.setVisibility(View.INVISIBLE);

                                        Intent intent = new Intent(PatientRegister.this, otpVerification.class);
                                        intent.putExtra("mobile",phoneNumber.getText().toString());
                                        intent.putExtra("otp",otp);
                                        intent.putExtra("typeP","Patient");
                                        startActivity(intent);
                                        finish();

                                    }
                                })
                                .build();
                        PhoneAuthProvider.verifyPhoneNumber(options);
                    }
                }
                else{
                    Toast.makeText(PatientRegister.this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}