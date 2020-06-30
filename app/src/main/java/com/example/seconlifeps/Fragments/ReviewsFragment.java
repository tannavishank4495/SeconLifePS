package com.example.seconlifeps.Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.seconlifeps.R;
import com.example.seconlifeps.model.Business;

public class ReviewsFragment extends Fragment{

    TextView textView;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.reviews_fragment,container,false);

        textView = view.findViewById(R.id.shelterDetail_text1);
        imageView= view.findViewById(R.id.shelterDetail_image);

        // bundle object to receive object
        Bundle objectShelter = getArguments();
        Business business = null;

        if (objectShelter != null)
        {
            business = (Business) objectShelter.getSerializable("objecto");
            // set data
            textView.setText(business.getBu_address());
            imageView.setImageResource(business.getBu_imagenId());
        }



        return view;
    }
}
