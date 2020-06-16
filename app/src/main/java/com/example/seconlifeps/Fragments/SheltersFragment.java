package com.example.seconlifeps.Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seconlifeps.R;
import com.example.seconlifeps.adapters.Adapter_shelters;
import com.example.seconlifeps.model.Business;

import java.util.ArrayList;

public class SheltersFragment extends Fragment{

    Adapter_shelters adapter_shelters;
    RecyclerView recyclerView;

    ArrayList<Business> business_list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view    = inflater.inflate(R.layout.shelters_fragment,container,false);

        recyclerView = view.findViewById(R.id.recyclerViewShelters);   //layout name in shelters_fragment.xml

        // load list
        business_list = new ArrayList<>();
    //    loadData();
        business_list.add(new Business("Dixon 12433","L-vV","9:v00-17:00","647-648-0714","123@123.com","$123.00 CA",R.drawable.petsh3));
        business_list.add(new Business("Dixon 12543","L-V","9:00-17:00","647-648-0714","123@123.com","$123.00 CA",R.drawable.petsh5));
        business_list.add(new Business("Dixon 1623","L-V","9:00-17:00","647-648-0714","123@123.com","$123.00 CA",R.drawable.petsh5));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));    //cos it's a fragment
        adapter_shelters  = new Adapter_shelters(getContext(),business_list);
        recyclerView.setAdapter(adapter_shelters);

        adapter_shelters.notifyDataSetChanged();

        System.out.println(business_list.size());

        return view;
    }

    public void loadData() {


    }

    public void showData() {

    }
}
