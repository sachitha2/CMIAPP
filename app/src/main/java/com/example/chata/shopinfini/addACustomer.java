package com.example.chata.shopinfini;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class addACustomer extends AppCompatActivity {
    private Spinner spinner1, spinner2;
    public RequestQueue mQue;
    public  Data data = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_add_acustomer);

        addItemsOnSpinner2();
//        jsonPrse("sam","1");
    }
    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }
    private  void jsonPrse(String uName,String pass){
        final ProgressDialog progressDialog = new ProgressDialog(addACustomer.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
//        mTextViewResults.setText( "jsonp ");
        String url = data.MAIN_URL + "/APP/login.json.php?uName="+uName+"&uPass="+pass;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.hide();

                        try {
                            String satus = response.getString("satus");
//                            mTextViewResults.setText( "responsed " + satus);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }






                        //call the methd
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
//                mTextViewResults.setText( "Not responsed");
            }
        });
        mQue.add(request);
    }
}
