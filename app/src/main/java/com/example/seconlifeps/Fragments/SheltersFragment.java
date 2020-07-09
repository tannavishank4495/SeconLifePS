package com.example.seconlifeps.Fragments;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seconlifeps.R;
import com.example.seconlifeps.adapters.Adapter_shelters;
import com.example.seconlifeps.interfaces.iShelters;
import com.example.seconlifeps.model.Business;

import java.util.ArrayList;

public class SheltersFragment extends Fragment{

    Adapter_shelters adapter_shelters;
    RecyclerView recyclerView;

    ArrayList<Business> business_list;

//reference to Reviews Fragment
    Activity activity;
    iShelters ishelters;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view    = inflater.inflate(R.layout.shelters_fragment,container,false);

        recyclerView = view.findViewById(R.id.recyclerViewShelters);   //layout name in shelters_fragment.xml

        // load list
        business_list = new ArrayList<>();
    //    loadData();
        business_list.add(new Business("4229 Dundas St W, Etobicoke, ON M8X\n" +
                "1Y3","L-V","9:v00-17:00","416-538-8592","info@torontocatrescue.ca","$123.00 CA",R.drawable.tcr_logo));
        business_list.add(new Business("7755 Bayview Ave, Markham, ON\n" +
                "L3T 4P1","L-V","9:00-17:00","905-762-1300","","$150.00 CA",R.drawable.mca_ec));
        business_list.add(new Business("475 Chrysler Dr, Brampton, ON L6S\n" +
                "5Z5","L,M,V","9:00-22:00","905-458-5800","animal.services@brampton.ca","$200.00 CA",R.drawable.bas));
        business_list.add(new Business("1300 Sheppard Ave W, Toronto, ON\n" +
                "M3K 2A6","L-D","8:v00-18:00","416-338-8723","info@ehs.com","$100.00 CA",R.drawable.ehs));
        business_list.add(new Business("67 Six Point Rd, Etobicoke, ON M8Z\n" +
                "2X3","L-S","8:00-20:00","416-249-6100","info@etobicokehumanesociety.com","$99.00 CA",R.drawable.ths));
        business_list.add(new Business("Dixon 1623","L-V","9:00-17:00","647-648-0714","info@ps.com","$223.00 CA",R.drawable.petsh5));




        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));    //cos it's a fragment

        adapter_shelters  = new Adapter_shelters(getContext(),business_list);

        recyclerView.setAdapter(adapter_shelters);

        adapter_shelters.notifyDataSetChanged();

        adapter_shelters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = business_list.get(recyclerView.getChildAdapterPosition(view)).getBu_address();
                Toast.makeText(getContext(),"selected " + address,Toast.LENGTH_LONG).show();

                //interface envia objecto
                ishelters.sendShelter(business_list.get(recyclerView.getChildAdapterPosition(view)));  //send all object

            }
        });

        System.out.println(business_list.size());




        return view;
    }

    public void loadData() {


    }

    public void showData() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            this.activity = (Activity) context;
            ishelters     = (iShelters) this.activity;


        }
    }
}
