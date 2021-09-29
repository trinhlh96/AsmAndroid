package com.example.asm.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.example.asm.R;
import com.example.asm.databases.DBHelper;

import java.util.ArrayList;


public class FragmentManage extends Fragment implements View.OnClickListener {
    private Button btnCreate;
    private Spinner spCategory;
    private DBHelper dbHelper;
    private EditText edName;
    private EditText edDescription;
    private EditText edDetail;
    private EditText edMoney;
    private DatePicker datePicker;
    private SimpleCursorAdapter simpleCursorAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View itemRoot = inflater.inflate(R.layout.expenditures, container, false);
        initView(itemRoot);
        return itemRoot;

    }

    private void initView(View itemRoot) {
        edName = itemRoot.findViewById(R.id.edName);
        edDescription = itemRoot.findViewById(R.id.edDescription);
        edDetail = itemRoot.findViewById(R.id.edDetail);
        edMoney = itemRoot.findViewById(R.id.edMoney);
        datePicker = itemRoot.findViewById(R.id.datePicker);
        btnCreate = itemRoot.findViewById(R.id.btnCreate);
        spCategory = itemRoot.findViewById(R.id.spCategory);
        dbHelper = new DBHelper(this.getContext());

        Cursor cursor = dbHelper.getAllCategories();
        ArrayList<String> categories = this.getCategories(cursor);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, categories);
        spCategory.setAdapter(adapter);
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    private ArrayList<String> getCategories(Cursor cursor) {

        ArrayList<String> categories = new ArrayList<String>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                categories.add(cursor.getString(cursor.getColumnIndex("name")));
            }
            cursor.close();
        }
        return categories;
    }

}