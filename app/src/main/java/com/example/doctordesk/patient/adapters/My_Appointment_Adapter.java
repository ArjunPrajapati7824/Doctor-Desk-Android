package com.example.doctordesk.patient.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctordesk.R;
import com.example.doctordesk.doctor.Doctor_Appointment_Adapter;
import com.example.doctordesk.patient.Patient_Appointment;
import com.example.doctordesk.patient.Patient_DoctorSearch;
import com.example.doctordesk.patient.models.myAppointmentDoctorModel;

import java.util.ArrayList;

public class My_Appointment_Adapter extends  RecyclerView.Adapter<My_Appointment_Adapter.My_DoctorViewHolder>{

    ArrayList<myAppointmentDoctorModel> myApppointmentArrayList;

    public My_Appointment_Adapter(ArrayList<myAppointmentDoctorModel> myApppointmentArrayList) {

        this.myApppointmentArrayList=myApppointmentArrayList;
    }


    @NonNull
    @Override
    public My_DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.patient_appointments_row,parent, false);//view created
        return new My_DoctorViewHolder(view);//reference of Holder class

    }

    @Override
    public void onBindViewHolder(@NonNull My_Appointment_Adapter.My_DoctorViewHolder holder, int position) {
        holder.NameOfDoctor.setText("Doctor Name : "+myApppointmentArrayList.get(position).getDr_Name());
        holder.DoctorAddress.setText("Clinic Address : "+myApppointmentArrayList.get(position).getClinic_Address());
        holder.Specialization.setText("Specialization : "+myApppointmentArrayList.get(position).getSpecialization());
        holder.NameOfClinic.setText("Clinic Name : "+myApppointmentArrayList.get(position).getClinic_Name());
        holder.Appointment_Name.setText("Patient Name : "+myApppointmentArrayList.get(position).getAppointment_Name());
        holder.Appointment_date.setText("Date : "+myApppointmentArrayList.get(position).getAppointment_date());
        holder.Appointment_Time.setText("Time : "+myApppointmentArrayList.get(position).getAppointment_time());

        if(myApppointmentArrayList.get(position).getAppointment_Status().equals("Accept")){
            holder.CheckStatus.setVisibility(View.VISIBLE);
            holder.CheckStatus.setText("Approved");
            holder.CheckStatus.setTextColor(Color.GREEN);
        }else if(myApppointmentArrayList.get(position).getAppointment_Status().equals("Reject")){
            holder.CheckStatus.setVisibility(View.VISIBLE);
            holder.CheckStatus.setText("Rejected");
            holder.CheckStatus.setTextColor(Color.RED);
        }else{
            holder.CheckStatus.setVisibility(View.VISIBLE);
            holder.CheckStatus.setText("Pending");
            holder.CheckStatus.setTextColor(Color.rgb(255,215,0));
        }

    }

    @Override
    public int getItemCount() {
        return myApppointmentArrayList.size();
    }

    public class My_DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView NameOfDoctor,DoctorAddress,Specialization,NameOfClinic,CheckStatus,Appointment_date,Appointment_Name,Appointment_Time;

        My_DoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            NameOfDoctor=itemView.findViewById(R.id.NameOfDoctor);
            DoctorAddress=itemView.findViewById(R.id.ClinicAddress);
            Specialization=itemView.findViewById(R.id.Specialization);
            NameOfClinic=itemView.findViewById(R.id.NameOfClinic);
            CheckStatus=itemView.findViewById(R.id.CheckStatus);
            Appointment_date=itemView.findViewById(R.id.appointmentDate);
            Appointment_Name=itemView.findViewById(R.id.PatientName);
            Appointment_Time=itemView.findViewById(R.id.appointmentTime);

        }

    }

}
