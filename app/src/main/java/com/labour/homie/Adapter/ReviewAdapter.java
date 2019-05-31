package com.labour.homie.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.labour.homie.Entities.CardForLabours;
import com.labour.homie.Entities.CardReview;
import com.labour.homie.R;
import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
        TextView titles,detail,requests;
        ImageButton imgphon;
        ArrayList<CardReview> list;
        Context context;
public ReviewAdapter(Context context, ArrayList<CardReview> arrayList){
        this.list = arrayList;
        this.context = context;
        }


@NonNull
@Override
public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView view = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_review,viewGroup,false);
        return new ReviewAdapter.ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder viewHolder, final int i) {
final int j;

final CardView cardView = viewHolder.cardView;
final Context context = cardView.getContext();

        cardView.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {

        }
        });

        }
public static class ViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;

    public ViewHolder(View view) {
        super(view);

        cardView = (CardView) view.findViewById(R.id.cardview_forreview);

    }
}
    @Override
    public int getItemCount() {
        int len = list.size();
        return 10 ;
    }
}
