package com.example.alex.hack_a_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DetailedHack extends AppCompatActivity {

    private final String TAG = DetailedHack.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_hack);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DetailedHack self = this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        final String name = intent.getExtras().getString("HackName");
        final String Location = intent.getExtras().getString("HackLocation");
        final String Date = intent.getExtras().getString("HackDate");
        final String URLHack = intent.getExtras().getString("HackURL");
        Log.d(TAG, name);
        Log.d(TAG, URLHack);
        TextView nt = new TextView(this);
        TextView lt = new TextView(this);
        TextView dt = new TextView(this);
        nt=(TextView)findViewById(R.id.NameText);
        lt=(TextView)findViewById(R.id.LocationText);
        dt=(TextView)findViewById(R.id.dateText);
        nt.setText(name);
        lt.setText(Location);
        dt.setText(Date);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(self);
                boolean flag = false;
                List<favDB> listOfFaves = db.getAllFavs();

                for (int i = 0; i < listOfFaves.size(); i++) {

                    if (listOfFaves.get(i).name.equals(name)) {
                        Toast.makeText(getApplicationContext(), Integer.toString(db.getFavCount()),
                                Toast.LENGTH_LONG).show();
                        db.delete_byID(listOfFaves.get(i).getId());
                        Toast.makeText(getApplicationContext(), Integer.toString(db.getFavCount()),
                                Toast.LENGTH_LONG).show();
                        flag = true;
                    }
                }
                if (flag == false) {
                    Toast.makeText(getApplicationContext(), "Added to Favorites",
                            Toast.LENGTH_LONG).show();
                    db.addFav(new favDB( name, Date, Location, URLHack));
                } else {
                    Toast.makeText(getApplicationContext(), "Removed from Favorites",
                            Toast.LENGTH_LONG).show();
                }


            }
        });
        WebView urlt = new WebView(this);
        urlt = (WebView) findViewById(R.id.webView);
        urlt.loadUrl(URLHack);
    }

}
