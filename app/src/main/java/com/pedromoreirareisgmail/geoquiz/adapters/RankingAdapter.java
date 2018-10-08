package com.pedromoreirareisgmail.geoquiz.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedromoreirareisgmail.geoquiz.R;
import com.pedromoreirareisgmail.geoquiz.db.Crud;
import com.pedromoreirareisgmail.geoquiz.interfaces.ItemClickListener;
import com.pedromoreirareisgmail.geoquiz.models.Category;
import com.pedromoreirareisgmail.geoquiz.models.Ranking;
import com.pedromoreirareisgmail.geoquiz.viewHolders.RankingViewHolder;

import java.util.List;
import java.util.Objects;

public class RankingAdapter extends RecyclerView.Adapter<RankingViewHolder> {

    private Context mContext;
    private List<Category> mListCategory;

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

        int totalScoreCategory = getTotalScoreCategory(mListCategory.get(position).getName());

        holder.tvScore.setText(String.valueOf(totalScoreCategory));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                // TODO: vai abrir detail da category

            }
        });
    }

    private int getTotalScoreCategory(String category) {

        int totalScore = 0;

        List<Ranking> listRanking = Crud.getScore(category,mContext);

        for (Ranking ranking : Objects.requireNonNull(listRanking)) {

            if (ranking.getCategory().equals(category)) {

                totalScore += totalScore + ranking.getScore();
            }
        }

        return totalScore;
    }

    @Override
    public int getItemCount() {
        return mListCategory != null ? mListCategory.size() : 0;
    }
}
