package com.example.agykoala.coursach.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agykoala.coursach.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ReportModifyActivity extends AppCompatActivity {

    FileOutputStream fileOutputStream;
    EditText fileName, report;
    BufferedWriter bw;
    String end;
    Button save;
    String LOG_TAG = "file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_modify);

        Intent intent = getIntent();

        String input = intent.getStringExtra("input");
        String output = intent.getStringExtra("output");

        fileName = (EditText) findViewById(R.id.fileName);
        report = (EditText) findViewById(R.id.report);
        save = (Button)findViewById(R.id.saveButton);
        end = report.getText().toString() + "input:";
        end += input;
        end += "output:";
        end += output;
        report.setText(end);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrintWriter out = null;
                try {
                    out = new PrintWriter(new OutputStreamWriter(openFileOutput("Report.txt", MODE_WORLD_READABLE)));
//                    fileOutputStream = openFileOutput("Report.txt", MODE_WORLD_READABLE);
//                    fileOutputStream.write(end.getBytes());
//                    fileOutputStream.close();
                    out.println(end);
                    Toast toast = Toast.makeText(getApplicationContext(),"File save" , Toast.LENGTH_SHORT);
                    toast.show();
                    Log.d(LOG_TAG, "Файл записан");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    out.close();
                }
            }
        });
    }
}
