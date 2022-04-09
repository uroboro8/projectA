package com.example.pwbaseprova;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pwbaseprova.gallery.ImageCustom;
import com.example.pwbaseprova.piatti.Piatto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapterGalleryImage extends RecyclerView.Adapter<CustomAdapterGalleryImage.ViewHolder>{

    private List<ImageCustom> gallery;
    private ItemClickListener mClickListener;



    // data is passed into the constructor
    public CustomAdapterGalleryImage(List<ImageCustom> piattiList) {
        gallery = piattiList;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_row_gallery,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageCustom image = gallery.get(position);

        ImageView imageView = holder.itemImage;

        if(image.getImage().trim().length() == 0)
            Picasso.get().load("https://www.mrw.it/img/cope/0iwkf4_1609360688.jpg").into(imageView);
        else
            Picasso.get().load(image.getImage()).into(imageView);
    }

    @Override
    public int getItemCount() {
        return gallery.size();
    }




    // stores and recycles views as they are scrolled off screen
    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView itemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.gallery_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    ImageCustom getItem(int id) {
        return gallery.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    void updateList(){
        notifyDataSetChanged();
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}