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
import com.pedromoreirareisgmail.geoquiz.activitys.TypeActivity;
import com.pedromoreirareisgmail.geoquiz.interfaces.ItemClickListener;
import com.pedromoreirareisgmail.geoquiz.models.CategoryImage;
import com.pedromoreirareisgmail.geoquiz.viewHolders.CategoryViewHolder;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private final Context mContext;
    private final List<CategoryImage> mList;

    public CategoryAdapter(Context context, List<CategoryImage> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        //  Cria layout category
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_category, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        //  Recebe o nome da categoria
        String category = mList.get(position).getCategory();

        //  Coloca o nome da categoria no TextView
        holder.tvCategoryName.setText(category);

        //  Atribui imagem a categoria
        int imageId = mContext.getResources().getIdentifier(
                mList.get(position).getImage(),
                "drawable",
                mContext.getPackageName()
        );

        holder.ivCategoryImage.setImageDrawable(mContext.getDrawable(imageId));


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                //  Categoria que o usuário escolheu para jogar
                Common.gameCategory = mList.get(position).getCategory();

                //  Vai para activity Type para escolher tipo de jogo
                Intent toType = new Intent(mContext, TypeActivity.class);
                mContext.startActivity(toType);
            }
        });
    }

    @Override
    public int getItemCount() {

        //  mList é diferente de null
        //  Sim - Retorna a quantidade de intens da lista
        //  Não - Retorna 0
        return mList != null ? mList.size() : 0;
    }
}
