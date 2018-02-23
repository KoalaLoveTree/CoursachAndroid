package com.example.agykoala.coursach.activitys;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agykoala.coursach.R;
import com.example.agykoala.coursach.dataType.Graph;
import com.example.agykoala.coursach.dataType.Point;
import com.example.agykoala.coursach.interfaces.OnDataPass;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnDataPass {

    Button button;
    GraphView graphD;
    public Graph graph;

    Button add, stop;
    EditText x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = (Button) findViewById(R.id.addButton);
        stop = (Button) findViewById(R.id.stopButton);
        x = (EditText) findViewById(R.id.pointX);
        y = (EditText) findViewById(R.id.pointY);
        graphD = (GraphView) findViewById(R.id.graph);
        button = (Button) findViewById(R.id.buildGraph);
        graph = new Graph();
        graphD.getViewport().setScalable(true);
        graphD.getViewport().setScalableY(true);
        button.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_load_from_file:
                XmlResourceParser parser = getResources().getXml(R.xml.example);
                try {
                    processData(parser);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }

                return true;
//            case R.id.action_create_report:
//                intent = new Intent(this, ReportModifyActivity.class);
//                String input = "";
//                for (Point point : graph.getEnterData()) {
//                    input = input + "x = " + point.getPointX() + " y = " + point.getPointY();
//                }
//                String output = "";
//                for (Point point : graph.getOutputData()) {
//                    output = output + "x = " + point.getPointX() + " y = " + point.getPointY();
//                }
//                intent.putExtra("input", input);
//                intent.putExtra("output", output);
//                startActivity(intent);
//                return true;
            case R.id.action_open_help:
                intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

    private void processData(XmlResourceParser parser) throws IOException, XmlPullParserException {
        graph.getEnterData().clear();
        int eventType = -1;
        while (eventType != XmlResourceParser.END_DOCUMENT) {
            if (eventType == XmlResourceParser.START_TAG) {
                String locationValue = parser.getName();
                if (locationValue.equals("point")) {
                    graph.setEnterData(new Point(Double.parseDouble(parser.getAttributeValue(null, "x")),
                            Double.parseDouble(parser.getAttributeValue(null, "y"))));
                }
            }
            eventType = parser.next();
        }

    }

    public void drawGraph() {
        graph.setEnterData(graph.DelDubl(graph.getEnterData()));
        DataPoint[] dataPoint = new DataPoint[graph.getEnterData().size()];
        for (int i = 0; i < graph.getEnterData().size(); i++) {
            dataPoint[i] = new DataPoint(graph.getEnterData().get(i).getPointX(),
                    graph.getEnterData().get(i).getPointY());
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoint);
        series.setColor(Color.RED);
        graphD.addSeries(series);
        graph.createOutputData();
        graph.setOutputData(graph.DelDubl(graph.getOutputData()));
        DataPoint[] dataPoint1 = new DataPoint[graph.getOutputData().size()];
        for (int i = 0; i < graph.getOutputData().size(); i++) {
            dataPoint1[i] = new DataPoint(graph.getOutputData().get(i).getPointX(),
                    graph.getOutputData().get(i).getPointY());
        }
        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(dataPoint1);
        series1.setColor(Color.GREEN);
        graphD.addSeries(series1);
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < graph.getEnterData().size(); i++) {
            for (int j = 0; j < graph.getEnterData().size(); j++) {
                if (i != j) {
                    if (graph.getEnterData().get(i).getPointX() == graph.getEnterData().get(j).getPointX()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Incorrect input").setMessage("Enter data it's not a function")
                                .setCancelable(false).setNegativeButton("Ok, let's try",
                                (dialog, id) -> dialog.cancel());
                        AlertDialog alert = builder.create();
                        alert.show();
                        add.setEnabled(true);
                        stop.setEnabled(true);
                        x.setEnabled(true);
                        y.setEnabled(true);
                        button.setEnabled(false);
                    }
                }
            }
        }
        drawGraph();
        button.setEnabled(false);
        button.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDataPass(String x, String y) {
        graph.setEnterData(new Point(Double.parseDouble(x), Double.parseDouble(y)));
    }

}
