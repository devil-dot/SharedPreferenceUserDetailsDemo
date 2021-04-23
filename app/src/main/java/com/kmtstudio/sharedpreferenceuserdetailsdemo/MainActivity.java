package com.kmtstudio.sharedpreferenceuserdetailsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userEdtTxt,passEdtTxt;
    private Button saveBtn,loadBtn;
    private TextView detailsTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userEdtTxt = findViewById(R.id.userEdtTxtID);
        passEdtTxt = findViewById(R.id.passEdtTxtID);

        saveBtn = findViewById(R.id.saveBtnID);
        loadBtn = findViewById(R.id.loadBtnID);

        detailsTxtView = findViewById(R.id.detailsTxtID);


        saveBtn.setOnClickListener(this);
        loadBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.saveBtnID) {

            String userName = userEdtTxt.getText().toString();
            String passWord = passEdtTxt.getText().toString();

            if (userName.equals("") && passWord.equals("")) {

                Toast.makeText(getApplicationContext(),"Please enter some data",Toast.LENGTH_SHORT).show();

            } else {

                //to write data
                SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userNameKey",userName);
                editor.putString("passWordKey",passWord);
                editor.apply();

                Toast.makeText(getApplicationContext(),"data stored successfully",Toast.LENGTH_SHORT).show();

                userEdtTxt.setText("");
                passEdtTxt.setText("");


            }


        } else if (v.getId()==R.id.loadBtnID) {

            //to read data
            SharedPreferences sharedPreferences = getSharedPreferences("userDetails",Context.MODE_PRIVATE);

            if (sharedPreferences.contains("userNameKey") && sharedPreferences.contains("passWordKey")) {

                String user = sharedPreferences.getString("userNameKey","Data not found");
                String pass = sharedPreferences.getString("passWordKey","Data not found");

                detailsTxtView.setText(user+"\n"+pass);
            }
        }

    }
}