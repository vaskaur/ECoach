package com.vk.ecoach.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vk.ecoach.Listener.OnRecyclerItemClickListener;
import com.vk.ecoach.R;
import com.vk.ecoach.model.Bus;
import com.vk.ecoach.model.Job;

import java.util.ArrayList;

public class JobCardAdapter extends RecyclerView.Adapter<com.vk.ecoach.adapter.JobCardAdapter.ViewHolder> {

    Context context;
    int resource;
    ArrayList<Job> objects;

    OnRecyclerItemClickListener recyclerItemClickListener;


    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener recyclerItemClickListener){
        this.recyclerItemClickListener= recyclerItemClickListener;
    }

    public JobCardAdapter(Context context, int resource, ArrayList<Job> objects) {


        this.context = context;
        this.resource = resource;
        this.objects = objects;


    }



    @NonNull
    @Override
    public JobCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resource, parent, false);
        final JobCardAdapter.ViewHolder holder = new JobCardAdapter.ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobCardAdapter.ViewHolder holder, int position) {

        Job job  = objects.get(position);

        // Extracting Data from News Object and Setting the data on list_item

        holder.txtjbusNo.setText(job.busnojob);
        holder.txtjdate.setText(job.date);

    }

    @Override
    public int getItemCount()  {
        return objects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        TextView txtjbusNo;
        TextView txtjdate;

        public ViewHolder(View itemView) {
            super(itemView);


            txtjbusNo = itemView.findViewById(R.id.textViewjbusno);
            txtjdate = itemView.findViewById(R.id.textViewjdate);
        }
    }
}
