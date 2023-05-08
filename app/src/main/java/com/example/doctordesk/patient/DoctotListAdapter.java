package com.example.doctordesk.patient;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctordesk.R;
import com.example.doctordesk.Patientmodels.DoctorModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class DoctotListAdapter extends RecyclerView.Adapter<DoctotListAdapter.DoctorListViewHolder> {
//
//    private final List<DoctorModel> doctorslist;
//
//    public DoctotListAdapter(List<DoctorModel> doctorslist) {
//
//        this.doctorslist = doctorslist;
//    }
//
//    @NonNull
//    @Override
//    public DoctorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        DoctorListViewBinding doctorListViewBinding = DoctorListViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
//
//        return new DoctorListViewHolder(doctorListViewBinding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull DoctorListViewHolder holder, int position) {
//
//        holder.SetUserData(doctorslist.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return doctorslist.size();
//    }
//
//
//    class DoctorListViewHolder extends RecyclerView.ViewHolder {
//
//        ///connect userdetail layout using binding
//        DoctorListViewBinding binding;
//
//        DoctorListViewHolder(DoctorListViewBinding doctorListViewBinding) {
//            super(doctorListViewBinding.getRoot());
//            binding = doctorListViewBinding;
//        }
//
//
//        //setuser data on row
//        void SetUserData(DoctorModel doctorModel) {
//            binding.NameOfDoctor.setText(doctorModel.DoctorName);
//            binding.NameOfClinic.setText(doctorModel.DoctorClinicName);
//            binding.ClinicAddress.setText(doctorModel.DoctorClinicAddress);
//            binding.Specialization.setText(doctorModel.DoctorSpecialization);
//        }
//
//    }

    Patient_DoctorSearch patient_doctorSearch;
    ArrayList<DoctorModel> arrayList;

//    FirebaseFirestore db;

    public DoctotListAdapter(Patient_DoctorSearch patient_doctorSearch,ArrayList<DoctorModel> arrayList) {
        this.patient_doctorSearch = patient_doctorSearch;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DoctorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new DoctorListViewHolder(LayoutInflater.from(patient_doctorSearch).inflate(R.layout.doctor_list_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorListViewHolder holder, int position) {
     //   holder.DoctorName.setText(arrayList.get(position).getDoctorName());
     //   holder.ClinicName.setText(arrayList.get(position).getDoctorClinicName());
     //   holder.ClinicAddress.setText(arrayList.get(position).getDoctorClinicAddress());
     //   holder.Specializaion.setText(arrayList.get(position).getDoctorSpecialization());
    }

    @Override
    public int getItemCount() {
        return arrayList.size()-1;
    }

    public class DoctorListViewHolder extends  RecyclerView.ViewHolder{

    TextView DoctorName,ClinicName,ClinicAddress,Specializaion;

    public DoctorListViewHolder(View view){
        super(view);

        DoctorName=view.findViewById(R.id.NameOfDoctor);
        ClinicName=view.findViewById(R.id.NameOfClinic);
        ClinicAddress=view.findViewById(R.id.ClinicAddress);
        Specializaion=view.findViewById(R.id.Specialization);
    }

    }
}