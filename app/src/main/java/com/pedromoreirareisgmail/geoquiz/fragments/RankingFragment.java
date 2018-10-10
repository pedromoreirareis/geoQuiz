package com.pedromoreirareisgmail.geoquiz.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedromoreirareisgmail.geoquiz.R;
import com.pedromoreirareisgmail.geoquiz.adapters.RankingAdapter;
import com.pedromoreirareisgmail.geoquiz.db.Crud;
import com.pedromoreirareisgmail.geoquiz.models.Category;

import java.util.List;

public class RankingFragment extends Fragment {


    private Context mContext;
    private RecyclerView mRv;


    public static RankingFragment newInstance(){

        return new RankingFragment();
    }

    public RankingFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getContext();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        mRv = view.findViewById(R.id.rv_frag_ranking);
        mRv.setHasFixedSize(true);
        mRv.setLayoutManager( new LinearLayoutManager(mContext));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        RankingAdapter adapter = new RankingAdapter(mContext,searchCategorys());

        mRv.setAdapter(adapter);
    }

    private List<Category> searchCategorys() {

        //  Faz pesquisa das categorias
        return Crud.getCategorys(mContext);
    }
}
