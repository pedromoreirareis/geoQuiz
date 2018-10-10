package com.pedromoreirareisgmail.geoquiz.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedromoreirareisgmail.geoquiz.Commons.Common;
import com.pedromoreirareisgmail.geoquiz.R;
import com.pedromoreirareisgmail.geoquiz.activitys.DetailActivity;
import com.pedromoreirareisgmail.geoquiz.db.Crud;
import com.pedromoreirareisgmail.geoquiz.interfaces.ItemClickListener;
import com.pedromoreirareisgmail.geoquiz.models.Category;
import com.pedromoreirareisgmail.geoquiz.viewHolders.RankingViewHolder;

import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankingViewHolder> {

    private final Context mContext;
    private final List<Category> mListCategory;

    public RankingAdapter(Context context, List<Category> listCategory) {
        mContext = context;
        mListCategory = listCategory;
    }

    @NonNull
    @Override
    public RankingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ranking, viewGroup, false);

        return new RankingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingViewHolder holder, int position) {

        holder.tvCategory.setText(mListCategory.get(position).getName());

        int sumScore = getSumScoreCategory(mListCategory.get(position).getName());

        holder.tvScore.setText(String.valueOf(sumScore));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                Common.detailCategory = mListCategory.get(position).getName();

                Intent toDetail = new Intent(mContext, DetailActivity.class);
                mContext.startActivity(toDetail);
            }
        });
    }

    private int getSumScoreCategory(String category) {

        return Crud.getSumScoreByCategory(category, mContext);
    }

    @Override
    public int getItemCount() {
        return mListCategory != null ? mListCategory.size() : 0;
    }
}
