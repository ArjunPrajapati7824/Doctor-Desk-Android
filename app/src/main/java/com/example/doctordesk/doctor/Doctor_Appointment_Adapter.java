package com.example.doctordesk.doctor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctordesk.R;
import com.example.doctordesk.patient.AppointmentBooking;
import com.example.doctordesk.patient.Patient_EditProfile;
import com.example.doctordesk.patient.Patient_MyProfile;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Doctor_Appointment_Adapter extends RecyclerView.Adapter<Doctor_Appointment_Adapter.PatientsHolder> {
       Context context;
       ArrayList<ModelPatientList> Patients;
//    PreferenceManager preferencesManager;
//AppointmentBooking appointmentBooking;
       Doctor_Appointment doctor_appointment;

    FirebaseFirestore db;


    public Doctor_Appointment_Adapter(ArrayList<ModelPatientList> Patients) {
        this.Patients=Patients;
    }

    @NonNull
    @Override
    public PatientsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.doctor_appointment_recycle,parent, false);//view created
        return new PatientsHolder(view);//reference of Holder class
    }

    @Override
    public void onBindViewHolder(@NonNull PatientsHolder holder, int position) {

        holder.NameOfPatient.setText(Patients.get(position).getAppointment_Name());
        holder.GenderOfPatient.setText(Patients.get(position).getAppointment_Gender());
        holder.AgeOfPatient.setText(Patients.get(position).getAppointment_Age());
        holder.NumberOfPatient.setText(Patients.get(position).getAppointment_Phone_Number());

        holder.AcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                preferencesManager=new PreferenceManager(appointmentBooking);

                FirebaseFirestore firebaseFireStore = FirebaseFirestore.getInstance();
//                String uid =firebaseFireStore.collection(Constants.KEY_COLLECTION_APPOINTMENTS).document(Constants.KEY_APPOINTMENT_ID).getId();

//                DocumentReference userRef = db.collection(Constants.KEY_COLLECTION_APPOINTMENTS).document(preferencesManager.getString(Constants.KEY_APPOINTMENT_ID));
//                firebaseFireStore.collection(Constants.KEY_COLLECTION_APPOINTMENTS).document(uid)
                firebaseFireStore.collection(Constants.KEY_COLLECTION_APPOINTMENTS).document(Patients.get(position).getAppointment_Id())

                        .update(Constants.KEY_APPOINTMENT_STATUS,"True").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                      Toast.makeText(view.getContext(), "Appointment Accepted", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(view.getContext(), "Appointment Declined", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
//                Map<String, Object> updates = new HashMap<>();
//                updates.put(Constants.KEY_APPOINTMENT_STATUS,"True");
//                userRef.update(updates)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//
//                                FirebaseFirestore database= FirebaseFirestore.getInstance();
//                                database.collection(Constants.KEY_COLLECTION_APPOINTMENTS)
//                                        .get()
//                                        .addOnCompleteListener(task -> {
//                                            if(task.isSuccessful() && task.getResult()!=null && task.getResult().getDocuments().size()>0){
//                                                Toast.makeText(view.getContext(), "Appointment Accepted", Toast.LENGTH_SHORT).show();
//                                            }
//
//                                        });
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(view.getContext(), "Data Updation Failed....", Toast.LENGTH_SHORT).show();
//                            }
//                        });
            }
        });
        holder.DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog= new AlertDialog.Builder(view.getContext());
                dialog.setCancelable(false);
                dialog.setTitle("delete");
                dialog.setMessage("Are you Sure ?");
                dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db= FirebaseFirestore.getInstance();
                        db.collection(Constants.KEY_COLLECTION_APPOINTMENTS).document(Patients.get(position).getAppointment_Id()).delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(view.getContext(), "Data Deleted", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                            Toast.makeText(view.getContext(), "Data Deletion failed", Toast.LENGTH_SHORT).show();

                                    }
                                });
                    }
                });

                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Patients.size();
    }


    class PatientsHolder extends RecyclerView.ViewHolder{
            TextView NameOfPatient, GenderOfPatient,AgeOfPatient, NumberOfPatient;

            Button AcceptButton,DeleteButton;
        public PatientsHolder(@NonNull View itemView) {
            super(itemView);

            NameOfPatient =(TextView)itemView.findViewById(R.id.NameOfPatient);
            GenderOfPatient =(TextView)itemView.findViewById(R.id.GenderOfPatient);
            AgeOfPatient=(TextView)itemView.findViewById(R.id.AgeOfPatient);
            NumberOfPatient=(TextView)itemView.findViewById(R.id.NumberOfPatient);
            AcceptButton=(Button)itemView.findViewById(R.id.AcceptButton);
            DeleteButton=(Button)itemView.findViewById(R.id.DeleteButton);
        }
    }
}
