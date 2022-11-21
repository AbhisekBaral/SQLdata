package com.example.db1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public
class MainActivity extends AppCompatActivity {

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et1 = findViewById(R.id.et1);
        EditText et2 = findViewById(R.id.et2);
        EditText et3 = findViewById(R.id.et3);
        Button B1 = findViewById(R.id.B1);
        Button B2 = findViewById(R.id.B2);
        Button B3 = findViewById(R.id.B3);
        Button B4 = findViewById(R.id.B4);
        database d = new database(this);
        // SQLiteDatabase db = d.getReadableDatabase();

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
                String name1 = et1.getText().toString();
                String username1 = et2.getText().toString();
                String password1 = et3.getText().toString();
                if (name1.equals("")|| username1.equals("")||password1.equals("")){
                    Toast.makeText(MainActivity.this,"Enter all the fields",Toast.LENGTH_SHORT).show();
                }
                else{
                  Boolean i = d.insert_data(name1,username1,password1);
                  if(i==true){
                      Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                  }
                  else{
                      Toast.makeText(MainActivity.this, "Not Successful", Toast.LENGTH_SHORT).show();
                  }
                }

                B2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public
                    void onClick(View v) {
                        String username = et2.getText().toString();
                        Boolean i = d.delete_data(username);
                        if(i==true)
                            Toast.makeText(MainActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "Not Successfull", Toast.LENGTH_SHORT).show();
                    }

                });


                B3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public
                    void onClick(View v) {
                        Cursor t = d.getinfo();
                        if(t.getCount()==0){
                            Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (t.moveToNext()){
                            buffer.append("Name::" +t.getString(0)+"\n");
                            buffer.append("Username::" +t.getString(1)+"\n");
                            buffer.append("Password::" +t.getString(2)+"\n\n\n");
                        }
                        AlertDialog.Builder builder = new  AlertDialog.Builder(MainActivity.this);
                        builder.setCancelable(true);
                        builder.setTitle("signUp user data");
                        builder.setMessage(buffer.toString());
                        AlertDialog alertDialog = builder.create();
                        builder.show();

                    }
                });

                B4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public
                    void onClick(View v) {
                        String name = et1.getText().toString();
                        String usernaem = et2.getText().toString();
                        String password = et3.getText().toString();
                        Boolean i = d.update_data(name,usernaem,password);
                        if(i == true)
                            Toast.makeText(MainActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                    else
                            Toast.makeText(MainActivity.this, "Not Successful", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });
    }
}
