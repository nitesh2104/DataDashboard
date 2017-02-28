package com.capstone490.nitesh.datadashboard.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.capstone490.nitesh.datadashboard.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        createLineChart();
                    }
                });
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void createLineChart() {
        LineChart linechart = (LineChart) findViewById(R.id.graph);
        ArrayList<String> XAxis = new ArrayList<>();
        ArrayList<Entry> YAxisSin = new ArrayList<>();
        ArrayList<Entry> YAxisCos = new ArrayList<>();

        double x = 0;
        for (int i = 0; i < 1000; i++) {
            float rotating_panel_data = (float) (Math.random()*75);
            float fixed_panel_data = (float) (Math.random()*60);
            x = x + 10;

            XAxis.add(i, String.valueOf(x));
            YAxisSin.add(new Entry(rotating_panel_data, i));
            YAxisCos.add(new Entry(fixed_panel_data, i));
        }

        String[] XAxes = new String[XAxis.size()];
        for (int i = 0; i < XAxis.size(); i++) {
            XAxes[i] = XAxis.get(i);
        }
        ArrayList<ILineDataSet> linedatasets = new ArrayList<>();
        LineDataSet lineDataSet1 = new LineDataSet(YAxisSin, "Rotating Solar Panel Data");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);

        LineDataSet lineDataSet2 = new LineDataSet(YAxisCos, "Fixed Solar Panel Data");
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setColor(Color.RED);

        linedatasets.add(lineDataSet1);
        linedatasets.add(lineDataSet2);

        linechart.setData(new LineData(XAxes, linedatasets));
        linechart.setVisibleXRange(1,10);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK)){
            Intent intent = new Intent(getApplicationContext(), Navigation_Drawer.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
