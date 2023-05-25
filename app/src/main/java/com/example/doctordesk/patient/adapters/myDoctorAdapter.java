package com.example.doctordesk.patient.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctordesk.R;
import com.example.doctordesk.patient.models.myAppointmentDoctorModel;

import java.util.ArrayList;

public class myDoctorAdapter extends  RecyclerView.Adapter<myDoctorAdapter.Patient_My_DoctorViewHolder>{

    ArrayList<myAppointmentDoctorModel> myDoctorArrayList;

    public myDoctorAdapter(ArrayList<myAppointmentDoctorModel> myDoctorArrayList) {

        this.myDoctorArrayList = myDoctorArrayList;
    }


    @NonNull
    @Override
    public Patient_My_DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.patient_mydoctor_recycler_row,parent, false);//view created
        return new Patient_My_DoctorViewHolder(view);//reference of Holder class

    }

    @Override
    public void onBindViewHolder(@NonNull myDoctorAdapter.Patient_My_DoctorViewHolder holder, int position) {
        holder.NameOfDoctor.setText(myDoctorArrayList.get(position).getDr_Name());
        holder.DoctorAddress.setText(myDoctorArrayList.get(position).getClinic_Address());
        holder.Specialization.setText(myDoctorArrayList.get(position).getSpecialization());
        holder.NameOfClinic.setText(myDoctorArrayList.get(position).getClinic_Name());

    }

    @Override
    public int getItemCount() {
        return myDoctorArrayList.size();
    }

    public class Patient_My_DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView NameOfDoctor,DoctorAddress,Specialization,NameOfClinic;

        Patient_My_DoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            NameOfDoctor=itemView.findViewById(R.id.NameOfDoctor);
            DoctorAddress=itemView.findViewById(R.id.ClinicAddress);
            Specialization=itemView.findViewById(R.id.Specialization);
            NameOfClinic=itemView.findViewById(R.id.NameOfClinic);


        }

    }

}


