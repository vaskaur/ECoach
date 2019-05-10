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
import com.vk.ecoach.model.Schedule;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<com.vk.ecoach.adapter.ScheduleAdapter.ViewHolder> {

    Context context;
    int resource;
    ArrayList<Schedule> objects;

    OnRecyclerItemClickListener recyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener recyclerItemClickListener){
        this.recyclerItemClickListener= recyclerItemClickListener;
    }

    public ScheduleAdapter(Context context, int resource, ArrayList<Schedule> objects) {


        this.context = context;
        this.resource = resource;
        this.objects = objects;


    }


    @NonNull
    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resource, parent, false);
        final ScheduleAdapter.ViewHolder holder = new ScheduleAdapter.ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ViewHolder holder, int position) {

        Schedule schedule  = objects.get(position);

        // Extracting Data from News Object and Setting the data on list_item

        holder.txtbusNo.setText(schedule.busnum);
        holder.txtdate.setText(schedule.date);
        holder.txttype.setText(schedule.type);

    }

    @Override
    public int getItemCount()  {
        return objects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        TextView txtbusNo;
        TextView txtdate;
        TextView txttype;

        public ViewHolder(View itemView) {
            super(itemView);


            txtbusNo = itemView.findViewById(R.id.textViewbusnos);
            txtdate = itemView.findViewById(R.id.textViewdate);
            txttype = itemView.findViewById(R.id.textViewcomment);

        }

    }
}
