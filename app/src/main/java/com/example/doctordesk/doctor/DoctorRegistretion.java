package com.example.doctordesk.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctordesk.R;
import com.example.doctordesk.otpVerification;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class DoctorRegistretion extends AppCompatActivity {
    Button DoctorRegister;
    TextView LoginText;
    EditText phoneNumber;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registretion);
        FirebaseApp.initializeApp(DoctorRegistretion.this);
        mAuth = FirebaseAuth.getInstance();
        LoginText=findViewById(R.id.RegLoginText);
        DoctorRegister=findViewById(R.id.DoctorSignup);
        phoneNumber=findViewById(R.id.DoctorPhoneNumber);

        DoctorRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!phoneNumber.getText().toString().trim().isEmpty())
                {
                    if((phoneNumber.getText().toString().trim()).length() == 10)
                    {

                        DoctorRegister.setVisibility(View.INVISIBLE);
                        PhoneAuthOptions options=PhoneAuthOptions.newBuilder(mAuth)
                                .setActivity(DoctorRegistretion.this)
                                .setPhoneNumber("+91"+phoneNumber.getText().toString())
                                .setTimeout(60l, TimeUnit.SECONDS)
                                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        DoctorRegister.setVisibility(View.VISIBLE);
                                    }


                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        DoctorRegister.setVisibility(View.VISIBLE);
                                        Toast.makeText(DoctorRegistretion.this, "Please Check Your Connection", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        super.onCodeSent(otp, forceResendingToken);

                                        DoctorRegister.setVisibility(View.VISIBLE);

                                        Intent intent = new Intent(DoctorRegistretion.this, otpVerification.class);
                                        intent.putExtra("mobile",phoneNumber.getText().toString());
                                        intent.putExtra("otp",otp);
                                        startActivity(intent);
                                        finish();

                                    }
                                })
                                .build();
                        PhoneAuthProvider.verifyPhoneNumber(options);
                    }
                }
                else{
                    Toast.makeText(DoctorRegistretion.this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        LoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DoctorRegistretion.this,DoctorLogin.class);
                startActivity(i);
            }
        });



    }
}