package com.pedromoreirareisgmail.geoquiz.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedromoreirareisgmail.geoquiz.Commons.Common;
import com.pedromoreirareisgmail.geoquiz.R;
import com.pedromoreirareisgmail.geoquiz.db.Crud;
import com.pedromoreirareisgmail.geoquiz.viewHolders.DetailViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailViewHolder> {

    private final Context mContext;
    private final List<String> mListType;
    private final List<String> mListTypePt;

    public DetailAdapter(Context context, List<String> list) {

        mContext = context;
        mListType = list;

        mListTypePt = new ArrayList<>();
        mListTypePt.add(context.getString(R.string.text_type_capitais));
        mListTypePt.add(context.getString(R.string.text_type_flags));
        mListTypePt.add(context.getString(R.string.text_type_maps));
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_detail, viewGroup, false);

        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {

        holder.mTvType.setText(mListTypePt.get(position));

        int scoreByType = getScoreByCategoryAndType(Common.detailCategory, mListType.get(position));

        holder.mTvScore.setText(String.valueOf(scoreByType));
    }

    private int getScoreByCategoryAndType(String category, String type) {

        return Crud.getScoreByCategoryAndType(category, type, mContext);
    }

    @Override
    public int getItemCount() {
        return mListType != null ? mListType.size() : 0;
    }

}
