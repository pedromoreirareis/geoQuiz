package com.pedromoreirareisgmail.geoquiz.Commons;

import android.util.Log;

public class Wait {

    public static void delay(long time){

        try {

            Thread.sleep(time);

        }catch (InterruptedException e){

            Log.d(Const.LOG_EXCEPTIONS, "Exception Wait.delay: " + e.toString());
        }
    }
}
