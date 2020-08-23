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
import com.example.seconlifeps.model.Reviews;

import java.util.ArrayList;

//import android.R.layout;

public class Adapter_reviews extends RecyclerView.Adapter<Adapter_reviews.ViewHolder> implements View.OnClickListener{

    ArrayList<Reviews> model;

    LayoutInflater inflater;

    //Listener
    private View.OnClickListener listener;


    public Adapter_reviews(Context context, ArrayList<Reviews> lista) {

        this.inflater = LayoutInflater.from(context);
        this.model    =  lista;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shelters_list,parent,false);
        View view = inflater.inflate(R.layout.review_list,parent,false);

        view.setOnClickListener(this);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String date   = model.get(position).getRv_date();
        String review = model.get(position).getRv_description();
    //    String hours   = model.get(position).getBu_visitHours();
    //    String contact = model.get(position).getBu_contactNo();
    //    String email   = model.get(position).getBu_email();
    //    String price   = model.get(position).getBu_price();
     //   int img  = model.get(position).getBu_imagenId();

        holder.date.setText(date);
        holder.review.setText(review);

//        holder.img.setImageResource(img);


    }

    @Override
    public int getItemCount() {
        return model.size();
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

        TextView date, review;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date   = itemView.findViewById(R.id.rev_date);
            review = itemView.findViewById(R.id.rev_review);
            img    = itemView.findViewById(R.id.rev_image);

        }
    }


}
