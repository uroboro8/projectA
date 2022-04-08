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
import androidx.recyclerview.widget.RecyclerView;

import com.example.pwbaseprova.piatti.Piatto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapterPiatti extends RecyclerView.Adapter<CustomAdapterPiatti.ViewHolder>{

    private List<Piatto> piatti;

    public CustomAdapterPiatti(List<Piatto> piatti) {
        this.piatti = piatti;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);

        View myView = inflater.inflate(R.layout.custom_row_piatti,parent,false);

        ViewHolder viewHolder = new ViewHolder(myView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Piatto piatto = piatti.get(position);

        TextView textView = holder.itemText;
        textView.setText(piatto.getName());

        ImageView imageView = holder.itemImage;
        Picasso.get().load(piatto.getImage()).into(imageView);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            int position = getAdapterPosition();// gets item position
            if (position != RecyclerView.NO_POSITION) {// Check if an item was deleted, but the user clicked it before the UI removed it
                Log.e("dentro","dentro");
                Piatto piatto = piatti.get(position);
                Intent intent = new Intent(view.getContext(),DettaglioPiattoActivity.class);
                intent.putExtra("item-value",piatto);
                view.getContext().startActivity(intent);
            }
        }
    }
}
