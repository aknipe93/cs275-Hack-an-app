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

public class DetailedHack extends AppCompatActivity {

    private final String TAG = DetailedHack.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_hack);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String name = intent.getExtras().getString("HackName");
        String Location = intent.getExtras().getString("HackLocation");
        String Date = intent.getExtras().getString("HackDate");
        String URLHack = intent.getExtras().getString("HackURL");
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

        WebView urlt = new WebView(this);
        urlt = (WebView) findViewById(R.id.webView);
        urlt.loadUrl(URLHack);
    }

}
