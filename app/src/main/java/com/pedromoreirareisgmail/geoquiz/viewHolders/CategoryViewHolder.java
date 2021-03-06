package com.pedromoreirareisgmail.geoquiz.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedromoreirareisgmail.geoquiz.R;
import com.pedromoreirareisgmail.geoquiz.interfaces.ItemClickListener;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final ImageView ivCategoryImage;
    public final TextView tvCategoryName;
    private ItemClickListener itemClickListener;


    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        ivCategoryImage = itemView.findViewById(R.id.iv_item_category_image);
        tvCategoryName = itemView.findViewById(R.id.tv_item_category_name);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {

        this.itemClickListener = itemClickListener;
    }
}
