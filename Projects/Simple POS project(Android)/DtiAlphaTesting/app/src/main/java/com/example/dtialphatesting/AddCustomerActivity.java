package com.example.dtialphatesting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCustomerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText addCustomerName, addCustomerAddress, addCustomerNumber, addCustomerEmail;
    private Button btnCustomerCancel, btnCustomerAdd;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        addCustomerName = (TextInputEditText)findViewById(R.id.txtCustomerName);
        addCustomerAddress = (TextInputEditText) findViewById(R.id.txtCustomerAddress);
        addCustomerNumber = (TextInputEditText) findViewById(R.id.txtCustomerNumber);
        addCustomerEmail = (TextInputEditText) findViewById(R.id.txtCustomerEmail);
        btnCustomerAdd = (Button) findViewById(R.id.btnCustomerAdd);
        btnCustomerCancel = (Button) findViewById(R.id.btnCustomerCancel);

        btnCustomerAdd.setOnClickListener(this);
        btnCustomerCancel.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        databaseReference.keepSynced(true);

    }

    private void additem() {
        String customerNameValue = addCustomerName.getText().toString();
        String customerAddressValue = addCustomerAddress.getText().toString();
        String customerNumberValue = addCustomerNumber.getText().toString();
        String customerEmailValue = addCustomerEmail.getText().toString();

        try {
            if (!TextUtils.isEmpty(customerNameValue) && !TextUtils.isEmpty(customerAddressValue) && !TextUtils.isEmpty(customerNumberValue) && !TextUtils.isEmpty(customerEmailValue)) {

                CustomerModel customer = new CustomerModel(customerNameValue, customerAddressValue, customerNumberValue, customerEmailValue);
                databaseReference.child("Customer").child(customerNameValue).setValue(customer);
                databaseReference.keepSynced(true);
                addCustomerName.setText("");
                addCustomerAddress.setText("");
                addCustomerNumber.setText("");
                addCustomerEmail.setText("");
                Toast.makeText(this, "Successfully Added", Toast.LENGTH_LONG).show();
                showaaddagaindialog();
            }else {
                Toast.makeText(this, "Invalid Data", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception ex){
            Toast.makeText(this, "Invalid Data", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.btnCustomerAdd: additem();break;
            case R.id.btnCustomerCancel: i = new Intent(AddCustomerActivity.this, DashboardActivity.class); startActivity(i); break;
            default:
        }
    }

    private void showaaddagaindialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Customer");
        builder.setMessage("Do you want to add another customer?");
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
                startActivity(new Intent(AddCustomerActivity.this, DashboardActivity.class));
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}