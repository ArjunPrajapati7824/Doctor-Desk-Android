package com.example.doctordesk.patient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.doctordesk.R;
import com.example.doctordesk.databinding.ActivityAppointmentBookingBinding;
import com.example.doctordesk.doctor.Doctor_Profile;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;

public class AppointmentBooking extends AppCompatActivity {

    private ActivityAppointmentBookingBinding binding;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    ;
    PreferenceManager preferencesManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppointmentBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferencesManager = new PreferenceManager(getApplicationContext());


        radioGroup = (RadioGroup) findViewById(R.id.ApPatientGender);
        binding.appointmentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month, and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for the date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        AppointmentBooking.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Create a Calendar instance for the selected date
                                Calendar selectedDate = Calendar.getInstance();
                                selectedDate.set(year, monthOfYear, dayOfMonth);

                                // Compare selected date with current date
                                if (selectedDate.before(c)) {
                                    // Show an error or reset the date to the current date
                                    // Example: Show a Toast message
                                    Toast.makeText(getApplicationContext(), "Please select a date after the current date", Toast.LENGTH_SHORT).show();
                                    // Reset the date to the current date
                                    binding.appointmentDate.setText(c.get(Calendar.DAY_OF_MONTH) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.YEAR));
                                } else {
                                    // Set the selected date on the startdate TextView
                                    binding.appointmentDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                }
                            }
                        },
                        // on below line we are passing year,
                        // month and day for the selected date in our date picker.
                        year, month, day);
                // at last, we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

        binding.appointmentTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mHour,mMinute;

                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(AppointmentBooking.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int mhour,
                                          int minute) {

                        binding.appointmentTime.setText(mhour+ ":" + minute);
                    }
                },mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                radioButton = group.findViewById(i);
            }
        });

        binding.BookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Signup_Isvalid()) {
                    bookAppintment();
                    clear_data();
                }
//                bookAppointment2();


            }
        });

    }

    void bookAppintment() {

        Loading(true);
        FirebaseFirestore firebaseFireStore = FirebaseFirestore.getInstance();
        String app_id = firebaseFireStore.collection(Constants.KEY_COLLECTION_APPOINTMENTS).document().getId();

        HashMap<String, Object> user = new HashMap<>();
        //put data in database
        user.put(Constants.KEY_APPOINTMENT_NAME, binding.ApPatientName.getText().toString());
        user.put(Constants.KEY_APPOINTMENT_PHONE_NUMBER, binding.ApPatientPhoneNumber.getText().toString());
        user.put(Constants.KEY_APPOINTMENT_AGE, binding.ApPatientAge.getText().toString());
        user.put(Constants.KEY_APPOINTMENT_GENDER, radioButton.getText().toString());
        user.put(Constants.KEY_APPOINTMENT_DESCRIPTION, binding.ApDescription.getText().toString());
        user.put(Constants.KEY_APPOINTMENT_DATE,binding.appointmentDate.getText().toString());
        user.put(Constants.KEY_APPOINTMENT_TIME,binding.appointmentTime.getText().toString());
        user.put(Constants.KEY_APPOINTMENT_ID, app_id);
        user.put(Constants.KEY_PATIENT_ID, preferencesManager.getString(Constants.KEY_PATIENT_ID));
        user.put(Constants.KEY_DOCTOR_ID, getIntent().getStringExtra(Constants.KEY_DOCTOR_ID));
        user.put(Constants.KEY_DOCTOR_NAME, getIntent().getStringExtra(Constants.KEY_DOCTOR_NAME));
        user.put(Constants.KEY_CLINIC_NAME, getIntent().getStringExtra(Constants.KEY_CLINIC_NAME));
        user.put(Constants.KEY_CLINIC_ADDRESS, getIntent().getStringExtra(Constants.KEY_CLINIC_ADDRESS));

        user.put(Constants.KEY_SPECIALIZATION, getIntent().getStringExtra(Constants.KEY_SPECIALIZATION));
        user.put(Constants.KEY_APPOINTMENT_STATUS, "Pending");


        firebaseFireStore.collection(Constants.KEY_COLLECTION_APPOINTMENTS)//create collection name
                .document(app_id)
                .set(user)
                .addOnSuccessListener(documentReference -> {
                    Loading(false);
//                   preferencesManager.putString(Constants.KEY_APPOINTMENT_ID,uid.toString());
//                   preferencesManager.putString(Constants.KEY_DOCTOR_ID, documentReference.getId());
//                   preferencesManager.putString(Constants.KEY_DOCTOR_NAME,binding.InputName.getText().toString());
//                    Intent intent = new Intent(getApplicationContext(),PatientHome.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    ShowToast("Waiting For Confirmation");
                    startActivity(new Intent(getApplicationContext(), Patient_Appointment.class));
                    finish();
                })
                .addOnFailureListener(exception -> {
                    Loading(false);
                    ShowToast(exception.getMessage());
                });


    }


