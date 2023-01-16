package com.example.fooddelivery.Model.Adapter;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery.Model.Ui.ShowDetailsActivity;
import com.example.fooddelivery.R;
import com.example.fooddelivery.Service.Model.CategoryDomains;
import com.example.fooddelivery.Service.Model.FoodDomains;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    ArrayList<FoodDomains> foodDomains;

    public PopularAdapter(ArrayList<FoodDomains> foodDomains) {
        this.foodDomains = foodDomains;
    }

    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vieweholder_popular, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularAdapter.ViewHolder holder, int position) {
        holder.popularTittle.setText(foodDomains.get(position).getTittle());
        holder.popularFee.setText(String.valueOf(foodDomains.get(position).getFee()));


        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.popularPic);

        holder.popularAdd.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
            intent.putExtra("OBJECT",foodDomains.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView popularTittle, popularFee, popularAdd;
        ImageView popularPic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popularTittle = itemView.findViewById(R.id.pTittleID);
            popularPic = itemView.findViewById(R.id.pImageViewID);
            popularFee = itemView.findViewById(R.id.pFeeID);
            popularAdd = itemView.findViewById(R.id.pAddID);
        }
    }
}

