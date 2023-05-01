package com.example.dtialphatesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout addItem, addSales, addCust, viewSales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        addItem = (ConstraintLayout) findViewById(R.id.addItem);
        addSales = (ConstraintLayout) findViewById(R.id.addSales);
        viewSales = (ConstraintLayout) findViewById(R.id.viewSales);
        addCust = (ConstraintLayout) findViewById(R.id.addCust);

        addItem.setOnClickListener(this);
        addSales.setOnClickListener(this);
        viewSales.setOnClickListener(this);
        addCust.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.addItem : i = new Intent(this,AddItemActivity.class);startActivity(i); break;
            case R.id.addSales: i = new Intent(this,AddSalesActivity.class); startActivity(i); break;
            case R.id.viewSales: i = new Intent(this,ViewSalesActivity.class);startActivity(i); break;
            case R.id.addCust: i = new Intent(this, TestActivity.class); startActivity(i); break;
            default: break;
        }
    }
}