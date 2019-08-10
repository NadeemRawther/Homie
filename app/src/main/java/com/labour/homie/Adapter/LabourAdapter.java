package com.labour.homie.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.labour.homie.Entities.CardForLabours;
import com.labour.homie.LabourProfile;
import com.labour.homie.R;

import java.util.ArrayList;

public class LabourAdapter extends RecyclerView.Adapter<LabourAdapter.ViewHolder> {
    TextView titles,detail,requests;
    ImageView imgphon,imgwhats_app;
    ArrayList<CardForLabours> list;
    Context context;
    RatingBar ratingBar;
    String category;

    public LabourAdapter(Context context, ArrayList<CardForLabours> arrayList,String link){
        this.list = arrayList;
        this.context = context;
        this.category = link;
    }
    @NonNull
    @Override
    public LabourAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView view = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_of_labours,viewGroup,false);
        return new LabourAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull LabourAdapter.ViewHolder viewHolder, final int i) {
        final int j;
        final CardView cardView = viewHolder.cardView;
        final Context context = cardView.getContext();
        titles = (TextView)cardView.findViewById(R.id.title);
        detail = (TextView)cardView.findViewById(R.id.details);
        requests = (TextView)cardView.findViewById(R.id.request);
        imgphon = (ImageView)cardView.findViewById(R.id.imgphone);
        ratingBar = (RatingBar)cardView.findViewById(R.id.ratingbarforlabour);
        ratingBar.setRating(Float.parseFloat(list.get(i).getRating().toString()));
        imgwhats_app = (ImageView)cardView.findViewById(R.id.whatsicon);
        ratingBar.setEnabled(false);
        titles.setText(list.get(i).getName());
        detail.setText(list.get(i).getPlace());
        requests.setText(list.get(i).getCharge());
        imgwhats_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.whatsapp.com/send?phone="+"+91"+list.get(i).getPhone();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });
        imgphon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ph = list.get(i).getPhone();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ph));
                context.startActivity(intent);
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(context, LabourProfile.class);
              intent.putExtra("userid",list.get(i).getUserid());
              intent.putExtra("category",category);
              context.startActivity(intent);

            }
        });

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardview_foroldage);
        }
    }
    @Override
    public int getItemCount() {
        int len = list.size();
        return len ;
    }
}