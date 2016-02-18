package com.example.alex.hack_a_app;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.content.Intent;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ListView list;
    private ArrayAdapter<String> listAdapter;
    private MainActivity self;
    private final String TAG = MainActivity.class.getSimpleName();
    public final static String EXTRA_MESSAGE = "com.example.alex.hack_a_app.DetailedHack.MESSAGE";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        self = this;
        Log.d(TAG, "main");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        list = (ListView) findViewById(R.id.listView);
//        URL oracle = null;
//        try {
        try {
            URL url = new URL("https://raw.githubusercontent.com/japacible/Hackathon-Calendar/master/README.md");
            Log.d(TAG, "Try");
            new DownloadFilesTask().execute(url);
        } catch (MalformedURLException e) {
            Log.d(TAG, "caught");
            e.printStackTrace();
        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent MapIntent = new Intent(self, MapsActivity.class);
            startActivity(MapIntent);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.alex.hack_a_app/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.alex.hack_a_app/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private class DownloadFilesTask extends AsyncTask<URL, Integer, List<String>> {
        private final String TAG = DownloadFilesTask.class.getSimpleName();

        public DownloadFilesTask() {

        }

        protected List<String> doInBackground(URL... urls) {
            Log.d(TAG, "In download");
            List<String> allLines = new ArrayList<String>();
            try {
//                conn = url.openConnection();
//            // Get the response
//                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                String[] line = ["a", "b"];
//                return line;
                URL oracle = new URL("https://raw.githubusercontent.com/japacible/Hackathon-Calendar/master/README.md");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(oracle.openStream()));

                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.indexOf("| [") >= 0) {
                        allLines.add(inputLine);
                    }
                }
//        System.out.println(allLines.get(9).toString());
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return allLines;
        }

        protected void onPostExecute(List<String> result) {
//            showDialog("Downloaded " + result + " bytes");
            final Hackathon[] hackArray = new Hackathon[result.size()];

            ArrayList<String> nameList = new ArrayList<String>();
            Log.d(TAG, "In post");

            for (int i = 0; i < result.size(); i++) {
                String sub = result.get(i).toString();
                String delims2 = "[|]";
                String[] parts = sub.split(delims2);
//            System.out.println(parts[1]);
                String name = parts[1].split("[\\[\\]]")[1];
                String url = parts[1].split("[\\(\\)]")[1];
                hackArray[i] = new Hackathon();
                hackArray[i].name = name;
                hackArray[i].URLHack = url;
                hackArray[i].Location = parts[2];
                hackArray[i].Date = parts[3];
                nameList.add(hackArray[i].name);
            }
            listAdapter = new ArrayAdapter<String>(self, R.layout.row, R.id.rowTextView, nameList);
            list.setAdapter(listAdapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    Log.d(TAG, Integer.toString(position));
                    Intent intent = new Intent(self, DetailedHack.class);
//                    Bundle b = new Bundle();
//                    b.putParcelable(Constants.Hackathon, hackArray[position]);
                    String name = hackArray[position].name;
                    String URLHack = hackArray[position].URLHack;
                    String Location = hackArray[position].Location;
                    String Date = hackArray[position].Date;
                    intent.putExtra("HackName",  name);
                    intent.putExtra("HackURL",  URLHack);
                    intent.putExtra("HackLocation",  Location);
                    intent.putExtra("HackDate",  Date);
                    startActivity(intent);
                }
            });
        }
    }

}
class Hackathon{
    String name;
    String URLHack;
    String Location;
    String Date;
}