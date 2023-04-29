package com.example.doctordesk.patient;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctordesk.R;

public class DoctorListViewHolder extends RecyclerView.ViewHolder  {
    ImageView DoctorImage;
    TextView name;
    TextView Special;
    TextView ClinicName;

    public DoctorListViewHolder(@NonNull View itemView) {
        super(itemView);
        DoctorImage=itemView.findViewById(R.id.imageDoctor);
        name=itemView.findViewById(R.id.NameOfDoctor);
        Special=itemView.findViewById(R.id.Specialization);
        ClinicName=itemView.findViewById(R.id.NameOfClinic);
    }
}
