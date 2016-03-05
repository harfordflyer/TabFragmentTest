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

    public String s_date;

    private EditText ed_currentKp;
    private EditText ed_currentKi;
    private EditText ed_currentKd;

    //private EditText ed_targetPit;
    private EditText ed_minTemp;
    private EditText ed_maxTemp;
    private EditText ed_fanSpeed;
    private EditText ed_sampleTime;

    private static final String CONFIG_NAME = "AppConfig";



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



        return thisView;
    }

    @Override
    public void onClick(View v) {


        SaveConfigurations();
    }

    public void RestoreConfigurations()
    {
        SharedPreferences config = getContext().getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);

        String restoredText = config.getString("minTemp", null);
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
        //editor.putString("targetPit", ed_targetPit.getText().toString());
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
