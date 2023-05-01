package com.example.dtialphatesting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddSalesActivity extends AppCompatActivity implements View.OnClickListener {


    private Button salescancelbtn, salessavebtn;
    Spinner customerSpinner;
    DatabaseReference databaseReference;
    List<String> customer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales);
        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        databaseReference.keepSynced(true);

        salescancelbtn = (Button) findViewById(R.id.salesCancelbtn);
        salessavebtn = (Button) findViewById(R.id.salesSavebtn);
        customerSpinner = (Spinner) findViewById( R.id.customerSalesSpinner);
        customer=new ArrayList<>();
        salescancelbtn.setOnClickListener(this);
        salessavebtn.setOnClickListener(this);



        databaseReference.child("Customer").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot:snapshot.getChildren()) {
                    String spinnerCustomer = childSnapshot.child("customerNameValue").getValue(String.class);
                    customer.add(spinnerCustomer);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AddSalesActivity.this, android.R.layout.simple_spinner_dropdown_item, customer);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                customerSpinner.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.salesCancelbtn: i = new Intent(AddSalesActivity.this, DashboardActivity.class); startActivity(i); break;
            case R.id.salesSavebtn: showaddsalesagaindialog(); break;
            default:
        }
    }

    private void showaddsalesagaindialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add more sales?");
        builder.setMessage("Do you want to add more sales?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                startActivity(getIntent());
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(AddSalesActivity.this, DashboardActivity.class));
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}