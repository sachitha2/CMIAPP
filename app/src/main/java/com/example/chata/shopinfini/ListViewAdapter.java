package com.example.chata.shopinfini;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewAdapter extends BaseAdapter {

    Context context;
    String[] Area;
    String[] Id;
    String From;

    public ListViewAdapter(Context context, String[] Area, String[] Id, String From){

        this.context = context;
        this.Area = Area;
        this.Id = Id;
        this.From = From;

    }



    @Override
    public int getCount() {
        return Id.length;
    }

    @Override
    public Object getItem(int position) {
        return Id[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(context);
        View btnView = myInflater.inflate(R.layout.btn_layout,null);

        TextView name = (TextView)btnView.findViewById(R.id.name);
        TextView id = (TextView)btnView.findViewById(R.id.id);

        name.setText(Area[position]);
        id.setText(Id[position]);

        switch (From){

            case "CreditsSelectArea":
                btnView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, Area[position] + " was clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case "SubjectList":
                btnView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                break;

            case "PracticeUnits":
                btnView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent intentPractice = new Intent();
                        ///intentPractice.setClass(context, Practice.class);
                        //intentPractice.putExtra("unitId", Ide[position]);
                        //intentPractice.putExtra("unitName", Namese[position]);

                        //context.startActivity(intentPractice);

                        //Toast.makeText(context, Id[position] + " was clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

        }

        return btnView;

    }

}
