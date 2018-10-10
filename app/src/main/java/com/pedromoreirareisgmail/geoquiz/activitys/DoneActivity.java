package com.pedromoreirareisgmail.geoquiz.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pedromoreirareisgmail.geoquiz.Commons.Common;
import com.pedromoreirareisgmail.geoquiz.Commons.Const;
import com.pedromoreirareisgmail.geoquiz.R;
import com.pedromoreirareisgmail.geoquiz.db.Crud;

public class DoneActivity extends AppCompatActivity implements Button.OnClickListener {


    private Context mContext;

    private TextView mTvScore;
    private TextView mTvQuantity;
    private TextView mTvInfoRecord;
    private TextView mTvCategory;
    private TextView mTvType;
    private ProgressBar mPbProgress;

    private int mOldScore = 0;
    private int mScore = 0;
    private int mQuantity = 0;
    private int mCorrects = 0;

    private boolean isScoreExists = false;

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
        mTvInfoRecord = findViewById(R.id.tv_done_info_record);
        mTvCategory = findViewById(R.id.tv_done_category);
        mTvType = findViewById(R.id.tv_done_type);
        mPbProgress = findViewById(R.id.pb_done_progress);

        findViewById(R.id.but_done_try_again).setOnClickListener(this);
    }

    private void initExtras() {

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            mScore = extras.getInt(Const.INTENT_SCORE);
            mQuantity = extras.getInt(Const.INTENT_QUANTITY);
            mCorrects = extras.getInt(Const.INTENT_CORRECTS);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        String category = Common.gameCategory;

        int type = Common.gameType;
        String typeTtitle = "";
        String typeString = "";

        switch (type) {

            case Common.TYPE_CAPITAIS:
                typeTtitle = getString(R.string.text_type_capitais);
                typeString = Common.TYPE_STRING_CAPITAL;
                break;

            case Common.TYPE_FLAGS:
                typeTtitle = getString(R.string.text_type_flags);
                typeString = Common.TYPE_STRING_FLAG;
                break;

            case Common.TYPE_MAPS:
                typeTtitle = getString(R.string.text_type_maps);
                typeString = Common.TYPE_STRING_MAP;
                break;

        }

        mTvType.setText(typeTtitle);
        mTvCategory.setText(category);

        mOldScore = Crud.getScoreByCategoryAndType(Common.gameCategory,typeString,mContext);

        if(mOldScore > 0){

            isScoreExists = true;
        }

        showDisplay(mScore, mCorrects, mQuantity,category,typeString);
    }


    private void showDisplay(int score, int corrects, int quantity,String category, String type) {

        //  Mostra a pontuação e a quantidade de resposta certa
        mTvScore.setText(String.format(getString(R.string.showScore), score));
        mTvQuantity.setText(String.format(getString(R.string.showTotalDone), corrects, quantity));

        //  Mostra o progresso corretas / total
        mPbProgress.setMax(quantity*10);
        mPbProgress.setProgress(score);


        if(score >= mOldScore ){

            mTvInfoRecord.setText(String.format(getString(R.string.showRecord),score));
            mTvInfoRecord.setBackground(getDrawable(R.drawable.bg_info_record));

        }else{

            mTvInfoRecord.setText(String.format(getString(R.string.showNoRecord),mOldScore));
            mTvInfoRecord.setBackground(getDrawable(R.drawable.bg_info_no_record));
        }


        if(isScoreExists){ //    ATUALIZAR BD

            // se -1 erro
            int id = Crud.getIdByCategoryAndType(category,type,mContext);

            if(id == -1){

                Toast.makeText(mContext, "Erro ao atualizar Pontuação", Toast.LENGTH_SHORT).show();

            }else{

                Crud.updateScore(id,score,category,type,mContext);
            }


        }else { // SALVAR BD



            Crud.insertScore(score, Common.gameCategory, Common.gameTypeString, mContext);
        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.but_done_try_again:
                finish();
                break;
        }

    }
}
