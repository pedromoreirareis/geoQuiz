package com.pedromoreirareisgmail.geoquiz.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pedromoreirareisgmail.geoquiz.R;

public class DetailViewHolder  extends RecyclerView.ViewHolder {

    public final TextView mTvType;
    public final TextView mTvScore;

    public DetailViewHolder(@NonNull View itemView) {
        super(itemView);

        mTvType = itemView.findViewById(R.id.tv_item_detail_type);
        mTvScore = itemView.findViewById(R.id.tv_item_detail_score);
    }
}
