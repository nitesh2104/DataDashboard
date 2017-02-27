package com.capstone490.nitesh.datadashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {
    LineChart lineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        LineChart linechart = (LineChart) findViewById(R.id.graph);
        ArrayList<String> XAxis = new ArrayList<>();
        ArrayList<Entry> YAxisSin = new ArrayList<>();
        ArrayList<Entry> YAxisCos = new ArrayList<>();

        double x = 0;
        int datapoints = 1000;

        for(int i=0; i<datapoints; i++){
            float sinfunc = Float.parseFloat(String.valueOf(Math.sin(x)));
            float cosfunc = Float.parseFloat(String.valueOf(Math.cos(x)));
            x = x + 0.1;

            XAxis.add(i, String.valueOf(x));
            YAxisSin.add(new Entry(sinfunc,i));
            YAxisCos.add(new Entry(cosfunc,i));
        }

        String[] XAxes = new String[XAxis.size()];
        for (int i=0; i< XAxis.size(); i++){
            XAxes[i] = XAxis.get(i);
        }
        ArrayList<ILineDataSet> linedatasets = new ArrayList<>();
        LineDataSet lineDataSet1 = new LineDataSet(YAxisSin,"Sin Function");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);

        LineDataSet lineDataSet2 = new LineDataSet(YAxisCos,"Cos Function");
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setColor(Color.RED);

        linedatasets.add(lineDataSet1);
        linedatasets.add(lineDataSet2);

        linechart.setData(new LineData(XAxes, linedatasets));
//        lineChart.setVisibleXRangeMaximum(65f);



    }
}
