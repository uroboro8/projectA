package com.example.pwbaseprova;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pwbaseprova.piatti.Piatto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapterPiatti extends RecyclerView.Adapter<CustomAdapterPiatti.ViewHolder>{

    private List<Piatto> piatti;
    private ItemClickListener mClickListener;



    // data is passed into the constructor
    public CustomAdapterPiatti(List<Piatto> piattiList) {
        piatti = piattiList;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_row_piatti,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Piatto piatto = piatti.get(position);

        TextView textView = holder.itemText;
        textView.setText(piatto.getName());

        ImageView imageView = holder.itemImage;

        if(piatto.getImage() != null && piatto.getImage().trim().length() != 0)
            Picasso.get().load(piatto.getImage()).into(imageView);
        else
            Picasso.get().load("https://uroboro8.github.io/ImageRepository/images/default-placeholder.png").into(imageView);
    }

    @Override
    public int getItemCount() {
        return piatti.size();
    }


    // stores and recycles views as they are scrolled off screen
    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView itemImage;
        TextView itemText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.item_name_piatti);
            itemImage = itemView.findViewById(R.id.item_image_piatti);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Piatto getItem(int id) {
        return piatti.get(id);
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
