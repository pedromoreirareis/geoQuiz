package com.pedromoreirareisgmail.geoquiz.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pedromoreirareisgmail.geoquiz.R;
import com.pedromoreirareisgmail.geoquiz.interfaces.ItemClickListener;

public class RankingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tvCategory;
    public TextView tvScore;
    private ItemClickListener itemClickListener;

    public RankingViewHolder(@NonNull View itemView) {
        super(itemView);

        tvCategory = itemView.findViewById(R.id.raking_category);
        tvScore = itemView.findViewById(R.id.raking_score);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {

        this.itemClickListener = itemClickListener;
    }
}
