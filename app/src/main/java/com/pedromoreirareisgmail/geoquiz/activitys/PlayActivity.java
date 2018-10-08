package com.pedromoreirareisgmail.geoquiz.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pedromoreirareisgmail.geoquiz.Commons.Common;
import com.pedromoreirareisgmail.geoquiz.Commons.Const;
import com.pedromoreirareisgmail.geoquiz.Commons.Wait;
import com.pedromoreirareisgmail.geoquiz.Game.Alternatives;
import com.pedromoreirareisgmail.geoquiz.Game.Controls;
import com.pedromoreirareisgmail.geoquiz.R;
import com.pedromoreirareisgmail.geoquiz.db.Crud;
import com.pedromoreirareisgmail.geoquiz.models.Answer;
import com.pedromoreirareisgmail.geoquiz.models.AnswerBut;
import com.pedromoreirareisgmail.geoquiz.models.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayActivity extends AppCompatActivity implements Button.OnClickListener {

    private Context mContext;

    private List<Question> mListQuestions;
    private List<Answer> mListAnswers;

    private CountDownTimer mCountDownTimer;
    private int mQuantity = 0;
    private int mTime = 0;
    private int mIndex = 0;
    private int mCurrent = 0;
    private int mScore = 0;
    private int mCorrects = 0;

    private long mTimeout = 0;
    private long mInterval = 0;
    private long mTimeAnswer = 0;

    private boolean isEndQuestions = false;

    private TextView mTvTitle;
    private TextView mTvQuestion;
    private TextView mTvCountDownTimer;
    private TextView mTvScore;
    private TextView mTvQuantity;
    private TextView mTvRegion;
    private ImageView mIvImagem;
    private ProgressBar mPbProgress;
    private Button mButA;
    private Button mButB;
    private Button mButC;
    private Button mButD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mContext = PlayActivity.this;

        initLists();
        initViews();
    }

    private void initLists() {

        mListQuestions = new ArrayList<>();
        mListAnswers = new ArrayList<>();

        //  Cria thread para busca
        mListQuestions = Crud.getQuestionsByCategory(Common.gameCategory, mContext);
        mListAnswers = Crud.getAnswersByCategory(Common.gameCategory, mContext);
    }

    private void initViews() {

        mTvTitle = findViewById(R.id.tv_play_title);
        mTvQuestion = findViewById(R.id.tv_play_question);
        mTvCountDownTimer = findViewById(R.id.tv_play_count_down_timer);
        mTvScore = findViewById(R.id.tv_play_score);
        mTvQuantity = findViewById(R.id.tv_play_quantity);
        mTvRegion = findViewById(R.id.tv_play_region);
        mIvImagem = findViewById(R.id.iv_play_image);
        mPbProgress = findViewById(R.id.pb_play_progress);
        mButA = findViewById(R.id.but_play_a);
        mButB = findViewById(R.id.but_play_b);
        mButC = findViewById(R.id.but_play_c);
        mButD = findViewById(R.id.but_play_d);

        mButA.setOnClickListener(this);
        mButB.setOnClickListener(this);
        mButC.setOnClickListener(this);
        mButD.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //  Altera aleatoriamente a ordem na lista
        Collections.shuffle(mListQuestions);
        Collections.shuffle(mListAnswers);


        switch (Common.gameType){

            case Common.TYPE_CAPITAIS:
                mTimeout = Common.TIMEOUT_CAPITAIS;
                mInterval = Common.INTERVAL_CAPITAIS;
                mTimeAnswer = Common.TIME_ANSWER_CAPITAIS;
                mPbProgress.setMax(Common.TIME_ANSWER_CAPITAIS);
                break;

            case Common.TYPE_FLAGS:
                mTimeout = Common.TIMEOUT_FLAGS;
                mInterval = Common.INTERVAL_FLAGS;
                mTimeAnswer = Common.TIME_ANSWER_FLAGS;
                mPbProgress.setMax(Common.TIME_ANSWER_FLAGS);
                break;

            case Common.TYPE_MAPS:
                mTimeout = Common.TIMEOUT_MAPS;
                mInterval = Common.INTERVAL_MAPS;
                mTimeAnswer = Common.TIME_ANSWER_MAPS;
                mPbProgress.setMax(Common.TIME_ANSWER_MAPS);
                break;
        }


        //  Quantidade de perguntas
        mQuantity = mListQuestions.size();

        mCountDownTimer = new CountDownTimer(mTimeout, mInterval) {
            @Override
            public void onTick(long millisUntilFinished) {

                //  Mostra o tempo restante no progressbar e no textview
                mTvCountDownTimer.setText(String.valueOf(mTimeAnswer - mTime));
                mPbProgress.setProgress(mTime);
                mTime++;
            }

            @Override
            public void onFinish() {

                //  Cancela o contador
                mCountDownTimer.cancel();

                //  Mostra a proxima pergunta
                showQuestion(++mIndex);
            }
        };

        //  Mostra a primeira pergunta
        showQuestion(mIndex);
        mTvScore.setText(String.format(getString(R.string.showScore), 0));
    }

    private void showQuestion(int currentIndex) {


        if (currentIndex < mQuantity) {

            //  Zera tempo e progressBar
            mTime = 0;
            mPbProgress.setProgress(mTime);

            //  Pergunta atual
            mCurrent++;

            //  Mostra qual pergunta esta respodendo em relação ao total mCurrent / mQuantity
            mTvQuantity.setText(String.format(getString(R.string.showTotal), mCurrent, mQuantity));

            //  Verifica o tipo de Quiz  0 - Capitais   /  1 - Flags  / 2 - Maps
            switch (Common.gameType) {


                case Common.TYPE_CAPITAIS:  //  Texto Capital

                    //  Exibe a pergunta de infromação
                    mTvTitle.setText(getString(R.string.question_info_capital));

                    //  Exibe a pergunta
                    mTvQuestion.setText(mListQuestions.get(currentIndex).getMain().trim());

                    //  Region Vazio
                    mTvRegion.setText("");

                    //  Esconde imageview e mostra textview
                    mIvImagem.setVisibility(View.GONE);
                    mTvQuestion.setVisibility(View.VISIBLE);

                    break;

                case Common.TYPE_FLAGS:  //  Imagem Flag

                    //  Exibe a pergunta de infromação
                    mTvTitle.setText(getString(R.string.question_info_flag));

                    //  Obtem referencia da imagem na List
                    String imageFlag = mListQuestions.get(currentIndex).getFlag().toLowerCase().trim();

                    //  Obtem referencia do id da imagem na pasta drawable, pelo nome da imagem
                    int imageFlagId = getResources().getIdentifier(imageFlag, "drawable", getPackageName());

                    //  Coloca imagem no imageview
                    mIvImagem.setImageDrawable(getResources().getDrawable(imageFlagId));

                    //  Coloca a região do pais/estado
                    mTvRegion.setText(mListQuestions.get(currentIndex).getRegion().trim());

                    //  Esconde textview e mostra imageview
                    mTvQuestion.setVisibility(View.GONE);
                    mIvImagem.setVisibility(View.VISIBLE);

                    break;

                case Common.TYPE_MAPS:  //  Imagem Map

                    //  Exibe a pergunta de infromação
                    mTvTitle.setText(getString(R.string.question_info_map));

                    //  Obtem referencia da imagem na List
                    String imageMap = mListQuestions.get(currentIndex).getMap().toLowerCase().trim();

                    //  Obtem o id da imagem na pasta drawable, pelo nome da imagem
                    int imageMapId = getResources().getIdentifier(imageMap, "drawable", getPackageName());

                    //  Coloca a imagem no imageview
                    mIvImagem.setImageDrawable(getResources().getDrawable(imageMapId));

                    //  Coloca a região do pais/estado
                    mTvRegion.setText(mListQuestions.get(currentIndex).getRegion().trim());

                    //  Esconde o textview e mostra o imageview
                    mTvQuestion.setVisibility(View.GONE);
                    mIvImagem.setVisibility(View.VISIBLE);

                    break;
            }

            //  Obtem conjunto de alternativas para botões
            AnswerBut answerBut = Alternatives.getAlternatives(
                    mListQuestions.get(currentIndex).getMain().trim(),
                    Common.gameType,
                    mListAnswers
            );

            mButA.setText(answerBut.getA());
            mButB.setText(answerBut.getB());
            mButC.setText(answerBut.getC());
            mButD.setText(answerBut.getD());

            //  Habilita Controles
            Controls.enabled(mContext, mButA, mButB, mButC, mButD);

            //  Inicia contador
            mCountDownTimer.start();

        } else {

            isEndQuestions = true;
            endQuestions();

        }
    }

    @Override
    public void onClick(View viewCliked) {

        //  Para contador
        mCountDownTimer.cancel();

        //  Verifica se ainda há pergunta
        if (mIndex < mQuantity) {

            //  Desabilita controles
            Controls.disable(mButA, mButB, mButC, mButD);

            Button clickedButton = (Button) viewCliked;

            //  Resposta escolhida peo usuario
            String textButtonCliked = clickedButton.getText().toString().trim();

            //  Resposta correta
            String correct = Alternatives.getCorrect(mListQuestions, Common.gameType, mIndex);

            if (textButtonCliked.equals(correct)) { //    CORRETA

                clickedButton.setBackground(getResources().getDrawable(R.drawable.bg_correct));
                
                mCorrects++;
                
                mScore+=10;
                mTvScore.setText(String.format(getString(R.string.showScore),mScore));
                
                //  Tempo de espera
                delayResult(true,1000);
                

            } else{ //  ERRADA

                clickedButton.setBackground(getResources().getDrawable(R.drawable.bg_error));

                Controls.showCorrect(correct,mContext,mButA,mButB,mButC,mButD);

                delayResult(false,2000);
            }
        }
    }

    private void delayResult(final boolean isCorrect, int time) {

        Handler handler = new Handler();

        Runnable codeToRun = new Runnable() {
            @Override
            public void run() {

                if(isCorrect){   //  CORRETA

                    showQuestion(++mIndex);

                }else{  //  ERRADA

                    isEndQuestions = true;

                    endQuestions();
                }
            }
        };

        handler.postDelayed(codeToRun,time);
    }

    private void endQuestions() {

        //  Encerra contador
        mCountDownTimer.cancel();

        Intent toDone = new Intent(mContext, DoneActivity.class);

        Bundle dataSend = new Bundle();

        //  Envia - Score / Quantity / Corrects
        dataSend.putInt(Const.INTENT_SCORE, mScore);
        dataSend.putInt(Const.INTENT_CORRECTS, mCorrects);
        dataSend.putInt(Const.INTENT_QUANTITY, mQuantity);

        toDone.putExtras(dataSend);
        startActivity(toDone);

        finish();
    }

    @Override
    public void onBackPressed() {

        isEndQuestions = true;
        endQuestions();
    }

    @Override
    protected void onStop() {
        super.onStop();

        verifyEndQuestions();
    }

    private void verifyEndQuestions() {

        if (!isEndQuestions) {

            Wait.delay(1000);
            endQuestions();
        }
    }
}
