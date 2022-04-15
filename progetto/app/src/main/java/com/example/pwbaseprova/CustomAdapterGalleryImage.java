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
    private int layoutId;


    //Passiamo i dati tramite il costruttore
    public CustomAdapterGalleryImage(List<ImageCustom> piattiList) {
        gallery = piattiList;
    }

    public  void setLayout(int layout){
        this.layoutId = layout;
    }
    //Inflate del layout dall'xml
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(this.layoutId,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //Inserisco i dati per ogni riga
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageCustom image = gallery.get(position);

        ImageView imageView = holder.itemImage;

        if(image.getImage() != null && image.getImage().trim().length() != 0)
            Picasso.get().load(image.getImage()).into(imageView);

    }

    @Override
    public int getItemCount() {
        return gallery.size();
    }




    //Salve e ricicla le view
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

    //Ottieni i dati dell'item cliccato
    ImageCustom getItem(int id) {
        return gallery.get(id);
    }

    //Abilita la possibilità di ascoltare i click
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    //Da fare per il filtro
    void updateList(){
        //notifyDataSetChanged();
    }

    //L'activity padre implementa questo metodo per rispondere ai click
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}