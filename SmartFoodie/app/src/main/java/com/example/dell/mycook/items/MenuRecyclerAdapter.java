package com.example.dell.mycook.items;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.mycook.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by dell on 3/5/2017.
 */

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.ViewHolder> {
    ArrayList<MymenuItem> items = new ArrayList<>();
    public MenuRecyclerAdapter(MymenuItem[] items){
        this.items.addAll(Arrays.asList(items));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item,parent,false));
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MymenuItem item = items.get(position);
        holder.menuItemTitle.setText(item.getItems());
        holder.customFaView.setText(item.getIcon());
        holder.item.setOnClickListener(item.getOnclick());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder{
        View item;
        TextView menuItemTitle;
        CustomFaView customFaView;

        public ViewHolder(View itemView) {
            super(itemView);
            item = itemView;
            menuItemTitle = (TextView) itemView.findViewById(R.id.menuItemTitle);
            customFaView = (CustomFaView) itemView.findViewById(R.id.icon);
        }
    }
}