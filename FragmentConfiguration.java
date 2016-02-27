package com.example.dhammond1.tabfragmenttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by dhammond1 on 2/14/2016.
 */
public class FragmentConfiguration extends Fragment{

    private String s_currentKp;
    private String s_currentKi;
    private String s_currentKd;

    private EditText ed_currentKp;
    private EditText ed_currentKi;
    private EditText ed_currentKd;
    private CheckBox chk_config;
    DatabaseHandler dbHandler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View thisView;
        Button btn_Kp;
        Button btn_Ki;
        Button btn_Kd;

        thisView = inflater.inflate(R.layout.fragment_configuration, container, false);
        ed_currentKp = (EditText)thisView.findViewById(R.id.ed_Kp);
        ed_currentKi = (EditText)thisView.findViewById(R.id.ed_Ki);
        ed_currentKd = (EditText)thisView.findViewById(R.id.ed_Kd);
        chk_config = (CheckBox)thisView.findViewById(R.id.ckbx_SaveConfig);
        //btn_Kp = (Button)thisView.findViewById(R.id.btn_setKp);
        //btn_Ki = (Button)thisView.findViewById(R.id.btn_setKi);
        //btn_Kd = (Button)thisView.findViewById(R.id.btn_setKd);

        return thisView;
    }
}
