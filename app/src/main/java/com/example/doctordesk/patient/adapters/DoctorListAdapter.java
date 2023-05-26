package com.example.doctordesk.patient.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctordesk.R;
import com.example.doctordesk.patient.AppointmentBooking;
import com.example.doctordesk.patient.models.DoctorModel;
import com.example.doctordesk.patient.Patient_DoctorSearch;
import com.example.doctordesk.utilities.Constants;

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
        holder.NameOfDoctor.setText("Doctor Name : "+arrayList.get(position).getDr_Name());
        holder.DoctorAddress.setText("Clinic Address : "+arrayList.get(position).getClinic_Address());
        holder.Specialization.setText("Specialization : "+arrayList.get(position).getSpecialization());
        holder.NameOfClinic.setText("Clinic Name : "+arrayList.get(position).getClinic_Name());

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AppointmentBooking.class);
                intent.putExtra(Constants.KEY_DOCTOR_ID,arrayList.get(position).getDoctor_Id());
                intent.putExtra(Constants.KEY_DOCTOR_NAME,arrayList.get(position).getDr_Name());
                intent.putExtra(Constants.KEY_CLINIC_ADDRESS,arrayList.get(position).getClinic_Address());
                intent.putExtra(Constants.KEY_CLINIC_NAME,arrayList.get(position).getClinic_Name());
                intent.putExtra(Constants.KEY_SPECIALIZATION,arrayList.get(position).getSpecialization());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class DoctorViewHolder extends  RecyclerView.ViewHolder {
        TextView NameOfDoctor,DoctorAddress,Specialization,NameOfClinic;
        Button btn;
        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            NameOfDoctor=itemView.findViewById(R.id.NameOfDoctor);
            DoctorAddress=itemView.findViewById(R.id.ClinicAddress);
            Specialization=itemView.findViewById(R.id.Specialization);
            NameOfClinic=itemView.findViewById(R.id.NameOfClinic);
            btn=itemView.findViewById(R.id.bookbtn);
        }
    }
}
