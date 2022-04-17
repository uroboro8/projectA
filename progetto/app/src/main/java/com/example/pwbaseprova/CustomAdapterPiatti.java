package com.example.pwbaseprova;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pwbaseprova.piatti.Piatto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapterPiatti extends RecyclerView.Adapter<CustomAdapterPiatti.ViewHolder>{

    private List<Piatto> piatti;
    private ItemClickListener mClickListener;


    //Passiamo i dati tramite il costruttore
    public CustomAdapterPiatti(List<Piatto> piattiList) {
        piatti = piattiList;
    }

    //Inflate del layout dall'xml
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_row_piatti,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //Inserisco i dati per ogni riga
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


    //Salve e ricicla le view
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

    //Ottieni i dati dell'item cliccato
    Piatto getItem(int id) {
        return piatti.get(id);
    }

    //Abilita la possibilità di ascoltare i click
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    //Filtro
    void updateList(List<Piatto> filteredList){
        this.piatti = filteredList;
        notifyDataSetChanged();
    }

    //L'activity padre implementa questo metodo per rispondere ai click
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
