package com.example.dhammond1.tabfragmenttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by dhammond1 on 2/14/2016.
 */
public class FragmentConfiguration extends Fragment implements View.OnClickListener{

    private String s_currentKp;
    private String s_currentKi;
    private String s_currentKd;
    private String s_targetPit;
    private String s_minTemp;
    private String s_maxTemp;
    private String s_fan;
    private String s_sampleTime;
    public String s_date;

    private EditText ed_currentKp;
    private EditText ed_currentKi;
    private EditText ed_currentKd;

    private EditText ed_targetPit;
    private EditText ed_minTemp;
    private EditText ed_maxTemp;
    private EditText ed_fanSpeed;
    private EditText ed_sampleTime;


    DatabaseHandler dbHandler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View thisView;

        s_date = DatabaseHandler.GetDateTime.GetDate(Calendar.getInstance());
        final String s_targetPit;
        final String s_minTemp;
        final String s_maxTemp;
        final String s_currentKp;
        final String s_currentKi;
        final String s_currentKd;
        final String s_fan;
        final String s_sampleTime;


        thisView = inflater.inflate(R.layout.fragment_configuration, container, false);
        ed_targetPit = (EditText)thisView.findViewById(R.id.ed_targetPit);
        ed_minTemp = (EditText)thisView.findViewById(R.id.ed_minPit);
        ed_maxTemp = (EditText)thisView.findViewById(R.id.ed_maxPit);
        ed_fanSpeed = (EditText)thisView.findViewById(R.id.ed_fanSpeed);
        ed_currentKp = (EditText)thisView.findViewById(R.id.ed_Kp);
        ed_currentKi = (EditText)thisView.findViewById(R.id.ed_Ki);
        ed_currentKd = (EditText)thisView.findViewById(R.id.ed_Kd);
        ed_sampleTime = (EditText)thisView.findViewById(R.id.ed_SampleTime);
        Button  btn_saveConfig = (Button)thisView.findViewById(R.id.btn_saveConfig);
        btn_saveConfig.setOnClickListener(this);



        // btn_saveConfig.setOnClickListener(new View.OnClickListener(){



           /* @Override
            public void onClick(View v){
                String target = String.valueOf(ed_targetPit.getText());
                String min = ed_minTemp.getText().toString();
                String max = ed_maxTemp.getText().toString();
                String fan = ed_fan.getText().toString();
                String kp = ed_currentKp.getText().toString();
                String ki = ed_currentKi.getText().toString();
                String kd = ed_currentKp.getText().toString();
                String samaple = ed_sampleTime.getText().toString();


                ConfigEntry entry = new ConfigEntry(date, ed_targetPit.getText().toString(),
                        ed_minTemp.getText().toString(),
                        ed_maxTemp.getText().toString(),
                        ed_fan.getText().toString(),
                        ed_currentKp.getText().toString(),
                        ed_currentKi.getText().toString(),
                        ed_currentKd.getText().toString(),
                        ed_sampleTime.getText().toString());

                dbHandler.addConfigEntry(entry);
            }*/
        //});

        return thisView;
    }

    @Override
    public void onClick(View v) {

        String target = String.valueOf(ed_targetPit.getText());
        String min =  String.valueOf(ed_minTemp.getText());
        String max = String.valueOf(ed_maxTemp.getText());
        String fan = String.valueOf(ed_fanSpeed.getText());
        String kp = String.valueOf(ed_currentKp.getText());
        String ki = String.valueOf(ed_currentKi.getText());
        String kd = String.valueOf(ed_currentKp.getText());
        String sample = String.valueOf(ed_sampleTime.getText());



        ConfigEntry entry = new ConfigEntry(s_date, target, min, max, fan, kp, ki, kd, sample);
        DatabaseHandler DBHandler = dbHandler.getInstance(getActivity().getApplicationContext());
        FragmentMain main = (FragmentMain)getFragmentManager().findFragmentById(R.id.fragMain);
        main.HaltService();
        DBHandler.addConfigEntry(entry);
        main.StartService();
    }
}
