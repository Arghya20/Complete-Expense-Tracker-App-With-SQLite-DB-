package com.bongoacademy.digitalmoneybag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddData extends AppCompatActivity {

    TextView tvDisplay;
    Button btn;
    EditText edAmount, edReason;
    DatabaseHelper dbHelper;

    public static boolean isExpense = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);


        tvDisplay = findViewById(R.id.tvDisplay);
        edAmount = findViewById(R.id.edAmount);
        edReason = findViewById(R.id.edReason);
        btn = findViewById(R.id.btn);
        dbHelper = new DatabaseHelper(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getAmount = edAmount.getText().toString();
                String getReason = edReason.getText().toString();
                Double doubleAmount = Double.parseDouble(getAmount);

                if (isExpense == true) {

                    dbHelper.addExpense(doubleAmount, getReason);
                    tvDisplay.setText("Expense Added Successfully");

                } else {
                    dbHelper.addIncome(doubleAmount, getReason);
                    tvDisplay.setText("Income Added Successfully");

                }


                // Reset EditText fields
                edAmount.setText("");
                edReason.setText("");
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}