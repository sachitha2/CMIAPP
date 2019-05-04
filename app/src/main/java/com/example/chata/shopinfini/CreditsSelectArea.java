package com.example.chata.shopinfini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreditsSelectArea extends AppCompatActivity {

    public  Data data = new Data();

    private ListView areaList;
    private RequestQueue requestQueueForAreaList;
    private String[] Area;
    private String[] Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_select_area);

        areaList = (ListView) findViewById(R.id.areaList);
        requestQueueForAreaList = Volley.newRequestQueue(CreditsSelectArea.this);
        jsonParseAreaList();

    }

    private void jsonParseAreaList() {

        String url = "http://cms.infinisolutionslk.com/APP/area.json.php";
        Toast.makeText(CreditsSelectArea.this,  " was clicked", Toast.LENGTH_SHORT).show();
        JsonObjectRequest requestAreaList = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(CreditsSelectArea.this,  " was clicked", Toast.LENGTH_SHORT).show();
                        try {
                          //  Read json and assign them to local variables

                            JSONArray Larea = response.getJSONArray("area");
                            JSONArray Lid = response.getJSONArray("id");
                            Area = new String[Larea.length()];
                            Id = new String[Larea.length()];

                            for (int i = 0; i < Larea.length(); i++){
                                Area[i] = Larea.get(i).toString();
                                Id[i] = Lid.get(i).toString();
                            }

                            //then create the grid view
                            ListViewAdapter areaListViewAdapter = new ListViewAdapter(CreditsSelectArea.this, Area, Id, "CreditsSelectArea");
                            areaList.setAdapter(areaListViewAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueueForAreaList.add(requestAreaList);

    }
}
