package com.pedromoreirareisgmail.geoquiz.Game;

import android.content.Context;
import android.widget.Button;

import com.pedromoreirareisgmail.geoquiz.R;

public class Controls {

    public static void enabled(Context context, Button butA, Button butB, Button butC, Button butD){

        butA.setBackground(context.getDrawable(R.drawable.bg_button));
        butB.setBackground(context.getDrawable(R.drawable.bg_button));
        butC.setBackground(context.getDrawable(R.drawable.bg_button));
        butD.setBackground(context.getDrawable(R.drawable.bg_button));

        butA.setEnabled(true);
        butB.setEnabled(true);
        butC.setEnabled(true);
        butD.setEnabled(true);
    }

    public static void disable(Button butA, Button butB, Button butC, Button butD){

        butA.setEnabled(false);
        butB.setEnabled(false);
        butC.setEnabled(false);
        butD.setEnabled(false);
    }


    public static void showCorrect(String correct,Context context, Button butA, Button butB, Button butC, Button butD){

        //  Colocar o background de resposta correta

        String a = butA.getText().toString().trim();
        String b = butB.getText().toString().trim();
        String c = butC.getText().toString().trim();
        String d = butD.getText().toString().trim();


        if(a.equals(correct)){

            butA.setBackground(context.getResources().getDrawable(R.drawable.bg_correct));

        }else if(b.equals(correct)){

            butB.setBackground(context.getResources().getDrawable(R.drawable.bg_correct));

        }else if(c.equals(correct)){

            butC.setBackground(context.getResources().getDrawable(R.drawable.bg_correct));

        }else if(d.equals(correct)){

            butD.setBackground(context.getResources().getDrawable(R.drawable.bg_correct));
        }

    }

}
