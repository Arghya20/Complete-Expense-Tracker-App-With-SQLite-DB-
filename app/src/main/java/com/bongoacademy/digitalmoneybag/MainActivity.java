package com.bongoacademy.digitalmoneybag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView totalBalance, tvTotalExpense, tvAddExpense, tvShowAllDataExpense, tvTotalIncome, tvAddIncome, tvShowAllDataIncome;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalBalance = findViewById(R.id.totalBalance);
        tvTotalExpense = findViewById(R.id.tvTotalExpense);
        tvAddExpense = findViewById(R.id.tvAddExpense);
        tvShowAllDataExpense = findViewById(R.id.tvShowAllDataExpense);
        tvTotalIncome = findViewById(R.id.tvTotalIncome);
        tvAddIncome = findViewById(R.id.tvAddIncome);
        tvShowAllDataIncome = findViewById(R.id.tvShowAllDataIncome);
        dbHelper = new DatabaseHelper(this);


        tvAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData.isExpense = true;
                startActivity(new Intent(MainActivity.this, AddData.class));
            }
        });


        tvAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData.isExpense = false;
                startActivity(new Intent(MainActivity.this, AddData.class));
            }
        });


        tvShowAllDataExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAllData.Expense = true;
                startActivity(new Intent(MainActivity.this, ShowAllData.class));
            }
        });

        tvShowAllDataIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAllData.Expense = false;
                startActivity(new Intent(MainActivity.this, ShowAllData.class));
            }
        });


        expenseDataShow();
        incomeDataShow();
        totalBalance();
    }

    public void expenseDataShow() {
        tvTotalExpense.setText("₹" + dbHelper.calculateTotalExpense());

    }

    public void incomeDataShow() {
        tvTotalIncome.setText("₹" + dbHelper.calculateTotalIncome());
    }

    public void totalBalance() {
        double totalAmount = dbHelper.calculateTotalIncome() - dbHelper.calculateTotalExpense();
        totalBalance.setText("₹" + totalAmount);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        expenseDataShow();
        incomeDataShow();
        totalBalance();
    }
//===================================================================
}