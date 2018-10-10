package com.pedromoreirareisgmail.geoquiz.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.pedromoreirareisgmail.geoquiz.Commons.Const;
import com.pedromoreirareisgmail.geoquiz.R;
import com.pedromoreirareisgmail.geoquiz.db.DbHelper;
import com.pedromoreirareisgmail.geoquiz.fragments.CategoryFragment;
import com.pedromoreirareisgmail.geoquiz.fragments.RankingFragment;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //  Cria DB se não existe
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        try {

            dbHelper.createDatabase();

        } catch (IOException e) {

            Log.d(Const.LOG_EXCEPTIONS, "Exception MainActivity/createDataBase: " + e.toString());
        }

        //  Referencia o BottomNavigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        //  Fragmento Padrão
        setDefaultFragmente();
    }

    private void setDefaultFragmente() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main_to_fragment, CategoryFragment.newInstance());
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        //  Cria instancia de fragmento
        Fragment selectedFragment = null;

        switch (menuItem.getItemId()) {

            //  Fragmento Category
            case R.id.action_category:
                selectedFragment = CategoryFragment.newInstance();
                break;

            //  Fragemnto Ranking
            case R.id.action_ranking:
                selectedFragment = RankingFragment.newInstance();
                break;
        }

        if(selectedFragment != null){

            //  Ativa frgamento selecionado
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_main_to_fragment,selectedFragment);
            transaction.commit();
        }

        return true;
    }


}
