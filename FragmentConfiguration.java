package com.example.dhammond1.tabfragmenttest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Set;

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

    private static final String CONFIG_NAME = "AppConfig";
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
        RestoreConfigurations();

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

       /* String target = String.valueOf(ed_targetPit.getText());
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
        main.StartService();*/
        SaveConfigurations();
    }

    public void RestoreConfigurations()
    {
        SharedPreferences config = getContext().getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
        String restoredText = config.getString("targetPit",null);
        SetTextBox(ed_targetPit, restoredText, "250");

        restoredText = config.getString("minTemp", null);
        SetTextBox(ed_minTemp, restoredText, "230");

        restoredText = config.getString("maxTemp", null);
        SetTextBox(ed_maxTemp, restoredText, "260");

        restoredText = config.getString("fanSpeed",null);
        SetTextBox(ed_fanSpeed, restoredText, "100");

        restoredText = config.getString("kp", null);
        SetTextBox(ed_currentKp, restoredText, "5");

        restoredText = config.getString("ki", null);
        SetTextBox(ed_currentKi, restoredText, "1");

        restoredText = config.getString("kd", null);
        SetTextBox(ed_currentKd, restoredText, "1");

        restoredText = config.getString("sampleTime", null);
        SetTextBox(ed_sampleTime, restoredText, "30");


    }

    public void SetTextBox(TextView view, String text, String defaultText)
    {
        if(text == null)
        {
            view.setText(defaultText, TextView.BufferType.EDITABLE);
        }
        else
        {
            view.setText(text, TextView.BufferType.EDITABLE);
        }
    }

    public void SaveConfigurations()
    {
        SharedPreferences.Editor editor = getContext().getSharedPreferences(CONFIG_NAME,Context.MODE_PRIVATE).edit();
        editor.putString("targetPit", ed_targetPit.getText().toString());
        editor.putString("minTemp", ed_minTemp.getText().toString());
        editor.putString("maxTemp", ed_maxTemp.getText().toString());
        editor.putString("fanSpeed", ed_fanSpeed.getText().toString());
        editor.putString("kp", ed_currentKp.getText().toString());
        editor.putString("ki", ed_currentKi.getText().toString());
        editor.putString("kd", ed_currentKd.getText().toString());
        editor.putString("sampleTime", ed_sampleTime.getText().toString());
        editor.apply();
    }
}
