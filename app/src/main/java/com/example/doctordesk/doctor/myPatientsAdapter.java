package com.example.doctordesk.doctor;

import android.content.Context;
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
import com.example.doctordesk.doctor.Model.myPatientsModel;
import com.example.doctordesk.patient.AppointmentBooking;
import com.example.doctordesk.patient.adapters.myDoctorAdapter;
import com.example.doctordesk.patient.models.myAppointmentDoctorModel;
import com.example.doctordesk.utilities.Constants;
import com.example.doctordesk.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class myPatientsAdapter extends RecyclerView.Adapter<myPatientsAdapter.My_PatientsViewHolder>{

    ArrayList<myPatientsModel> myPatientasArrayList;

    PreferenceManager preferencesManager;

    Context context;
    public myPatientsAdapter(ArrayList<myPatientsModel> myPatientasArrayList) {

        this.myPatientasArrayList = myPatientasArrayList;
    }


    @NonNull
    @Override
    public My_PatientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.doctor_myptient_recycle_row,parent, false);//view created
        return new My_PatientsViewHolder(view);//reference of Holder class

    }

    @Override
    public void onBindViewHolder(@NonNull My_PatientsViewHolder holder, int position) {


        holder.NameOfPatient.setText("Name : "+myPatientasArrayList.get(position).getAppointment_Name());
        holder.appointmentPhoneNumber.setText("Contact : "+myPatientasArrayList.get(position).getAppointment_Phone_Number());
        holder.appointmentGender.setText("Gender : "+myPatientasArrayList.get(position).getAppointment_Gender());
        holder.appointmentAge.setText("Age : "+myPatientasArrayList.get(position).getAppointment_Age());

        holder.PrescriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferencesManager = new PreferenceManager(view.getContext());
                Intent intent = new Intent(view.getContext(), DoctorPrescription.class);
                intent.putExtra("Appointment_name",myPatientasArrayList.get(position).getAppointment_Name());
                intent.putExtra("Appointment_phone",myPatientasArrayList.get(position).getAppointment_Phone_Number());
                intent.putExtra("Appointment_gender",myPatientasArrayList.get(position).getAppointment_Gender());
                intent.putExtra("Appointment_age",myPatientasArrayList.get(position).getAppointment_Age());
                intent.putExtra("Doctor_name",preferencesManager.getString(Constants.KEY_DOCTOR_NAME));
                intent.putExtra("Clinic_Name",preferencesManager.getString(Constants.KEY_CLINIC_NAME));
                intent.putExtra("Clinic_Address",preferencesManager.getString(Constants.KEY_CLINIC_ADDRESS));
                intent.putExtra("Doctor_contact",preferencesManager.getString(Constants.KEY_DOCTOR_PHONENUMBER));
                intent.putExtra("Specialization",preferencesManager.getString(Constants.KEY_SPECIALIZATION));
                intent.putExtra("registration_number",preferencesManager.getString(Constants.KEY_DOCTOR_REGISTRATION_NUMBER));

                view.getContext().startActivity(intent);
            }
        });

        holder.Rejctbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore firebaseFireStore = FirebaseFirestore.getInstance();

                firebaseFireStore.collection(Constants.KEY_COLLECTION_APPOINTMENTS).document(myPatientasArrayList.get(position).getAppointment_Id())

                        .update(Constants.KEY_APPOINTMENT_STATUS,"Pending").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Intent i = new Intent(view.getContext(),Doctor_Appointment.class);
                                    view.getContext().startActivity(i);
                                    Toast.makeText(view.getContext(), "Appointment Rejected", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(view.getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();


                                }
                            }
                        });
            }
        });

    }

    @Override
    public int getItemCount() {
        return myPatientasArrayList.size();
    }

    public class My_PatientsViewHolder extends RecyclerView.ViewHolder {
        TextView NameOfPatient,appointmentPhoneNumber,appointmentGender,appointmentAge;

        Button PrescriptionBtn,Rejctbtn;
        My_PatientsViewHolder(@NonNull View itemView) {
            super(itemView);

            NameOfPatient=itemView.findViewById(R.id.NameOfPatient);
            appointmentPhoneNumber=itemView.findViewById(R.id.appointmentPhoneNumber);
            appointmentGender=itemView.findViewById(R.id.appointmentGender);
            appointmentAge=itemView.findViewById(R.id.appointmentAge);
            PrescriptionBtn=itemView.findViewById(R.id.PrescriptionBtn);
            Rejctbtn=itemView.findViewById(R.id.Rejectbtn);
        }

    }

}
