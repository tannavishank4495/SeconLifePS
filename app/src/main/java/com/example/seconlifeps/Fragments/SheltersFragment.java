package com.example.seconlifeps.Fragments;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
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
import com.example.seconlifeps.model.SQLiteHelper;

import java.util.ArrayList;

public class SheltersFragment extends Fragment{

    public static SQLiteHelper mySqliteHelper;

    String userId;
    Double userlat,userlong;

    Adapter_shelters adapter_shelters;
    RecyclerView recyclerView;

    ArrayList<Business> business_list, busines_nearby;

    //reference to Reviews Fragment
    Activity activity;
    iShelters ishelters;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view    = inflater.inflate(R.layout.shelters_fragment,container,false);

        userId = "0";
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userId = bundle.getString("userId", "0");
        }
        Log.d("Shelters Fragment", userId.toString());

        // Getting last location from User
        mySqliteHelper = new SQLiteHelper(this.getContext(),"RECORDDB1.sqlite",null,1);

        String sqlstr = "SELECT us_id,us_email, us_firstName, us_lastName, us_latitude, us_longitude " +
                "FROM User WHERE us_id = " + userId;
        Cursor cursor = mySqliteHelper.getData(sqlstr);

        int c = cursor.getCount();
        if (c == 0)
        {
            Log.d("User:","NOT FOUND");
            System.out.println("Users: "+c);
            Toast.makeText(getActivity(),"No User found", Toast.LENGTH_SHORT).show();
            // show error ;
        }
        // Valid
        else
        {
            Log.d("User:","FOUND");
            System.out.println("Users: "+c);
        }
        while(cursor.moveToNext())
        {
            int id       = cursor.getInt(0);
            String email = cursor.getString(1);
            String fname  = cursor.getString(2);
            String lname  = cursor.getString(3);
            String lat  = cursor.getString(4);
            String lon  = cursor.getString(5);
            Log.d("User:",email);
            Log.d("Last location:",lat+","+lon);
            userlat = Double.parseDouble(lat);
            userlong= Double.parseDouble(lon);
        }


        recyclerView = view.findViewById(R.id.recyclerViewShelters);   //layout name in shelters_fragment.xml

        business_list = new ArrayList<>();
        busines_nearby = new ArrayList<>();


        // Fetch all shelters available from db.   (dymanic version - 2 phase)
        /*
        sqlstr = "SELECT bu_id, bu_name, bu_address, bu_visitDays, bu_visitHours, bu_contactNo, bu_email, bu_price, bu_imagenId, bu_lat, bu_lon " +
                "FROM Business";   //  WHERE bu_id = " + businessId;
        cursor = mySqliteHelper.getData(sqlstr);

        c = cursor.getCount();
        if (c == 0)
        {
            Log.d("Businesses:","NOT FOUND");
            Toast.makeText(getActivity(),"No shelters found nearby", Toast.LENGTH_SHORT).show();
            // show error ;
        }
        // Valid
        else
        {
            System.out.println("Business found: "+c);
        }
        while(cursor.moveToNext())
        {
            int id        = cursor.getInt(0);
            String bname  = cursor.getString(1);
            String baddr  = cursor.getString(2);
            String bdays  = cursor.getString(3);
            String bhours = cursor.getString(4);
            String bno    = cursor.getString(5);
            String bemail = cursor.getString(6);
            String bprice = cursor.getString(7);
            byte[] image = cursor.getBlob(8);
            Double blat   = cursor.getDouble(9);
            Double blon   = cursor.getDouble(10);
            Log.d("Business:",bname);

            business_list.add(new Business(id,bname,baddr,bdays,bhours,bno,bemail,bprice,image,blat,blon));


        }

*/



        //------------
        // Creating the list of shelters (static version)
        business_list.add(new Business(1,"","4229 Dundas St W, Etobicoke, ON M8X\n" +
                "1Y3","L-V","9:v00-17:00","416-538-8592","info@torontocatrescue.ca","$123.00 CA",R.drawable.tcr_logo,43.659868,-79.512026));
        business_list.add(new Business(2,"","7755 Bayview Ave, Markham, ON\n" +
                "L3T 4P1","L-V","9:00-17:00","905-762-1300","","$150.00 CA",R.drawable.mca_ec,43.829171,-79.396483));
        business_list.add(new Business(3,"","475 Chrysler Dr, Brampton, ON L6S\n" +
                "5Z5","L,M,V","9:00-22:00","905-458-5800","animal.services@brampton.ca","$200.00 CA",R.drawable.bas,43.686710,-79.760320));
        business_list.add(new Business(4,"","1300 Sheppard Ave W, Toronto, ON\n" +
                "M3K 2A6","L-D","8:v00-18:00","416-338-8723","info@ehs.com","$100.00 CA",R.drawable.ehs,43.773174,-79.481540));
        business_list.add(new Business(5,"","67 Six Point Rd, Etobicoke, ON M8Z\n" +
                "2X3","L-S","8:00-20:00","416-249-6100","info@etobicokehumanesociety.com","$99.00 CA",R.drawable.ths,43.644354,-79.527953));
        business_list.add(new Business(6,"","290 Dixon Rd.,Etobicoke, ON","L-V","9:00-17:00","647-648-0714","info@ps.com","$223.00 CA",R.drawable.petsh5,43.696468, -79.545792));


        //Selecting shelters by user location

        for (Business bu : business_list) {
            System.out.println(bu.getBu_email()+":"+bu.getBu_lat()+","+bu.getBu_lon());
            //nearby(userlat,userlong,bu.getBu_lat(),bu.getBu_lon(),0.02);
            if (nearby(userlat,userlong,bu.getBu_lat(),bu.getBu_lon(),0.02))
            {
                Log.d("Nearby :",bu.getBu_email());
                //add to selected list
                busines_nearby.add(bu);
            }



        }




        ////

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));    //cos it's a fragment

        adapter_shelters  = new Adapter_shelters(getContext(),busines_nearby);

        recyclerView.setAdapter(adapter_shelters);

        adapter_shelters.notifyDataSetChanged();

        adapter_shelters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = busines_nearby.get(recyclerView.getChildAdapterPosition(view)).getBu_address();
                Toast.makeText(getContext(),"selected " + address,Toast.LENGTH_LONG).show();

                //interface envia objecto
              //  ishelters.sendShelter(business_list.get(recyclerView.getChildAdapterPosition(view)));  //send all object
                ishelters.sendShelter(busines_nearby.get(recyclerView.getChildAdapterPosition(view))); //send the object
            }
        });

        System.out.println(business_list.size());




        return view;
    }

    public void loadData() {


    }

    public void showData() {

    }

    public boolean nearby(Double userlat, Double userlong, Double bu_lat, Double bu_lon, Double r)
    {
        //using a square perimeter
        Double limitN, limitS, limitE, limitW;
        limitN = userlat + r;
        limitS = userlat - r;
        limitE     = userlong + r;
        limitW     = userlong - r;
        Log.d("User Lat-Long:",userlat.toString()+"-"+userlong.toString());
        Log.d("Limits N-S-E-W:",limitN.toString()+"-"+limitS.toString()+"-"+limitE.toString()+"-"+limitW.toString());
        Log.d("Business Lat-Long:",bu_lat.toString()+"-"+bu_lon.toString());


        if (bu_lat <= limitN && bu_lat >= limitS) {
            Log.d("inside limits N/S ","ok");

            if (bu_lon <= limitE && bu_lon >= limitW) {
                Log.d("inside limits E/W ","ok");
                Log.d("------------:","----------");
                return true;
            }
        }
        Log.d("------------:","----------");
        return false;
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
