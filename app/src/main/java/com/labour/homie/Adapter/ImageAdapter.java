package com.labour.homie.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.labour.homie.Entities.ImagAndText;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.layout_for_gridimage, null);
        TextView textView = (TextView) v.findViewById(R.id.textView);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        textView.setText(birdList.get(position).getTitle());
        imageView.setImageResource(birdList.get(position).getImage());
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // FragmentManager fragmentManager = context.getApplicationContext().getString();
               // FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            }
        });
        return v;

    }


}
