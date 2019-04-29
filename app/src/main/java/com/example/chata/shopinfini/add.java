package com.example.chata.shopinfini;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class add extends AppCompatActivity {
    Button btnAddCustomer;
    final private String  TAG = "add";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btnAddCustomer = findViewById(R.id.btnAddCustomer);
        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: add customer btn clicked" );
                Intent startIntent =new Intent(getApplicationContext(),addACustomer.class);
                startActivity(startIntent);

            }
        });
    }
}
