package com.example.recyclerview_try;

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

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<String> data;
    private int layoutId;

    public ItemAdapter(List<String> data) {
        this.data = data;
    }

    public void setLayout(int layoutId){
        this.layoutId = layoutId;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       Context context = parent.getContext();
       LayoutInflater inflater =LayoutInflater.from(context);

       View myView = inflater.inflate(this.layoutId,parent,false);

       ViewHolder viewHolder = new ViewHolder(myView);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text = data.get(position);

        TextView textView = holder.itemText;
        textView.setText(text);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView itemImage;
        TextView itemText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.item_name);
            itemImage = itemView.findViewById(R.id.item_image);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();// gets item position
            if (position != RecyclerView.NO_POSITION) {// Check if an item was deleted, but the user clicked it before the UI removed it
                Log.e("dentro","dentro");
                String item = data.get(position);
                Intent intent = new Intent(view.getContext(),SecondActivity.class);
                intent.putExtra("item-value",item);
                view.getContext().startActivity(intent);
            }
        }
    }
}
