package com.example.android.pets;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.android.pets.data.MyOpenHelper;
import com.example.android.pets.data.User;

import java.util.ArrayList;

public class activity_catalog_user extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_user);


        FloatingActionButton fab_user = (FloatingActionButton) findViewById(R.id.fab_user);

        fab_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_catalog_user.this, activity_editor_user.class);
                startActivity(intent);
            }
        });


    }
}
