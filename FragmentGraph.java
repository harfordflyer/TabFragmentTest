package com.example.dhammond1.tabfragmenttest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dhammond1 on 2/14/2016.
 */
public class FragmentGraph extends Fragment{
    DatabaseHandler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View thisView;

        thisView = inflater.inflate(R.layout.fragment_graph, container, false);

        SetDataPoints(thisView);


        return thisView;
    }

    public void SetDataPoints(View thisView)
    {
        GraphView graph = (GraphView)thisView.findViewById(R.id.graph);

        DatabaseHandler dbHandler = handler.getInstance(thisView.getContext());

        TemperatureEntry entry = dbHandler.getLastEntry();

        String date = entry.getDate();

        List<TemperatureEntry> entries = dbHandler.getEntriesByDate(date);

        double count = 0.0;
        LineGraphSeries<DataPoint> series;


        ArrayList<DataPoint> dataList = new ArrayList<DataPoint>();
        for(TemperatureEntry e : entries)
        {
            e.getPitTemp();
            DataPoint p = new DataPoint(count, Double.parseDouble(e.getPitTemp()));
            dataList.add(p);
            count++;
        }

        DataPoint[] dataPoints = new DataPoint[dataList.size()];
        dataPoints = dataList.toArray(dataPoints);

        series = new LineGraphSeries<DataPoint>(dataPoints);


      /*  LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0,1),
                new DataPoint(1,1),
                new DataPoint(4,5),
                new DataPoint(5,6),
                new DataPoint(8,10),
                new DataPoint(4,3),
        });*/
        graph.addSeries(series);
    }

}
