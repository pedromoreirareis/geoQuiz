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

import com.pedromoreirareisgmail.geoquiz.Commons.Const;
import com.pedromoreirareisgmail.geoquiz.R;
import com.pedromoreirareisgmail.geoquiz.adapters.CategoryAdapter;
import com.pedromoreirareisgmail.geoquiz.models.CategoryImage;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private Context mContext;
    private RecyclerView mRv;

    public CategoryFragment() {
    }

    public static CategoryFragment newInstance() {

        return new CategoryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getContext();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        mRv = view.findViewById(R.id.rv_frag_category);
        mRv.setHasFixedSize(true);
        mRv.setLayoutManager(new LinearLayoutManager(mContext));

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        CategoryAdapter adapter = new CategoryAdapter(mContext, getCategorys());

        mRv.setAdapter(adapter);
    }

    private List<CategoryImage> getCategorys() {

        List<String> category = new ArrayList<>();
        category.add(Const.CATEGORY_ESTADOS);
        category.add(Const.CATEGORY_AMERICA);
        category.add(Const.CATEGORY_EUROPA);
        category.add(Const.CATEGORY_ASIA);
        category.add(Const.CATEGORY_AFRICA);
        category.add(Const.CATEGORY_OCEANIA);
        category.add(Const.CATEGORY_TERRITORIOS);

        List<String> image = new ArrayList<>();
        image.add(Const.CATEGORY_IMG_ESTADOS);
        image.add(Const.CATEGORY_IMG_AMERICA);
        image.add(Const.CATEGORY_IMG_EUROPA);
        image.add(Const.CATEGORY_IMG_ASIA);
        image.add(Const.CATEGORY_IMG_AFRICA);
        image.add(Const.CATEGORY_IMG_OCEANIA);
        image.add(Const.CATEGORY_IMG_TERRITORIOS);

        List<CategoryImage> listCategoryImage = new ArrayList<>();

        for(int i =0; i < category.size();i++){

            listCategoryImage.add(new CategoryImage(
                    category.get(i),
                    image.get(i)
            ));
        }

        //  Faz pesquisa das categorias
        return listCategoryImage;
    }
}
