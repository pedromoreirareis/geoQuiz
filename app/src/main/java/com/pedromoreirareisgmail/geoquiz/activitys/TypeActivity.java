package com.pedromoreirareisgmail.geoquiz.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pedromoreirareisgmail.geoquiz.Commons.Common;
import com.pedromoreirareisgmail.geoquiz.R;

public class TypeActivity extends AppCompatActivity implements View.OnClickListener {


    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        mContext = TypeActivity.this;

        findViewById(R.id.but_type_capitais).setOnClickListener(this);
        findViewById(R.id.but_type_flags).setOnClickListener(this);
        findViewById(R.id.but_type_maps).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent toPlay = new Intent(mContext,PlayActivity.class);

        switch (v.getId()){

            case R.id.but_type_capitais:
                Common.gameType = Common.TYPE_CAPITAIS;
                startActivity(toPlay);
                finish();
                break;

            case R.id.but_type_flags:
                Common.gameType = Common.TYPE_FLAGS;
                startActivity(toPlay);
                finish();
                break;

            case R.id.but_type_maps:
                Common.gameType = Common.TYPE_MAPS;
                startActivity(toPlay);
                finish();
                break;
        }
    }
}
