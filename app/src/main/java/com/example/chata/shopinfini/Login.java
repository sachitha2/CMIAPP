package com.example.chata.shopinfini;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    public  Data data = new Data();
    public RequestQueue mQue;
    public TextView mTextViewResults;
    public EditText uName,pass;
    public String uN,pa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        mQue = Volley.newRequestQueue(this);
        mTextViewResults = findViewById(R.id.msg);
        Button buttonP = findViewById(R.id.button);
        SharedPreferences pref = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = pref.getBoolean("firstStart", true);
        if(firstStart){

            buttonP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTextViewResults.setText("hello");




                    if (haveNet()) {
                        uName = findViewById(R.id.txtUName);
                        pass = findViewById(R.id.txtPassword);

                        uN = uName.getText().toString();
                        pa = pass.getText().toString();
                        if (uN.length() == 0) {
                            mTextViewResults.setText("Enter User name");
                        } else if (pa.length() == 0) {
                            mTextViewResults.setText("Enter Password");
                        } else {
                            jsonPrse(uN, pa);
                        }

                    } else if (!haveNet()) {
//                    Toast.makeText(login.this,"Network connectin is not available",Toast.LENGTH_SHORT).show();
                        openDialog();
                    }

                }
            });

        }else {
            this.finish();
            Intent startIntent =new Intent(getApplicationContext(),MainActivity.class);
            startActivity(startIntent);
        }






    }

    private  void jsonPrse(String uName,String pass){
        ProgressDialog progressDialog = new ProgressDialog(Login.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        mTextViewResults.setText( "jsonp ");
        String url = data.MAIN_URL + "/APP/login.json.php?uName="+uName+"&uPass="+pass;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            String satus = response.getString("satus");
                            mTextViewResults.setText( "responsed " + satus);
                            if(satus.equals("1")){
                                mTextViewResults.setText( "Login ok");
                                SharedPreferences pref =getSharedPreferences("prefs",MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putBoolean("firstStart",false);
                                editor.apply();

                                //new intent code here


                                Intent startIntent =new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(startIntent);
                                finish();

                            }else{
                                mTextViewResults.setText( "Login Failed" +satus);
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }






                        //call the methd
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                mTextViewResults.setText( "Not responsed");
            }
        });
        mQue.add(request);
    }
    public void openDialog() {
        ExampleDialog ex = new ExampleDialog();
        ex.show(getSupportFragmentManager(),"example dialog");
    }
    private  boolean haveNet(){
        boolean haveWifi = false;
        boolean haveMobileData = false;
        ConnectivityManager connectivityManager =(ConnectivityManager) getSystemService( CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
        for(NetworkInfo info:networkInfos){
            if(info.getTypeName().equalsIgnoreCase("WIFI")){
                if(info.isConnected()){
                    haveWifi  = true;
                }

            }
            if(info.getTypeName().equalsIgnoreCase("MOBILE")){
                if(info.isConnected()){
                    haveMobileData = true;
                }

            }
        }
        return haveMobileData || haveWifi;
    }



}
