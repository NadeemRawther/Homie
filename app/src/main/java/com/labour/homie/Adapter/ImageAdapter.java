package com.labour.homie.Adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.labour.homie.Entities.ImagAndText;
import com.labour.homie.LabourList;
import com.labour.homie.ListOfLabours;
import com.labour.homie.R;

import java.util.ArrayList;

public class ImageAdapter extends ArrayAdapter {
    private Context context;

    // Constructor
    ArrayList<ImagAndText> birdList = new ArrayList<>();

    public ImageAdapter(Context context, int textViewResourceId, ArrayList<ImagAndText> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        birdList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.layout_for_gridimage, null);
        TextView textView = (TextView) v.findViewById(R.id.textView);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        textView.setText(birdList.get(position).getTitle());
        Glide.with(context).load(birdList.get(position).getImage()).into(imageView);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*FragmentManager fragmentManager =((Activity)context).getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                LabourList fragment = new LabourList();
                fragmentTransaction.add(R.id.grid_view_items, new LabourList());
                fragmentTransaction.commit();*/
                Intent intent = new Intent(context.getApplicationContext(), ListOfLabours.class);
                intent.putExtra("link",birdList.get(position).getTitle());
                context.startActivity(intent);




            }
        });
        return v;
    }


}
