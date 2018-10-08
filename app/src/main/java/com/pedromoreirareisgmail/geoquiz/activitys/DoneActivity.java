package com.pedromoreirareisgmail.geoquiz.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pedromoreirareisgmail.geoquiz.Commons.Common;
import com.pedromoreirareisgmail.geoquiz.Commons.Const;
import com.pedromoreirareisgmail.geoquiz.R;
import com.pedromoreirareisgmail.geoquiz.db.Crud;
import com.pedromoreirareisgmail.geoquiz.models.Ranking;

import java.util.List;

public class DoneActivity extends AppCompatActivity implements Button.OnClickListener{


    private Context mContext;

    private TextView mTvScore;
    private TextView mTvQuantity;
    private Button mButTryAgain;
    private ProgressBar mPbProgress;

    private String mScoreOld = "0";
    private List<Ranking> mListRanking;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        mContext = DoneActivity.this;

        initViews();
        initExtras();
    }

    private void initViews() {

        mTvScore = findViewById(R.id.tv_done_score);
        mTvQuantity = findViewById(R.id.tv_done_quantity);
        mButTryAgain = findViewById(R.id.but_done_try_again);
        mPbProgress = findViewById(R.id.pb_done_progress);

        mButTryAgain.setOnClickListener(this);
    }

    private void initExtras() {

        Bundle extras = getIntent().getExtras();

        if(extras != null){

            int score = extras.getInt(Const.INTENT_SCORE);
            int quantity = extras.getInt(Const.INTENT_QUANTITY);
            int corrects = extras.getInt(Const.INTENT_CORRECTS);
            
            showDisplay(score,corrects,quantity);

        }
    }

    private void showDisplay(int score, int corrects, int quantity) {

        //  Mostra a pontuação e a quantidade de resposta certa
        mTvScore.setText(String.format(getString(R.string.showScore),score));
        mTvQuantity.setText(String.format(getString(R.string.showTotal),corrects,quantity));

        //  Mostra o progresso corretas / total
        mPbProgress.setMax(quantity);
        mPbProgress.setProgress(score);

        //  Salva ranking
        Crud.insertScore(score,Common.gameCategory,mContext);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.but_done_try_again:

                Intent toMain = new Intent(mContext,MainActivity.class);
                startActivity(toMain);
                finish();
                break;
        }

    }
}