//    private void bookAppointment2(){
//
//        Loading(true);
//        FirebaseFirestore firebaseFireStore = FirebaseFirestore.getInstance();
//        String app_id_2 =firebaseFireStore.collection(Constants.KEY_COLLECTION_APPOINTMENTS_HISTORY).document().getId();
//
//        HashMap<String, Object> user = new HashMap<>();
//        //put data in database
//        user.put(Constants.KEY_APPOINTMENT_NAME, binding.ApPatientName.getText().toString());
//        user.put(Constants.KEY_APPOINTMENT_PHONE_NUMBER, binding.ApPatientPhoneNumber.getText().toString());
//        user.put(Constants.KEY_APPOINTMENT_AGE, binding.ApPatientAge.getText().toString());
//        user.put(Constants.KEY_APPOINTMENT_GENDER, radioButton.getText().toString());
//        user.put(Constants.KEY_APPOINTMENT_DESCRIPTION, binding.ApDescription.getText().toString());
//        user.put(Constants.KEY_APPOINTMENT_ID,app_id_2);
//        user.put(Constants.KEY_PATIENT_ID,preferencesManager.getString(Constants.KEY_PATIENT_ID));
//        user.put(Constants.KEY_DOCTOR_ID,getIntent().getStringExtra(Constants.KEY_DOCTOR_ID));
//        user.put(Constants.KEY_APPOINTMENT_STATUS,"False");
//
//        firebaseFireStore.collection(Constants.KEY_COLLECTION_APPOINTMENTS_HISTORY)//create collection name
//                .document(app_id_2)
//                .set(user)
//                .addOnSuccessListener(documentReference -> {
//                    Loading(false);
////                   preferencesManager.putString(Constants.KEY_APPOINTMENT_ID,uid.toString());
////                   preferencesManager.putString(Constants.KEY_DOCTOR_ID, documentReference.getId());
////                   preferencesManager.putString(Constants.KEY_DOCTOR_NAME,binding.InputName.getText().toString());
////                    Intent intent = new Intent(getApplicationContext(),PatientHome.class);
////                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                    startActivity(intent);//if signup then go to mainactivity
////                    finish();
//                })
//                .addOnFailureListener(exception -> {
//                    Loading(false);
//                    ShowToast(exception.getMessage());
//                });
//
//    }

    private void clear_data() {
        binding.ApPatientName.setText("");
        binding.ApPatientAge.setText("");
        binding.ApDescription.setText("");
        binding.ApPatientGender.clearCheck();
        binding.ApPatientPhoneNumber.setText("");

    }

    private void ShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void Loading(boolean IsLoading) {
        if (IsLoading) {
            binding.BookAppointment.setVisibility(View.INVISIBLE);
            binding.ProgressBar.setVisibility(View.VISIBLE);
        } else {
            binding.ProgressBar.setVisibility(View.INVISIBLE);
            binding.BookAppointment.setVisibility(View.VISIBLE);
        }
    }

    private boolean Signup_Isvalid() {//validation for all fields
        if (binding.ApPatientName.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Patient Name");
            return false;
        } else if (!binding.ApPatientName.getText().toString().trim().matches("^[A-Za-z]+$")) {
            ShowToast("Enter valid Name");
            return false;
        } else if (binding.ApPatientPhoneNumber.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Number");
            return false;
        } else if (binding.ApPatientPhoneNumber.getText().toString().length() != 10) {
            ShowToast("Enter Valid Mobile Number");
            return false;
        } else if (binding.ApPatientAge.getText().toString().trim().isEmpty()) {
            ShowToast("Enter Age");
            return false;
        } else if (binding.ApPatientGender.getCheckedRadioButtonId() == -1) {
            ShowToast("Select Gender");
            return false;
        } else
            return true;
    }
}

