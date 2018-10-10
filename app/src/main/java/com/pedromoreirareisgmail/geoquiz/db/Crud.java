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

    public static int getIdByCategoryAndType(String category, String type, Context context) {

        String[] sellectionCategory = new String[]{category, type};

        int id = -1;

        DbHelper dbHelper = new DbHelper(context);

        Cursor cursor;

        try (SQLiteDatabase db = dbHelper.getReadableDatabase()) {

            cursor = db.rawQuery(
                    "SELECT Id FROM Rankings WHERE Category = ? AND Type = ?",
                    sellectionCategory
            );

            if (cursor == null) {

                return -1;
            }

            cursor.moveToFirst();

            do {

                id = cursor.getInt(cursor.getColumnIndex(Const.RANKINGS_ID));

            } while (cursor.moveToNext());

            cursor.close();

        } catch (Exception e) {

            Log.d(Const.LOG_EXCEPTIONS, "Exception getIdByCategoryAndType: " + e.toString());
        }

        return id;
    }

    public static int getScoreByCategoryAndType(String category, String type, Context context) {

        String[] sellectionCategory = new String[]{category, type};

        int value = 0;

        DbHelper dbHelper = new DbHelper(context);

        Cursor cursor;

        try (SQLiteDatabase db = dbHelper.getReadableDatabase()) {

            //  Retorna
            cursor = db.rawQuery(
                    "SELECT MAX(Score) AS ScoreMax FROM Rankings WHERE Category = ? AND Type = ?",
                    sellectionCategory
            );

            if (cursor == null) {

                return 0;
            }

            cursor.moveToFirst();

            do {

                value = cursor.getInt(cursor.getColumnIndex("ScoreMax"));

            } while (cursor.moveToNext());

            cursor.close();

        } catch (Exception e) {

            Log.d(Const.LOG_EXCEPTIONS, "Exception getScoreByCategoryAndType: " + e.toString());
        }

        return value;
    }

    public static int getSumScoreByCategory(String category, Context context) {

        String[] sellectionCategory = new String[]{category};

        int sum = 0;

        DbHelper dbHelper = new DbHelper(context);

        Cursor cursor;

        try (SQLiteDatabase db = dbHelper.getReadableDatabase()) {

            cursor = db.rawQuery(
                    "SELECT SUM(Score) AS SumScore FROM Rankings WHERE Category = ?",
                    sellectionCategory
            );

            if (cursor == null) {

                return 0;
            }

            cursor.moveToFirst();

            do {

                sum = cursor.getInt(cursor.getColumnIndex("SumScore"));

            } while (cursor.moveToNext());

            cursor.close();

        } catch (Exception e) {

            Log.d(Const.LOG_EXCEPTIONS, "Exception getScoreByCategory: " + e.toString());
        }

        return sum;
    }

    public static void insertScore(int score, String category, String type, Context context) {

        DbHelper dbHelper = new DbHelper(context);

        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {

            ContentValues values = new ContentValues();
            values.put(Const.RANKINGS_SCORE, score);
            values.put(Const.RANKINGS_CATEGORY, category);
            values.put(Const.RANKINGS_TYPE, type);

            db.insert(Const.TABLE_RANKINGS, null, values);

        } catch (Exception e) {

            Log.d(Const.LOG_EXCEPTIONS, "Exception insertScore: " + e.toString());
        }


    }

    public static void updateScore(int id, int score, String category, String type, Context context) {

        String idUpdate = "Id = ?";
        String[] sellectionArgs = new String[]{String.valueOf(id)};

        DbHelper dbHelper = new DbHelper(context);

        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {

            ContentValues values = new ContentValues();
            values.put(Const.RANKINGS_SCORE, score);
            values.put(Const.RANKINGS_CATEGORY, category);
            values.put(Const.RANKINGS_TYPE, type);

            db.update(Const.TABLE_RANKINGS, values, idUpdate, sellectionArgs);

        } catch (Exception e) {

            Log.d(Const.LOG_EXCEPTIONS, "Exception updateScore: " + e.toString());
        }


    }
}
