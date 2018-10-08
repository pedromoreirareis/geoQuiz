package com.pedromoreirareisgmail.geoquiz.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pedromoreirareisgmail.geoquiz.Commons.Const;
import com.pedromoreirareisgmail.geoquiz.models.Answer;
import com.pedromoreirareisgmail.geoquiz.models.Category;
import com.pedromoreirareisgmail.geoquiz.models.Question;
import com.pedromoreirareisgmail.geoquiz.models.Ranking;

import java.util.ArrayList;
import java.util.List;

public class Crud {


    public static List<Category> getCategorys(Context context) {

        List<Category> listCategorys = new ArrayList<>();

        DbHelper dbHelper = new DbHelper(context);

        Cursor cursor;

        try (SQLiteDatabase db = dbHelper.getReadableDatabase()) {

            cursor = db.rawQuery(
                    "SELECT DISTINCT Category FROM Questions",
                    null
            );

            if (cursor == null) {

                return null;
            }

            cursor.moveToFirst();

            do {

                Category category = new Category(
                        cursor.getString(cursor.getColumnIndex(Const.QUESTIONS_CATEGORY))
                );

                listCategorys.add(category);

            } while (cursor.moveToNext());

            cursor.close();

        } catch (Exception e) {

            Log.d(Const.LOG_EXCEPTIONS, "Exception getCategorys: " + e.toString());

        }


        return listCategorys;
    }

    public static List<Question> getQuestionsByCategory(String category, Context context) {

        String[] selletionCategory = new String[]{category};

        List<Question> listQuestionByCategory = new ArrayList<>();

        DbHelper dbHelper = new DbHelper(context);

        Cursor cursor;

        try (SQLiteDatabase db = dbHelper.getReadableDatabase()) {

            cursor = db.rawQuery(
                    "SELECT * FROM Questions WHERE Category = ? ORDER BY Random()",
                    selletionCategory
            );

            if (cursor == null) {

                return null;
            }

            cursor.moveToFirst();

            do {

                Question question = new Question(
                        cursor.getString(cursor.getColumnIndex(Const.QUESTIONS_MAIN)),
                        cursor.getString(cursor.getColumnIndex(Const.QUESTIONS_FLAG)),
                        cursor.getString(cursor.getColumnIndex(Const.QUESTIONS_MAP)),
                        cursor.getString(cursor.getColumnIndex(Const.QUESTIONS_CAPITAL)),
                        cursor.getString(cursor.getColumnIndex(Const.QUESTIONS_CATEGORY)),
                        cursor.getString(cursor.getColumnIndex(Const.QUESTIONS_REGION)),
                        cursor.getString(cursor.getColumnIndex(Const.QUESTIONS_DESCRIPTION))
                );

                listQuestionByCategory.add(question);

            } while (cursor.moveToNext());

            cursor.close();

        } catch (Exception e) {

            Log.d(Const.LOG_EXCEPTIONS, "Exception getQuestionsByCategory: " + e.toString());
        }

        return listQuestionByCategory;
    }

    public static List<Answer> getAnswersByCategory(String category, Context context) {

        String[] sellectionCategory = new String[]{category};

        List<Answer> listAnswerByCategory = new ArrayList<>();

        DbHelper dbHelper = new DbHelper(context);

        Cursor cursor;

        try (SQLiteDatabase db = dbHelper.getReadableDatabase()) {

            cursor = db.rawQuery(
                    "SELECT * FROM Answers WHERE Category = ? ORDER BY Random()",
                    sellectionCategory
            );

            if (cursor == null) {

                return null;
            }

            cursor.moveToFirst();

            do {

                Answer answer = new Answer(
                        cursor.getString(cursor.getColumnIndex(Const.ANSWERS_MAIN)),
                        cursor.getString(cursor.getColumnIndex(Const.ANSWERS_FLAG)),
                        cursor.getString(cursor.getColumnIndex(Const.ANSWERS_MAP)),
                        cursor.getString(cursor.getColumnIndex(Const.ANSWERS_CAPITAL)),
                        cursor.getString(cursor.getColumnIndex(Const.ANSWERS_CATEGORY))
                );

                listAnswerByCategory.add(answer);


            } while (cursor.moveToNext());

            cursor.close();

        } catch (Exception e) {

            Log.d(Const.LOG_EXCEPTIONS, "Exception getAnswersByCategory: " + e.toString());
        }

        return listAnswerByCategory;
    }

    public static List<Ranking> getScore(String category, Context context) {

        String[] sellectionCategory = new String[]{category};

        List<Ranking> listRankingByCategory = new ArrayList<>();

        DbHelper dbHelper = new DbHelper(context);

        Cursor cursor;

        try (SQLiteDatabase db = dbHelper.getReadableDatabase()) {

            cursor = db.rawQuery(
                    "SELECT * FROM Rankings WHERE Category = ?",
                    sellectionCategory
            );

            if (cursor == null) {

                return null;
            }

            cursor.moveToFirst();

            do {

                Ranking ranking = new Ranking(
                        cursor.getInt(cursor.getColumnIndex(Const.RANKINGS_ID)),
                        cursor.getInt(cursor.getColumnIndex(Const.RANKINGS_SCORE)),
                        cursor.getString(cursor.getColumnIndex(Const.RANKINGS_CATEGORY))
                );

                listRankingByCategory.add(ranking);

            } while (cursor.moveToNext());

            cursor.close();

        } catch (Exception e) {

            Log.d(Const.LOG_EXCEPTIONS, "Exception getScoreByCategory: " + e.toString());
        }

        return listRankingByCategory;
    }

    public static void insertScore(int score, String category, Context context) {

        DbHelper dbHelper = new DbHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Const.RANKINGS_SCORE, score);
        values.put(Const.RANKINGS_CATEGORY, category);

        db.insert(Const.TABLE_RANKINGS, null, values);
    }
}
