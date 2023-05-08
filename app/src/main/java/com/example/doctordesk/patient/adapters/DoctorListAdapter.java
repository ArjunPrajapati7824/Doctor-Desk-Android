package com.example.doctordesk.patient.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctordesk.R;
import com.example.doctordesk.patient.models.DoctorModel;
import com.example.doctordesk.patient.Patient_DoctorSearch;

import java.util.ArrayList;

public class DoctorListAdapter extends  RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder>{
    Patient_DoctorSearch patient_doctorSearch;
    ArrayList<DoctorModel> arrayList;
    public DoctorListAdapter(Patient_DoctorSearch patient_doctorSearch, ArrayList<DoctorModel> arrayList) {

        this.patient_doctorSearch=patient_doctorSearch;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new DoctorViewHolder(LayoutInflater.from(patient_doctorSearch).inflate(R.layout.doctor_list_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        holder.NameOfDoctor.setText(arrayList.get(position).getDr_Name());
        holder.DoctorAddress.setText(arrayList.get(position).getClinic_Address());
        holder.Specialization.setText(arrayList.get(position).getSpecialization());
        holder.NameOfClinic.setText(arrayList.get(position).getClinic_Name());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class DoctorViewHolder extends  RecyclerView.ViewHolder {
        TextView NameOfDoctor,DoctorAddress,Specialization,NameOfClinic;
        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            NameOfDoctor=itemView.findViewById(R.id.NameOfDoctor);
            DoctorAddress=itemView.findViewById(R.id.DoctorAddress);
            Specialization=itemView.findViewById(R.id.Specialization);
            NameOfClinic=itemView.findViewById(R.id.NameOfClinic);
        }
    }
}
