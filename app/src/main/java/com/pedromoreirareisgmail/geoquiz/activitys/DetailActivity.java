package com.pedromoreirareisgmail.geoquiz.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.pedromoreirareisgmail.geoquiz.Commons.Common;
import com.pedromoreirareisgmail.geoquiz.Commons.Const;
import com.pedromoreirareisgmail.geoquiz.R;
import com.pedromoreirareisgmail.geoquiz.adapters.DetailAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private Context mContext;
    private RecyclerView mRv;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mContext = DetailActivity.this;

        initViews();
    }

    private void initViews() {

        mRv = findViewById(R.id.rv_detail);
        mRv.setHasFixedSize(true);
        mRv.setLayoutManager(new LinearLayoutManager(mContext));

        mTvTitle = findViewById(R.id.tv_detail_title);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mTvTitle.setText(Common.detailCategory);

        DetailAdapter adapter = new DetailAdapter(mContext, getListType());

        mRv.setAdapter(adapter);
    }

    private List<String> getListType() {

        List<String> list = new ArrayList<>();
        list.add(Const.RANKING_TYPE_CAPITAL);
        list.add(Const.RANKING_TYPE_FLAG);
        list.add(Const.RANKING_TYPE_MAA);

        return list;
    }
}
