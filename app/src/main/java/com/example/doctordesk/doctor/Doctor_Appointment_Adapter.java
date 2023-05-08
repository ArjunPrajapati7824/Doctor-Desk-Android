package com.example.doctordesk.doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctordesk.R;

import java.util.ArrayList;

public class Doctor_Appointment_Adapter extends RecyclerView.Adapter<Doctor_Appointment_Adapter.PatientsHolder> {
       Context context;
       ArrayList<ModelPatientList> Patients;

    public Doctor_Appointment_Adapter(Context context,ArrayList<ModelPatientList> Patients) {
        this.context = context;
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

        holder.NameOfPatient.setText(Patients.get(position).name);
        holder.GenderOfPatient.setText(Patients.get(position).gender);
        holder.AgeOfPatient.setText(Patients.get(position).age);
        holder.NumberOfPatient.setText(Patients.get(position).number);

    }

    @Override
    public int getItemCount() {
        return Patients.size();
    }


    class PatientsHolder extends RecyclerView.ViewHolder{
            TextView NameOfPatient, GenderOfPatient,AgeOfPatient, NumberOfPatient;
        public PatientsHolder(@NonNull View itemView) {
            super(itemView);

            NameOfPatient =(TextView)itemView.findViewById(R.id.NameOfPatient);
            GenderOfPatient =(TextView)itemView.findViewById(R.id.GenderOfPatient);
            AgeOfPatient=(TextView)itemView.findViewById(R.id.AgeOfPatient);
            NumberOfPatient=(TextView)itemView.findViewById(R.id.NumberOfPatient);
        }
    }
}
