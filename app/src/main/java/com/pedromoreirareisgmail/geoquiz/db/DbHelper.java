package com.pedromoreirareisgmail.geoquiz.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.pedromoreirareisgmail.geoquiz.Commons.Const;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MyDB.db";
    private static final int DB_VERSION = 1;

    private static String DB_PATH = "";
    private static SQLiteDatabase mDatabase;
    private Context mContext;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        //  Endereço da pasta onde fica os DBs
        DB_PATH = Objects.requireNonNull(context).getApplicationInfo().dataDir + "/databases/";

        //  Verifica se o DB "MyDB.db" existe
        File file = new File(DB_PATH + DB_NAME);
        if (file.exists()) {

            openDatabase();
        }

        mContext = context;
    }

    private void openDatabase() {

        //  Endereço do banco de dados
        String myPath = DB_PATH + DB_NAME;

        //  Abre o banco de dados no modo leitura e escrita
        mDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void createDatabase() throws IOException {


        boolean isDBExists = checkDataBase();

        if (!isDBExists) {

            this.getWritableDatabase();

            try {

                copyDatabase();

            } catch (IOException e) {

                Log.d(Const.LOG_EXCEPTIONS, "Exception createDatabase/copyDatabase: " + e.toString());
            }
        }

    }

    private boolean checkDataBase() {

        SQLiteDatabase tempDB = null;

        try {

            String myPath = DB_PATH + DB_NAME;
            tempDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);


        } catch (SQLiteException e) {

            Log.d(Const.LOG_EXCEPTIONS, "Exception checkDataBase: " + e.toString());

        }

        if (tempDB != null) {

            tempDB.close();
        }

        return tempDB != null;
    }

    private void copyDatabase() throws IOException {

        try {

            InputStream myImput = mContext.getAssets().open(DB_NAME);
            String outPutFileName = DB_PATH + DB_NAME;
            OutputStream myOutPut = new FileOutputStream(outPutFileName);


            byte[] buffer = new byte[1024];
            int length;

            while ((length = myImput.read(buffer)) > 0) {

                myOutPut.write(buffer, 0, length);
            }

            myOutPut.flush();
            myOutPut.close();
            myImput.close();


        } catch (Exception e) {

            Log.d(Const.LOG_EXCEPTIONS, "Exception copyDatabase: " + e.toString());
        }
    }


    @Override
    public synchronized void close() {

        if (mDatabase != null) {

            mDatabase.close();
        }

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
