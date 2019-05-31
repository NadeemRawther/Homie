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
import android.widget.TextView;

import com.labour.homie.Entities.CardForLabours;
import com.labour.homie.LabourProfile;
import com.labour.homie.R;

import java.util.ArrayList;

public class LabourAdapter extends RecyclerView.Adapter<LabourAdapter.ViewHolder> {

    TextView titles,detail,requests;
    ImageButton imgphon;
    ArrayList<CardForLabours> list;
    Context context;

    public LabourAdapter(Context context, ArrayList<CardForLabours> arrayList){
        this.list = arrayList;
        this.context = context;
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
        imgphon = (ImageButton)cardView.findViewById(R.id.imgphone);
        titles.setText(list.get(i).getName());
        detail.setText(list.get(i).getPlace());
        requests.setText(list.get(i).getCharge());
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