package com.example.alex.hack_a_app;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    public String[][] arrayReceived=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Object[] objectArray = (Object[]) getIntent().getExtras().getSerializable("HackArray");
        if(objectArray!=null){
            arrayReceived = new String[objectArray.length][];
            for(int i=0;i<objectArray.length;i++){
                arrayReceived[i]=(String[]) objectArray[i];
            }
        }


        Geocoder gc = new Geocoder(this);
        double lat = 0,lng = 0;
        for (int i = 0;i<arrayReceived.length;i++){
            if(gc.isPresent()){
                List<Address> list = null;
                try {
                    list = gc.getFromLocationName(arrayReceived[i][2], 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (list.get(0) != null) {
                    Address address = list.get(0);


                    lat = address.getLatitude();
                    lng = address.getLongitude();
                }
            }

            LatLng location = new LatLng(lat, lng);
//            mMap.setOnMarkerClickListener(this);

            Marker marker = mMap.addMarker(new MarkerOptions().position(location).title(arrayReceived[i][0]).snippet(arrayReceived[i][3]));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            marker.showInfoWindow();
            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                @Override
                public void onInfoWindowClick(Marker arg0) {
                    // TODO Auto-generated method stub
//
                    String url = "",location = "",date = "";


                    for (int i = 0;i<arrayReceived.length;i++){
                        if (arg0.getTitle() == arrayReceived[i][0]){
                            url = arrayReceived[i][1];
                            location = arrayReceived[i][2];
                            date = arrayReceived[i][3];
                            break;
                        }
                    }
                    Intent intent = new Intent(MapsActivity.this, DetailedHack.class);
                    intent.putExtra("HackName",  arg0.getTitle());
                    intent.putExtra("HackURL",  url);
                    intent.putExtra("HackLocation", location);
                    intent.putExtra("HackDate", date);
                    startActivity(intent);
                }
            });



        }

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
