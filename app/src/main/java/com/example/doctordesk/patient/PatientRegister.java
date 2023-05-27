package com.example.doctordesk.patient;


import static android.view.View.INVISIBLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityPatientRegisterBinding;
import com.example.doctordesk.otpVerification;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class PatientRegister extends AppCompatActivity {


    private ActivityPatientRegisterBinding binding;
    private PreferenceManager preferencesManager;
    private FirebaseAuth mAuth;

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    public static boolean checkpatientExists;

    String[] bloodGroup = {"A+", "A-", "B+", "B-","AB+", "AB-","O+","O-"};
    ArrayList<String> arrayList;
    int SelectedId;

    public static boolean verify_otp_patient=false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();


        arrayList=new ArrayList<>();
        arrayList.add("A+");
        arrayList.add("A-");
        arrayList.add("B+");
        arrayList.add("B-");
        arrayList.add("AB+");
        arrayList.add("AB-");
        arrayList.add("O+");
        arrayList.add("O-");
        //add items in bloodgroup spinner
        ArrayAdapter<String> bloodGroupAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,bloodGroup);
        binding.spinnerLanguage.setAdapter(bloodGroupAdapter);

        radioGroup = (RadioGroup) findViewById(R.id.PatientGender);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                radioButton=group.findViewById(i);

            }
        });
        //ShowToast(radioGroup.getCheckedRadioButtonId().getText().toString());

    binding.spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        binding.spinnerLanguage.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });

        preferencesManager = new PreferenceManager(this);

        if(otpVerification.confirm){
            if(getIntent().getStringExtra("confirm").equals("1")){

                binding.PatientRgName.setText(preferencesManager.getString(Constants.KEY_PATIENTS_NAME));
                binding.PatientPhoneNumber.setText(preferencesManager.getString(Constants.KEY_PATIENT_PHONE_NUMBER));
                binding.PatientAge.setText(preferencesManager.getString(Constants.KEY_PATIENT_AGE));
                binding.PatientCity.setText(preferencesManager.getString(Constants.KEY_PATIENT_CITY));
                binding.PatientWeight.setText(preferencesManager.getString(Constants.KEY_PATIENT_WEIGHT));
                binding.PatientLoginPass1.setText(preferencesManager.getString(Constants.KEY_PATIENT_PASSWORD));
                binding.PatientLoginPass2.setText(preferencesManager.getString(Constants.KEY_PATIENT_PASSWORD));
//            radioGroup.setOnCheckedChangeListener(preferencesManager.getString(Constants.KEY_DOCTOR_ID));
                binding.VerifyOtp.setVisibility(INVISIBLE);
                binding.PatientSingUp.setVisibility(View.VISIBLE);
            }
        }

        setListeners();
    }

    private void setListeners() {

        binding.PatientSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Signup_Isvalid())
                    SignUp();
            }
        });
        binding.VerifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(Signup_Isvalid()){
                    preferencesManager.putString(Constants.KEY_PATIENTS_NAME, binding.PatientRgName.getText().toString());
                    preferencesManager.putString(Constants.KEY_PATIENT_PHONE_NUMBER, binding.PatientPhoneNumber.getText().toString());
                    preferencesManager.putString(Constants.KEY_PATIENT_AGE, binding.PatientAge.getText().toString());
                    preferencesManager.putString(Constants.KEY_PATIENT_CITY, binding.PatientCity.getText().toString());
                    preferencesManager.putString(Constants.KEY_PATIENT_WEIGHT, binding.PatientWeight.getText().toString());
                    preferencesManager.putString(Constants.KEY_PATIENT_PASSWORD, binding.PatientLoginPass1.getText().toString());
                    preferencesManager.putString(Constants.KEY_PATIENT_PASSWORD, binding.PatientLoginPass2.getText().toString());
//                    SignUp();

                    binding.VerifyOtp.setVisibility(INVISIBLE);
                    binding.ProgressBar.setVisibility(View.VISIBLE);
                    PhoneAuthOptions options=PhoneAuthOptions.newBuilder(mAuth)
                            .setActivity(PatientRegister.this)
                            .setPhoneNumber("+91"+binding.PatientPhoneNumber.getText().toString())
                            .setTimeout(60l, TimeUnit.SECONDS)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                    binding.VerifyOtp.setVisibility(View.VISIBLE);
                                }
                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {

                                    binding.VerifyOtp.setVisibility(View.VISIBLE);
                                    binding.ProgressBar.setVisibility(INVISIBLE);
                                    Toast.makeText(PatientRegister.this, "Please Check Your Connection", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(otp, forceResendingToken);

                                    verify_otp_patient=true;
                                    binding.VerifyOtp.setVisibility(INVISIBLE);
                                    binding.ProgressBar.setVisibility(View.VISIBLE);
//                                    SignUp();
                                    Intent intent = new Intent(PatientRegister.this, otpVerification.class);
                                    intent.putExtra("mobile",binding.PatientPhoneNumber.getText().toString());
                                    intent.putExtra("otp",otp);
                                    intent.putExtra("check","1");
                                    startActivity(intent);
                                    finish();

                                }
                            })
                            .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);
                }

                else{
                    binding.VerifyOtp.setVisibility(View.VISIBLE);
                    binding.ProgressBar.setVisibility(View.INVISIBLE);
                }

            }

        });



        binding.LoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PatientRegister.this, PatientLogin.class);
                startActivity(i);
            }
        });
    }

    public void SignUp() {//sign up of doctor

        Loading(true);
        FirebaseFirestore firebaseFireStore = FirebaseFirestore.getInstance();
        String uid =firebaseFireStore.collection(Constants.KEY_COLLECTION_PATIENTS).document().getId();

        HashMap<String, Object> user = new HashMap<>();
        //put data in database
        user.put(Constants.KEY_PATIENTS_NAME, binding.PatientRgName.getText().toString());
        user.put(Constants.KEY_PATIENT_PHONE_NUMBER, binding.PatientPhoneNumber.getText().toString());
        user.put(Constants.KEY_PATIENT_AGE, binding.PatientAge.getText().toString());
        user.put(Constants.KEY_PATIENT_CITY, binding.PatientCity.getText().toString());
        user.put(Constants.KEY_PATIENT_BLOOD_GROUP, binding.spinnerLanguage.getSelectedItem().toString());
        user.put(Constants.KEY_PATIENT_GENDER,radioButton.getText().toString());
        user.put(Constants.KEY_PATIENT_WEIGHT, binding.PatientWeight.getText().toString());
        user.put(Constants.KEY_PATIENT_PASSWORD, binding.PatientLoginPass1.getText().toString());
        user.put(Constants.KEY_PATIENT_ID, uid);


        firebaseFireStore.collection(Constants.KEY_COLLECTION_PATIENTS)//create collection name
                .document(uid)
                .set(user)
                .addOnSuccessListener(documentReference -> {
                    Loading(false);
//                   preferencesManager.putBoolean(Constants.KEY_IS_DOCTOR_SIGNED_IN,true);
//                   preferencesManager.putString(Constants.KEY_DOCTOR_ID, documentReference.getId());
//                   preferencesManager.putString(Constants.KEY_DOCTOR_NAME,binding.InputName.getText().toString());
//                    Intent intent = new Intent(getApplicationContext(),PatientHome.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(new Intent(getApplicationContext(),PatientLogin.class));
                    ShowToast("Welcome");
//                    startActivity(intent);//if signup then go to mainactivity
//                    finish();

                })
                .addOnFailureListener(exception -> {
                    Loading(false);
                    ShowToast(exception.getMessage());
                });
    }

    private void Loading(boolean IsLoading) {
        if (IsLoading) {
            binding.PatientSingUp.setVisibility(View.INVISIBLE);
            binding.ProgressBar.setVisibility(View.VISIBLE);
        } else {
            binding.ProgressBar.setVisibility(View.INVISIBLE);
            binding.PatientSingUp.setVisibility(View.VISIBLE);
        }
    }

    private boolean patientExists(){
        FirebaseFirestore database= FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_DOCTORS).whereEqualTo(Constants.KEY_PATIENT_PHONE_NUMBER,binding.PatientPhoneNumber.getText().toString())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        checkpatientExists=true;
                    }
                });
        return checkpatientExists;
    }


    private void ShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("ResourceType")
    private boolean Signup_Isvalid() {//validation for all fields
        FirebaseFirestore database= FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_PATIENTS).whereEqualTo(Constants.KEY_DOCTOR_PHONENUMBER,binding.PatientPhoneNumber.getText().toString())
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful() && task.getResult()!=null && task.getResult().getDocuments().size()>0){
                        checkpatientExists=true;
                    }else {
                        checkpatientExists=false;
                    }

                });
        if (binding.PatientRgName.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Name");
            return false;
        }
        else if (!binding.PatientRgName.getText().toString().trim().matches("^[A-Z a-z]+$")) {
            ShowToast("Enter valid Name");
            return false;
        }else if (binding.PatientPhoneNumber.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Number");
            return false;
        } else if (binding.PatientPhoneNumber.getText().toString().length() != 10) {
            ShowToast("Enter Valid Mobile Number");
            return false;
        }
        else if (!checkpatientExists) {
            ShowToast("Already exists");
            return false;
        }
        else if (binding.PatientAge.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Age");
            return false;
        } else if (binding.PatientCity.getText().toString().trim().isEmpty()) {
            ShowToast("Confirm City");
            return false;
        } else if (binding.spinnerLanguage.getSelectedItem().toString().isEmpty()) {
            ShowToast("Enter Blood Group");
            return false;
        }
        else if (binding.PatientGender.getCheckedRadioButtonId() == -1){
            ShowToast("Select Gender");
            return false;
        }
        else if (binding.PatientLoginPass1.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Password");
            return false;
        } else if (binding.PatientLoginPass2.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Confirm Password");
            return false;
        }
        else
            return true;
    }



