package com.example.doctordesk.doctor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctordesk.R;
import com.example.doctordesk.doctor.Model.myPatientsModel;
import com.example.doctordesk.patient.adapters.myDoctorAdapter;
import com.example.doctordesk.patient.models.myAppointmentDoctorModel;

import java.util.ArrayList;

public class myPatientsAdapter extends RecyclerView.Adapter<myPatientsAdapter.My_PatientsViewHolder>{

    ArrayList<myPatientsModel> myPatientasArrayList;

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
        holder.NameOfPatient.setText(myPatientasArrayList.get(position).getAppointment_Name());
        holder.appointmentPhoneNumber.setText(myPatientasArrayList.get(position).getAppointment_Phone_Number());
        holder.appointmentGender.setText(myPatientasArrayList.get(position).getAppointment_Gender());
        holder.appointmentAge.setText(myPatientasArrayList.get(position).getAppointment_Age());

    }

    @Override
    public int getItemCount() {
        return myPatientasArrayList.size();
    }

    public class My_PatientsViewHolder extends RecyclerView.ViewHolder {
        TextView NameOfPatient,appointmentPhoneNumber,appointmentGender,appointmentAge;

        My_PatientsViewHolder(@NonNull View itemView) {
            super(itemView);

            NameOfPatient=itemView.findViewById(R.id.NameOfPatient);
            appointmentPhoneNumber=itemView.findViewById(R.id.appointmentPhoneNumber);
            appointmentGender=itemView.findViewById(R.id.appointmentGender);
            appointmentAge=itemView.findViewById(R.id.appointmentAge);

        }

    }

}
