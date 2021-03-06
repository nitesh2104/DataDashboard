package com.capstone490.nitesh.datadashboard.Views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.capstone490.nitesh.datadashboard.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class Rotating_panel_graph extends AppCompatActivity {
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
        ArrayList<Entry> YAxisPoints = new ArrayList<>();

        double x = 0;
        for (int i = 0; i < 1000; i++) {
            float rotating_panel_data = (float) (Math.random()*75);
            x = x + 10;

            XAxis.add(i, String.valueOf(x));
            YAxisPoints.add(new Entry(rotating_panel_data, i));
        }

        String[] XAxes = new String[XAxis.size()];
        for (int i = 0; i < XAxis.size(); i++) {
            XAxes[i] = XAxis.get(i);
        }
        ArrayList<ILineDataSet> linedatasets = new ArrayList<>();
        LineDataSet lineDataSet1 = new LineDataSet(YAxisPoints, "Rotating Solar Panel Data");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);

        linedatasets.add(lineDataSet1);

        linechart.setData(new LineData(XAxes, linedatasets));
        linechart.setVisibleXRange(1,10);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK)){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}

