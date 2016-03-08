package com.example.alex.hack_a_app;

import android.content.Intent;
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
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


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
//        https://maps.googleapis.com/maps/api/geocode/json?address=santa+cruz&components=country:ES&key=YOUR_API_KEY
        // Add a marker in Sydney and move the camera
//        getLatLong(getLocationInfo("Philadelphia, +PA"));

        Intent intent = getIntent();
//        Parcelable[] hackArray = intent.getParcelableArrayExtra("HackArray");

        String[][] arrayReceived=null;
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
            mMap.addMarker(new MarkerOptions().position(location).title(arrayReceived[i][0]));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

        }




    }
//    private static JSONObject getLocationInfo(String address)
//    {
//
//        StringBuilder stringBuilder = new StringBuilder();
//        try {
//
//            address = address.replaceAll(" ","%20");
//
//            HttpPost httppost = new HttpPost("http://maps.google.com/maps/api/geocode/json?address=" + address + "&key=AIzaSyBt4YXm6eVS10jiPFXqli94MRRcF7DGl3o");
//            HttpClient client = new DefaultHttpClient();
//            HttpResponse response;
//            stringBuilder = new StringBuilder();
//
//
//            response = client.execute(httppost);
//            HttpEntity entity = response.getEntity();
//            InputStream stream = entity.getContent();
//            int b;
//            while ((b = stream.read()) != -1) {
//                stringBuilder.append((char) b);
//            }
//        } catch (IOException e) {
//
//            Log.i("getLocIOException", e.toString());
//        }
//
//
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject = new JSONObject(stringBuilder.toString());
//        } catch (JSONException e) {
//            // TODO Auto-generated catch block
//            Log.i("getLocJSONException", e.toString());
//        }
//
//        return jsonObject;
//    }
//
//    private static double[] getLatLong(JSONObject jsonObject)
//    {
//
//        double longitute;
//        double latitude;
//        try {
//
//            longitute = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");
//            Log.i("Log1", longitute+"");
//            latitude = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
//            Log.i("lat1", latitude+"");
//        } catch (JSONException e) {
//
//            longitute=0;
//            latitude = 0;
//            Log.i("getLatLong", e.toString());
//
//        }
//        double[] latLong = new double[0];
//        latLong[0]= longitute;
//        latLong[1] =  latitude;
//        return latLong;
//    }
}
