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

public class BusDataAdapter extends RecyclerView.Adapter<BusDataAdapter.ViewHolder> {

    Context context;
    int resource;
    ArrayList<Bus> objects;

    OnRecyclerItemClickListener recyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener recyclerItemClickListener){
    this.recyclerItemClickListener= recyclerItemClickListener;
    }

    public BusDataAdapter(Context context, int resource, ArrayList<Bus> objects) {


        this.context = context;
        this.resource = resource;
        this.objects = objects;


    }


    @NonNull
    @Override
    public BusDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resource, parent, false);
        final BusDataAdapter.ViewHolder holder = new BusDataAdapter.ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          recyclerItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bus bus  = objects.get(position);

        // Extracting Data from News Object and Setting the data on list_item

        holder.textbusNo.setText(bus.busno);
        holder.textKm.setText(bus.km);
        holder.textengine.setText(bus.engine);
        holder.textbattery.setText(bus.battery);
        holder.textonroute.setText(bus.onroute);
        holder.textmnfyear.setText(bus.mnfyear);
        holder.textairfilter.setText(bus.airfilter);
        holder.textdieselfilter.setText(bus.dieselfilter);
        holder.textengineoil.setText(bus.engineoil);
        holder.textaservicedate.setText(bus.aservicedate);
        holder.textaservicedue.setText(bus.aservicedue);
        holder.textbreaks.setText(bus.breaks);
        holder.textbearing.setText(bus.bearing);
        holder.textgrease.setText(bus.grease);
        holder.textbservicedate.setText(bus.bservicedate);
        holder.textbservicedue.setText(bus.bservicedue);


    }



    @Override
    public int getItemCount() {
        return objects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        TextView textbusNo;
        TextView textKm;
        TextView textmnfyear;
       // TextView textmodel;
        TextView textonroute;
        TextView textengine;
        TextView textbattery;
        TextView textairfilter;
        TextView textdieselfilter;
        TextView textengineoil;
        TextView textaservicedate;
        TextView textaservicedue;
        TextView textbreaks;
        TextView textbearing;
        TextView textgrease;
        TextView textbservicedate;
        TextView textbservicedue;


        public ViewHolder(View itemView) {
            super(itemView);


            textbusNo = itemView.findViewById(R.id.textViewbusno);
            textKm = itemView.findViewById(R.id.textViewbuskm);
            textmnfyear = itemView.findViewById(R.id.textViewbusmnfyear);
            textonroute = itemView.findViewById(R.id.textViewonroute);
            textengine = itemView.findViewById(R.id.textViewbusengine);
            textbattery = itemView.findViewById(R.id.textViewbusbattery);
            textairfilter = itemView.findViewById(R.id.textViewaf);
            textdieselfilter = itemView.findViewById(R.id.textViewDf);
            textengineoil = itemView.findViewById(R.id.textViewengineoil);
            textaservicedate = itemView.findViewById(R.id.textViewservicedatea);
            textaservicedue = itemView.findViewById(R.id.textViewserviceduea);
            textbreaks = itemView.findViewById(R.id.textViewbreak);
            textbearing = itemView.findViewById(R.id.textViewbearingcheck);
            textgrease = itemView.findViewById(R.id.textViewgrease);
            textbservicedate = itemView.findViewById(R.id.textViewservicedateb);
            textbservicedue = itemView.findViewById(R.id.textViewservicedueb);



        }

    }
}
