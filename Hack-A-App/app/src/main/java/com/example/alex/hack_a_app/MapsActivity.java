package com.example.alex.hack_a_app;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

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
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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