//        binding.PatientSingin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!binding.PatientPhoneNumber.getText().toString().trim().isEmpty())
//                {
//                    if((binding.PatientPhoneNumber.getText().toString().trim()).length() == 10)
//                    {
//
////                        DoctorRegister.setVisibility(View.INVISIBLE);
//                        PhoneAuthOptions options=PhoneAuthOptions.newBuilder(mAuth)
//                                .setActivity(PatientRegister.this)
//                                .setPhoneNumber("+91"+binding.PatientPhoneNumber.getText().toString())
//                                .setTimeout(60l, TimeUnit.SECONDS)
//                                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                                    @Override
//                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
////                                        processbar_sending_otp.setVisibility(View.VISIBLE);
////                                        DoctorRegister.setVisibility(View.INVISIBLE);
//                                    }
//
//
//                                    @Override
//                                    public void onVerificationFailed(@NonNull FirebaseException e) {
////                                        processbar_sending_otp.setVisibility(View.VISIBLE);
////                                        DoctorRegister.setVisibility(View.INVISIBLE);
//                                        Toast.makeText(PatientRegister.this, "Please Check Your Connection", Toast.LENGTH_SHORT).show();
//                                    }
//
//                                    @Override
//                                    public void onCodeSent(@NonNull String otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                        super.onCodeSent(otp, forceResendingToken);
////                                        processbar_sending_otp.setVisibility(View.VISIBLE);
////                                        DoctorRegister.setVisibility(View.INVISIBLE);
//
//                                        Intent intent = new Intent(PatientRegister.this, otpVerification.class);
//                                        intent.putExtra("mobile",binding.PatientPhoneNumber.getText().toString());
//                                        intent.putExtra("otp",otp);
//                                        intent.putExtra("typeP","Patient");
//                                        startActivity(intent);
//                                        finish();
//
//                                    }
//                                })
//                                .build();
//                        PhoneAuthProvider.verifyPhoneNumber(options);
//                    }
//                }
//                else{
//                    Toast.makeText(PatientRegister.this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });



}