package com.example.dtialphatesting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText productName, productPrice;
    private TextView productCode;
    private Button cancelBtn, addBtn;
    public static TextView resultTextView;
    DatabaseReference databaseReference;

    Spinner spinner;
    List<String> category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        databaseReference.keepSynced(true);

        category=new ArrayList<>();
        resultTextView = findViewById(R.id.textProductQrBarcode);
        cancelBtn = (Button) findViewById(R.id.btnItemCancelbutton);
        addBtn = (Button) findViewById(R.id.btnItemAddbutton);
        productName = (EditText) findViewById( R.id.editProductName);
        spinner = (Spinner) findViewById(R.id.spinnerCategory);
        productPrice = (EditText) findViewById( R.id.editProductPrice);
        productCode = (TextView) findViewById(R.id.textProductQrBarcode);

        cancelBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);


        startActivity(new Intent(this, ScanCodeActivity.class));

        databaseReference.child("Category").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot:snapshot.getChildren()) {
                    String spinnerCategory = childSnapshot.child("productCategoryValue").getValue(String.class);
                    category.add(spinnerCategory);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AddItemActivity.this, android.R.layout.simple_spinner_dropdown_item, category);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void additem() {
        String productNameValue = productName.getText().toString();
        String productCategoryValue = spinner.getSelectedItem().toString();
        double productPriceValue = Double.parseDouble(productPrice.getText().toString());
        String qrbarcodeValue = productCode.getText().toString();
        if(qrbarcodeValue.isEmpty()) {
            productCode.setError("Is Empty");
            productCode.requestFocus();
            return;
        }
        try {
            if (!TextUtils.isEmpty(productNameValue) && !TextUtils.isEmpty(productCategoryValue) && !TextUtils.isEmpty(String.valueOf(productPriceValue)) && !TextUtils.isEmpty(qrbarcodeValue)) {

                ProductModel item = new ProductModel(productNameValue, productCategoryValue, productPriceValue, qrbarcodeValue);
                databaseReference.child("Product").child(productNameValue + "-" + qrbarcodeValue).setValue(item);
                databaseReference.keepSynced(true);
                productName.setText("");
                spinner.getSelectedItem();
                productPrice.setText("");
                productCode.setText("");
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
            case R.id.btnItemCancelbutton: i = new Intent(this, DashboardActivity.class); startActivity(i); break;
            case R.id.btnItemAddbutton: additem(); break;
            default:
        }
    }

    private void showaaddagaindialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Product");
        builder.setMessage("Do you want to add another product?");
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
                startActivity(new Intent(AddItemActivity.this, DashboardActivity.class));
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}