package com.example.pwbaseprova;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pwbaseprova.itinerari.Itinerario;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapterItinerari extends RecyclerView.Adapter<CustomAdapterItinerari.ViewHolder>{

    private List<Itinerario> itinerari;
    private CustomAdapterItinerari.ItemClickListener mClickListener;



    // data is passed into the constructor
    public CustomAdapterItinerari(List<Itinerario> itinerariList) {
        itinerari = itinerariList;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public CustomAdapterItinerari.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_row_itinerari,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    // binds the data in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Itinerario itinerario = itinerari.get(position);

        TextView textView = holder.itemText;
        textView.setText(itinerario.getName());

        ImageView imageView = holder.itemImage;
        //Log.e("image",itinerario.getCover());
        if (itinerario.getCover() != null) {
            if (itinerario.getCover().trim().length() == 0)
                Picasso.get().load("https://www.mrw.it/img/cope/0iwkf4_1609360688.jpg").into(imageView);
            else
                Picasso.get().load(itinerario.getCover()).into(imageView);
        }
        TextView textView1 = holder.itemText1;
        textView1.setText(itinerario.getDuration()+"h - "+itinerario.getPrice()+"$");
    }

    @Override
    public int getItemCount() {
        return itinerari.size();
    }


    // stores and recycles views as they are scrolled off screen
    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView itemImage;
        TextView itemText;
        TextView itemText1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.item_name_itinerari);
            itemImage = itemView.findViewById(R.id.item_image_itinerari);
            itemText1 = itemView.findViewById(R.id.textViewDurationAndCost);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Itinerario getItem(int id) {
        return itinerari.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(CustomAdapterItinerari.ItemClickListener itemClickListener) {
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
