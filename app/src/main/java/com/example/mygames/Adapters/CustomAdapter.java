package com.example.mygames.Adapters;

import android.content.Intent;
import android.net.Uri;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygames.Models.DataModel;
import com.example.mygames.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private ArrayList<DataModel> dataSet;
    public CustomAdapter(ArrayList<DataModel> dataSet){
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gamecard_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView textViewTitle = holder.textViewTitle;
        TextView textViewPublisher = holder.textViewPublisher;
        TextView textViewDescription = holder.textViewDescription;
        //TextView textViewGameUrl= holder.textViewGameUrl;
        TextView textViewGenre = holder.textViewGenre;
        TextView textViewPlatform = holder.textViewPlatform;
        TextView textViewDeveloper = holder.textViewDeveloper;
        TextView textViewReleaseDate = holder.textViewReleaseDate;

        String thumbnailUrl = dataSet.get(position).getThumbnail();
        if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
            Picasso.get().load(thumbnailUrl).into(holder.imageViewThumbnail1);
        }

        textViewTitle.setText(dataSet.get(position).getTitle());
        textViewPublisher.setText(dataSet.get(position).getPublisher());
        textViewDescription.setText(dataSet.get(position).getDescription());
        //textViewGameUrl.setText(dataSet.get(position).getGameUrl());
        textViewGenre.setText(dataSet.get(position).getGenre());
        textViewPlatform.setText(dataSet.get(position).getPlatform());
        textViewDeveloper.setText(dataSet.get(position).getDeveloper());
        textViewReleaseDate.setText(dataSet.get(position).getReleaseDate());



        // Create a SpannableString for the textViewGameUrl
        String fullText = "More info click here: " + dataSet.get(position).getGameUrl();
        SpannableString spannableString = new SpannableString("More info click here");

        // Define a clickable span for the "More info click here" part
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                String url = dataSet.get(position).getGameUrl();
                if (url != null && !url.isEmpty()) {
                    // Open the URL using an Intent
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    view.getContext().startActivity(intent);
                }
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false); // Remove underline from the clickable text
            }
        };


        spannableString.setSpan(clickableSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the modified spannableString to textViewGameUrl
        holder.textViewGameUrl.setText(spannableString);
        holder.textViewGameUrl.setMovementMethod(LinkMovementMethod.getInstance());
        holder.textViewGameUrl.setHighlightColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.link_highlight_color));



    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }




    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewPublisher;
        TextView textViewDescription;
        TextView textViewGameUrl;
        TextView textViewGenre;
        TextView textViewPlatform;
        TextView textViewDeveloper;
        //TextView imageViewThumbnail;
        ImageView imageViewThumbnail1;
        TextView textViewReleaseDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewPublisher = itemView.findViewById(R.id.textViewPublisher);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewGameUrl = itemView.findViewById(R.id.textViewGameUrl);
            textViewGenre = itemView.findViewById(R.id.textViewGenre);
            textViewPlatform = itemView.findViewById(R.id.textViewPlatform);
            textViewDeveloper = itemView.findViewById(R.id.textViewDeveloper);
            //imageViewThumbnail = itemView.findViewById(R.id.imageViewThumbnail);
            imageViewThumbnail1 = itemView.findViewById(R.id.imageViewThumbnail1);
            textViewReleaseDate = itemView.findViewById(R.id.textViewReleaseDate);
        }
    }


    public void updateData(ArrayList<DataModel> newData) {
        dataSet.clear();
        dataSet.addAll(newData);
        notifyDataSetChanged();
    }


}
