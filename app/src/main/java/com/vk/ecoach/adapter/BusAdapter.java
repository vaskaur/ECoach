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

import java.util.ArrayList;

public class BusAdapter extends RecyclerView.Adapter<com.vk.ecoach.adapter.BusAdapter.ViewHolder> {

    Context context;
    int resource;
    ArrayList<Bus> objects;

    OnRecyclerItemClickListener recyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener recyclerItemClickListener){
        this.recyclerItemClickListener= recyclerItemClickListener;
    }

    public BusAdapter(Context context, int resource, ArrayList<Bus> objects) {


        this.context = context;
        this.resource = resource;
        this.objects = objects;


    }


    @NonNull
    @Override
    public BusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resource, parent, false);
        final BusAdapter.ViewHolder holder = new BusAdapter.ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BusAdapter.ViewHolder holder, int position) {

        Bus bus  = objects.get(position);

        // Extracting Data from News Object and Setting the data on list_item

        holder.txtbusNo.setText(bus.busno);
        holder.txtKm.setText(bus.km);

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        TextView txtbusNo;
        TextView txtKm;

        public ViewHolder(View itemView) {
            super(itemView);


            txtbusNo = itemView.findViewById(R.id.textViewBusNo);
            txtKm = itemView.findViewById(R.id.textViewKm);

        }

    }
}
