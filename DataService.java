package com.example.dhammond1.tabfragmenttest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;
import java.lang.Runnable;
import android.os.SystemClock;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by dhammond1 on 2/12/2016.
 */
public class DataService extends Service {

    private final IBinder mBinder = new MyBinder();
    public TemperatureEntry sampleEntry = new TemperatureEntry(null, null, null, null);
    DatabaseHandler dbHandler;
    Calendar c = Calendar.getInstance();

    @Override
    public void onCreate()
    {

        Log.d("on create", "service created");
    }


    public int onStartCommand(Intent intent, int flags, int startId){
        //to do ---- something useful
        //for now, write a method that will return a random value of pit and meat temp
        //in a TemperatureEntry class
        String[] temps = intent.getStringArrayExtra("temps");
        sampleEntry.setPitTemp(temps[0]);
        sampleEntry.setMeatTemp(temps[1]);
        Log.d("on start started", "service started?");
        Log.d("intents: ", temps[0]);
        Log.d("intents: ", temps[1]);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                DatabaseHandler DBHandler = dbHandler.getInstance(getApplicationContext());
                boolean exists = DBHandler.DoesDatabaseExist(getApplicationContext(),"temperatureEntry.db");
                Log.d("DBHandler: ", Boolean.toString(exists));
                ReadTemperatures(sampleEntry, DBHandler);
            }
        };

        Thread t = new Thread(r);
        t.start();
        //android.os.Debug.waitForDebugger();
        //ReadTemperatures(sampleEntry);
        // MainActivity.dbHandler.addEntry(sampleEntry);
        return Service.START_STICKY;
    }



    public IBinder onBind(Intent intent){
        //to do for communication return IBinder implementation
        return null;
    }

    public class MyBinder extends Binder {
        DataService getService(){
            return DataService.this;
        }
    }

    public TemperatureEntry GetTemperatureEntry()
    {
        return sampleEntry;
    }

    private void ReadTemperatures(TemperatureEntry sample, DatabaseHandler db)
    {
        Random rand = new Random();
        for(int i = 0; i < 10; i++)
        {
            //SystemClock.sleep(2000);
            int pit = rand.nextInt(240) + 20;
            rand = new Random();
            int meat = rand.nextInt(220) + 60;
            sample.setPitTemp(Integer.toString(pit));
            sample.setMeatTemp(Integer.toString(meat));
            Log.d("pit temp: ", sample.getPitTemp());
            Log.d("meat temp: ", sample.getMeatTemp());
            Log.d("date: ", DatabaseHandler.GetDateTime.GetDate(c));
            Log.d("time: ", DatabaseHandler.GetDateTime.GetTime(c));
            db.addEntry(new TemperatureEntry(DatabaseHandler.GetDateTime.GetDate(c),
                    DatabaseHandler.GetDateTime.GetTime(c), sample.getPitTemp(), sample.getMeatTemp()));
        }

    }
}
