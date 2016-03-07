package com.example.dhammond1.tabfragmenttest;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
        LineGraphSeries<DataPoint> pitSeries = new LineGraphSeries<DataPoint>();
        LineGraphSeries<DataPoint> meatSeries = new LineGraphSeries<DataPoint>();
        int meatCol = ContextCompat.getColor(getContext(),R.color.colorAccent);

        int cccP = pitSeries.getColor();
        int cccM = meatSeries.getColor();

        meatSeries.setColor(Color.MAGENTA);
        pitSeries.setColor(Color.GREEN);
        cccM = meatSeries.getColor();
        cccP = pitSeries.getColor();

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        pitSeries.setCustomPaint(paint);

        ArrayList<DataPoint> pitDataList = new ArrayList<DataPoint>();
        ArrayList<DataPoint> meatDataList = new ArrayList<DataPoint>();
        for(TemperatureEntry e : entries)
        {
            //e.getPitTemp();
            DataPoint pit = new DataPoint(count, Double.parseDouble(e.getPitTemp()));
            DataPoint meat = new DataPoint(count, Double.parseDouble(e.getMeatTemp()));
            pitDataList.add(pit);
            meatDataList.add(meat);
            count++;
        }

        DataPoint[] pitDataPoints = new DataPoint[pitDataList.size()];
        DataPoint[] meatDataPoints = new DataPoint[meatDataList.size()];

        meatDataPoints = meatDataList.toArray(meatDataPoints);
        meatSeries = new LineGraphSeries<DataPoint>(meatDataPoints);
        graph.addSeries(meatSeries);

        pitDataPoints  = pitDataList.toArray(pitDataPoints);
        pitSeries = new LineGraphSeries<DataPoint>(pitDataPoints);
        graph.addSeries(pitSeries);
    }

}
