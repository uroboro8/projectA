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



    //Passiamo i dati tramite il costruttore
    public CustomAdapterItinerari(List<Itinerario> itinerariList) {
        itinerari = itinerariList;
    }

    //Inflate del layout dall'xml
    @NonNull
    @Override
    public CustomAdapterItinerari.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_row_itinerari,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    //Inserisco i dati per ogni riga
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Itinerario itinerario = itinerari.get(position);

        TextView textView = holder.itemText;
        textView.setText(itinerario.getName());

        ImageView imageView = holder.itemImage;

        if (itinerario.getCover() != null && itinerario.getCover().trim().length() != 0)
            Picasso.get().load(itinerario.getCover()).into(imageView);

        TextView duration = holder.itemText1;
        String durationString = String.valueOf(itinerario.getDuration());
        String[] durationSplit = durationString.split("\\.");
        if (durationSplit[1].equals("0"))
            duration.setText(durationSplit[0] + "h - "+itinerario.getPrice()+"€");
        else
            duration.setText((durationString) + "h - "+itinerario.getPrice()+"€");

    }

    @Override
    public int getItemCount() {
        return itinerari.size();
    }


    //Salve e ricicla le view
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

    //Ottieni i dati dell'item cliccato
    Itinerario getItem(int id) {
        return itinerari.get(id);
    }

    //Abilita la possibilità di ascoltare i click
    void setClickListener(CustomAdapterItinerari.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    //Da fare per il filtro
    void updateList(List<Itinerario> filteredList){
        this.itinerari = filteredList;
       notifyDataSetChanged();
    }

    //L'activity padre implementa questo metodo per rispondere ai click
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
