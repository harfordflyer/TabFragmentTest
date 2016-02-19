package com.example.dhammond1.tabfragmenttest;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Chronometer;


/**
 * Created by dhammond1 on 2/14/2016.
 */
public class FragmentMain extends Fragment {
    private String s_currentPitTemp;
    private String s_currentMeatTemp;
    private String s_targetPitTemp;

    private String s_targetMinTemp;
    private String s_targetFanSpeed;
    private EditText ed_PitEditBox;
    private EditText ed_MinEditBox;
    private EditText ed_FanEditBox;
    private static TextView tv_PitText;
    private static TextView tv_MeatText;




    Chronometer chrono;
    long mLastStopTime = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View thisView;
        Button btnStart;
        Button btnStop;
        Button btn_minPit;
        Button btn_minFan;
        Button btn_setPit;

        thisView = inflater.inflate(R.layout.fragment_main, container, false);

        ed_PitEditBox = (EditText)thisView.findViewById(R.id.ed_targetPitTemp);
        ed_MinEditBox = (EditText)thisView.findViewById(R.id.ed_targetMinTemp);
        ed_FanEditBox = (EditText)thisView.findViewById(R.id.ed_targetFanSpeed);
        tv_PitText = (TextView)thisView.findViewById(R.id.tx_tempPit);
        tv_MeatText = (TextView)thisView.findViewById(R.id.tx_tempMeat);
        tv_PitText.setText("");
        tv_MeatText.setText("");


        Toast.makeText(getActivity(),"fragment main",Toast.LENGTH_LONG).show();

        btn_setPit = (Button)thisView.findViewById(R.id.btn_setPit);
        btn_minFan = (Button)thisView.findViewById(R.id.btn_setFanSpeed);
        btn_minPit = (Button)thisView.findViewById(R.id.btn_setMinTemp);

        btnStart = (Button) thisView.findViewById(R.id.chronStart);
        btnStop = (Button) thisView.findViewById(R.id.chronStop);
        chrono = (Chronometer)thisView.findViewById(R.id.chronometer);


        btn_setPit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                s_targetPitTemp = String.valueOf(ed_PitEditBox.getText());
            }
        });

        btn_minPit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                s_targetMinTemp = String.valueOf(ed_MinEditBox.getText());
            }
        });

        btn_minFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                s_targetFanSpeed = String.valueOf((ed_FanEditBox.getText()));
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mLastStopTime == 0)
                {
                    chrono.setBase(SystemClock.elapsedRealtime());
                }
                else
                {
                    long intervalOnPause = (SystemClock.elapsedRealtime() - mLastStopTime);
                    chrono.setBase(chrono.getBase() + intervalOnPause);
                }
                chrono.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                chrono.stop();

                mLastStopTime = SystemClock.elapsedRealtime();

            }
        });


        return thisView;

    }



}
