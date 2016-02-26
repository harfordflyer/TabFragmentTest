package com.example.dhammond1.tabfragmenttest;

/**
 * Created by dhammond1 on 2/25/2016.
 */
public class ConfigEntry {

    int _id;
    String _configDate;
    String _configPit;
    String _configFan;
    String _configMin;
    String _configKp;
    String _configKi;
    String _configKd;

    public ConfigEntry()
    {}

    public ConfigEntry(String date, String pit, String fan, String min, String kp, String ki, String kd)
    {
        _configDate = date;
        _configPit = pit;
        _configFan = fan;
        _configMin = min;
        _configKp = kp;
        _configKi = ki;
        _configKd = kd;
    }

    public ConfigEntry(int id, String date, String pit, String fan, String min, String kp, String ki, String kd)
    {
        _id = id;
        _configDate = date;
        _configPit = pit;
        _configFan = fan;
        _configMin = min;
        _configKp = kp;
        _configKi = ki;
        _configKd = kd;
    }

    public int getID(){ return this._id; }
    public void setID(int id){ this._id = id; }

    public String getDate(){return this._configDate;}
    public void setDate(String date){this._configDate = date;}

    public String getPit() {return this._configPit;}
    public void setPit(String pit){this._configPit = pit;}

    public String getFan() {return this._configFan;}
    public void setFan(String fan){this._configFan = fan;}

    public String getMin() {return this._configMin;}
    public void setMin(String min){this._configMin = min;}


    public String getKP() {return this._configKp;}
    public void setKP(String kp) {this._configKp = kp;}

    public void setKI(String ki){this._configKi = ki;}
    public String getKI() {return this._configKi;}

    public String getKD() {return this._configKd;}
    public void setKD(String kd) {this._configKd = kd;}


}
