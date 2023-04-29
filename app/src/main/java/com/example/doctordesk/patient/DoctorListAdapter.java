package com.example.doctordesk.patient;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctordesk.R;

import java.util.List;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListViewHolder> {

    Context context;
    List<ClipData.Item> item;

    public DoctorListAdapter(Context context, List<ClipData.Item> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public DoctorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DoctorListViewHolder(LayoutInflater.from(context).inflate(R.layout.doctor_list_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorListViewHolder holder, int position) {
        holder.name.setText(item.get(position).getText());
        holder.Special.setText(item.get(position).getText());
        holder.ClinicName.setText(item.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return item.size();
    }
}
