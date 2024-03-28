package com.bongoacademy.digitalmoneybag;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowAllData extends AppCompatActivity {

    TextView tvAllData;
    ListView listView;
    DatabaseHelper dbHelper;
    ArrayList<HashMap<String, String>> arrayList;
    HashMap<String, String> hashMap;
    public static boolean Expense = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_data);

        tvAllData = findViewById(R.id.tvAllData);
        listView = findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);

        if (Expense == true) tvAllData.setText("Showing all Expenses");
        else tvAllData.setText("Showing all Income");


        loadData();
    }


    public void loadData() {
        Cursor cursor = null;

        if (Expense == true) {
            cursor = dbHelper.getAllExpenses();
        } else {
            cursor = dbHelper.getAllIncome();
        }


        if (cursor != null && cursor.getCount() > 0) {

            arrayList = new ArrayList<>();

            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                double amount = cursor.getDouble(1);
                String reason = cursor.getString(2);
                double time = cursor.getDouble(3);

                hashMap = new HashMap<>();
                hashMap.put("id", "" + id);
                hashMap.put("amount", "" + amount);
                hashMap.put("reason", "" + reason);
                hashMap.put("time", "" + time);

                arrayList.add(hashMap);

            }
            MyAdapter myAdapter = new MyAdapter();
            listView.setAdapter(myAdapter);

        } else {
            tvAllData.append("\n\n No data found!");
        }
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.items, parent, false);
            TextView tvAmount, tvReason, tvDelete;
            tvAmount = view.findViewById(R.id.tvAmount);
            tvReason = view.findViewById(R.id.tvReason);
            tvDelete = view.findViewById(R.id.tvDelete);

            hashMap = arrayList.get(position);

            String id = hashMap.get("id");
            String amount = hashMap.get("amount");
            String reason = hashMap.get("reason");

            tvReason.setText(reason);
            tvAmount.setText(amount);


            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Expense == true) {
                        dbHelper.deleteExpense(id);
                    } else {
                        dbHelper.deleteIncome(id);
                    }


                    loadData();
                }
            });


            return view;
        }
    }


}