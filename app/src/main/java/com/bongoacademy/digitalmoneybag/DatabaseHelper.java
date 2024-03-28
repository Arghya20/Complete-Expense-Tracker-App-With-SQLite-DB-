package com.bongoacademy.digitalmoneybag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "digital_moneybag", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table expense (id INTEGER primary key autoincrement, amount DOUBLE, reason TEXT, time DOUBLE)");
        db.execSQL("create table income (id INTEGER primary key autoincrement, amount DOUBLE, reason TEXT, time DOUBLE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists expense");
        db.execSQL("drop table if exists income");

    }

    //====================== Add Expense ========================================================
    public void addExpense(Double amount, String reason) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues conval = new ContentValues();
        conval.put("amount", amount);
        conval.put("reason", reason);
        conval.put("time", System.currentTimeMillis());

        db.insert("expense", null, conval);
    }


    //====================== Get total Expense ====================================================
    public double calculateTotalExpense() {
        double totalExpense = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from expense", null);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                double amount = cursor.getDouble(1);
                totalExpense = totalExpense + amount;
            }
        }

        return totalExpense;
    }


    //====================== Add Income ========================================================
    public void addIncome(Double amount, String reason) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues conval = new ContentValues();
        conval.put("amount", amount);
        conval.put("reason", reason);
        conval.put("time", System.currentTimeMillis());

        db.insert("income", null, conval);
    }


    //====================== Get total Income ====================================================
    public double calculateTotalIncome() {
        double totalIncome = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from income", null);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                double amount = cursor.getDouble(1);
                totalIncome = totalIncome + amount;
            }
        }

        return totalIncome;
    }


    //    ================================ Get All Expenses ==============================================================
    public Cursor getAllExpenses() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from expense", null);
        return cursor;
    }

    //    ================================ Get All Income ==============================================================
    public Cursor getAllIncome() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from income", null);
        return cursor;
    }

    //    ======================================= Delete Expense ==============================================================
    public void deleteExpense(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from expense where id like " + id);
    }
    //    ======================================= Delete Income ==============================================================
    public void deleteIncome(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from income where id like " + id);
    }

}
