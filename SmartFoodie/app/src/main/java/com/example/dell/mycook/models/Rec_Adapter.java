package com.example.dell.mycook.models;

/**
 * Created by dell on 3/6/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.mycook.R;

import java.util.List;

public class Rec_Adapter extends RecyclerView.Adapter<Rec_Adapter.ViewHolder> {

    private List<Item> itemList;
    private String[] imageUrls;
    //Provide a reference to the views for each data item
    //Complex data items may need more than one view per item, and
    //you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        //each data item is just a string in this case
        public TextView Name;
        public ImageView img;

        public ViewHolder(View v) {
            super(v);
            img = (ImageView) v.findViewById(R.id.iv_album_cover);
            Name=(TextView)v.findViewById(R.id.tv_year);
        }
    }

    //Provide a suitable constructor
    public Rec_Adapter(List<Item> itemList, String[] imageUrls){
        this.itemList = itemList;
        this.imageUrls = imageUrls;
    }

    //Create new views (invoked by the layout manager)
    @Override
    public Rec_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Creating a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item,parent,false);
        //set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //Replace the contents of a view (invoked by the layout manager
    @Override
    public void onBindViewHolder(Rec_Adapter.ViewHolder holder, int position) {
        Item item = itemList.get(position);

        holder.Name.setText(item.getName());
        Glide.with(holder.img.getContext())
                .load(imageUrls[position])
                .centerCrop().into(holder.img);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
