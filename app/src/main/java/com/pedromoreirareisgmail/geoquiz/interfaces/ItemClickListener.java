package com.pedromoreirareisgmail.geoquiz.interfaces;

import android.view.View;

public interface ItemClickListener {

    //  Interface para clicks no Recyclerview
    void onClick(View view, int position, boolean isLongClick);
}
