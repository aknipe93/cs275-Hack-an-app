package com.example.alex.hack_a_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        final FavActivity self = this;
        DBHelper db = new DBHelper(self);
        List<favDB> listOfHacks = db.getAllFavs();
        ListView list = (ListView) findViewById(R.id.listView2);
        final Hackathon[] hackArray = new Hackathon[listOfHacks.size()];
        ArrayList<String> nameList = new ArrayList<String>();
        for(int i = 0; i < listOfHacks.size(); i++ ){
            hackArray[i] = new Hackathon();
            hackArray[i].name = listOfHacks.get(i).name;
            hackArray[i].Location = listOfHacks.get(i).urlHack;
            hackArray[i].URLHack = listOfHacks.get(i).location;
            hackArray[i].Date =listOfHacks.get(i).date;
            nameList.add(hackArray[i].name +"\t\t\t\t"+hackArray[i].Date);
        }
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(self, R.layout.row, R.id.rowTextView, nameList);
        list.setAdapter(listAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(self, DetailedHack.class);

                String name = hackArray[position].name;
                String URLHack = hackArray[position].URLHack;
                String Location = hackArray[position].Location;
                String Date = hackArray[position].Date;
                intent.putExtra("HackName", name);
                intent.putExtra("HackURL", URLHack);
                intent.putExtra("HackLocation", Location);
                intent.putExtra("HackDate", Date);

                startActivity(intent);
            }
        });
    }
}
