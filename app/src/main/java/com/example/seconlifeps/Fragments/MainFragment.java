package com.example.seconlifeps.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.seconlifeps.R;
import com.example.seconlifeps.model.SQLiteHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class MainFragment extends Fragment implements OnMapReadyCallback {

    public static SQLiteHelper mySqliteHelper;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    String userId;

    GoogleMap mGoogleMap;
    MapView mapView;

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);

        userId = "0";
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userId = bundle.getString("userId", "0");
        }
        Log.d("Main Fragment", userId.toString());










        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // checking user permission of location (GPS)
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }


        mapView = view.findViewById(R.id.mapView);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }


    }

    private void getUserLocation() {

        Log.d("getUserLocation()","-");
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //   Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d("getUserLocation()","0");
            return;
        }
        Log.d("getUserLocation()","1");
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    currentLocation = location;
                    Toast.makeText(getActivity(), currentLocation.getLatitude()+ " " + currentLocation.getLongitude(),Toast.LENGTH_LONG).show();
                    Log.d("Current Location",currentLocation.getLatitude()+" "+currentLocation.getLongitude());
                }


            }
        });
    }

    private void saveUserLocation() {


        try {
            mySqliteHelper.updateUserLocation(currentLocation.getLatitude(),currentLocation.getLongitude(),Integer.parseInt(userId));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Toast.makeText(getActivity(),"User Location Updated!", Toast.LENGTH_SHORT).show();
        Log.d("User Location Updated", userId.toString());


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;

        // Enable current location
        // checking user permission of location (GPS)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        getUserLocation();
        // Update record
        saveUserLocation();


        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Marker 1
        LatLng latlon = new LatLng(43.659868,-79.512026);
        MarkerOptions options = new MarkerOptions().position(latlon).title("Toronto Cat Rescue").snippet("4229 Dundas St W, Etobicoke, ON M8X\n" +
                "1Y3");
        googleMap.addMarker(options);

        // Marker 2
        latlon = new LatLng(43.829171,-79.396483);
        options = new MarkerOptions().position(latlon).title("Markham Cat Adoption &\n" +
                "Education Centre").snippet("7755 Bayview Ave, Markham, ON\n" +
                "L3T 4P1");
        googleMap.addMarker(options);

        // Marker 3
        latlon = new LatLng(43.686710,-79.760320);
        options = new MarkerOptions().position(latlon).title("Brampton Animal Services").snippet("475 Chrysler Dr, Brampton, ON L6S\n" +
                "5Z5");
        googleMap.addMarker(options);

        // Marker 4
        latlon = new LatLng(43.773174,-79.481540);
        options = new MarkerOptions().position(latlon).title("Toronto Animal Services,\n" +
                "North Shelter").snippet("1300 Sheppard Ave W, Toronto, ON\n" +
                "M3K 2A6");
        googleMap.addMarker(options);

        // Marker 5
        latlon = new LatLng(43.644354,-79.527953);
        options = new MarkerOptions().position(latlon).title("TEtobicoke Humane Society").snippet("67 Six Point Rd, Etobicoke, ON M8Z\n" +
                "2X3");
        googleMap.addMarker(options);

        CameraPosition cameraPosition = CameraPosition.builder().target(latlon).zoom(10).bearing(0).tilt(0).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }


}
