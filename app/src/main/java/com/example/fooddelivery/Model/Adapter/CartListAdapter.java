package com.example.fooddelivery.Model.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery.R;
import com.example.fooddelivery.Service.Database.ManagementCart;
import com.example.fooddelivery.Service.Interface.ChangeNumberItemListener;
import com.example.fooddelivery.Service.Model.FoodDomains;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.viewHolder> {

    private ArrayList<FoodDomains> foodDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemListener changeNumberItemListener;

    public CartListAdapter(ArrayList<FoodDomains> foodDomains, Context context, ChangeNumberItemListener changeNumberItemListener) {
        this.foodDomains = foodDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }

    @Override
    public CartListAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cartlist, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartListAdapter.viewHolder holder, int position) {
        holder.tittle.setText(foodDomains.get(position).getTittle());
        holder.feeEachItem.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((foodDomains.get(position).getNumberInCart() * foodDomains.get(position).getFee()) * 100) / 100));
        holder.num.setText(String.valueOf(foodDomains.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);


        holder.plusItem.setOnClickListener(v -> {
            managementCart.plusNumberFood(foodDomains, position, new ChangeNumberItemListener() {
                @Override
                public void change() {
                    notifyDataSetChanged();
                    changeNumberItemListener.change();
                }
            });
        });

        holder.minusItem.setOnClickListener(v -> {
            managementCart.minusNumberFood(foodDomains, position, new ChangeNumberItemListener() {
                @Override
                public void change() {
                    notifyDataSetChanged();
                    changeNumberItemListener.change();
                }
            });
        });

    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tittle, feeEachItem, totalEachItem, num;
        ImageView pic, plusItem, minusItem;

        public viewHolder(View itemView) {
            super(itemView);
            tittle = itemView.findViewById(R.id.cTittleId);
            feeEachItem = itemView.findViewById(R.id.cFeeEachItem);
            totalEachItem = itemView.findViewById(R.id.cTotalEachItem);
            num = itemView.findViewById(R.id.cNumberId);
            pic = itemView.findViewById(R.id.cImageViewId);
            plusItem = itemView.findViewById(R.id.cPlusId);
            minusItem = itemView.findViewById(R.id.cMinusId);
        }
    }
}
