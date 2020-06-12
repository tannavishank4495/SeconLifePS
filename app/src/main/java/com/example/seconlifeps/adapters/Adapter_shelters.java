package com.example.seconlifeps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seconlifeps.R;
import com.example.seconlifeps.model.Business;

import java.util.ArrayList;

public class Adapter_shelters extends RecyclerView.Adapter<Adapter_shelters.ViewHolder> implements View.OnClickListener{

    ArrayList<Business> model;

    LayoutInflater inflater;

    //Listener
    private View.OnClickListener listener;


    public Adapter_shelters(Context context, ArrayList<Business> model) {

        this.inflater = LayoutInflater.from(context);
        this.model    =  model;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.shelters_list,parent,false);

        view.setOnClickListener(this);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String adreess = model.get(position).getBu_address();
        String days    = model.get(position).getBu_visitDays();
        String hours   = model.get(position).getBu_visitHours();
        String contact = model.get(position).getBu_contactNo();
        String email   = model.get(position).getBu_email();
        String price   = model.get(position).getBu_price();
        int    imagen  = model.get(position).getBu_imagenId();

        holder.address.setText(adreess);
        holder.visitDays.setText(days);
        holder.visitHours.setText(hours);
        holder.contactNo.setText(contact);
        holder.email.setText(email);
        holder.price.setText(price);
        holder.image.setImageResource(imagen);


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onClick(View view) {

        if (listener != null)
        {
            listener.onClick(view);
        }

    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;


    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView address, visitDays, visitHours, contactNo, email, price;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            address = itemView.findViewById(R.id.address);
            visitDays = itemView.findViewById(R.id.visit_days);
            visitHours = itemView.findViewById(R.id.visit_hours);
            contactNo  = itemView.findViewById(R.id.contact_number);
            email  = itemView.findViewById(R.id.contact_email);
            price  = itemView.findViewById(R.id.price);
            image  = itemView.findViewById(R.id.simpleSwitch);


        }
    }


}
