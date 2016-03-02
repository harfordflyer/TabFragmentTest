package com.example.dhammond1.tabfragmenttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Chronometer;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//PID algorithm from http://www.ee.ucl.ac.uk/~mflanaga/java/index.html
//import flanagan.control.PropIntDeriv;
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
    private static TextView tv_targetTemp;
    DatabaseHandler dbHandler;
    ScheduledExecutorService databaseReadTask;
    Future<?> scheduleFuture;
    private static final String CONFIG_NAME = "AppConfig";

    Chronometer chrono;
    long mLastStopTime = 0;

    public void HaltService()
    {
        scheduleFuture.cancel(true);
    }

    public void StartService()
    {
        scheduleFuture = databaseReadTask.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                DatabaseHandler DBHandler = dbHandler.getInstance(getContext());
                TemperatureEntry entry = DBHandler.getLastEntry();
                Bundle bundle = new Bundle();
                bundle.putSerializable("entry", entry);
                Message message = new Message();
                message.setData(bundle);
                runnableCallback.sendMessage(message);

            }
        }, 10000, 10000, TimeUnit.MILLISECONDS);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View thisView;
        Button btnStart;
        Button btnStop;


        thisView = inflater.inflate(R.layout.fragment_main, container, false);

        tv_targetTemp = (TextView)thisView.findViewById(R.id.txt_targetTemp);
        tv_PitText = (TextView)thisView.findViewById(R.id.tx_tempPit);
        tv_MeatText = (TextView)thisView.findViewById(R.id.tx_tempMeat);
        tv_PitText.setText("");
        tv_MeatText.setText("");

        btnStart = (Button) thisView.findViewById(R.id.chronStart);
        btnStop = (Button) thisView.findViewById(R.id.chronStop);
        chrono = (Chronometer)thisView.findViewById(R.id.chronometer);
        Toast.makeText(getActivity(),"fragment main",Toast.LENGTH_LONG).show();

        SharedPreferences prefs = getContext().getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
        String restoredText = prefs.getString("targetPit", null);
        if(restoredText == null)
        {
            tv_targetTemp.setText("250", TextView.BufferType.EDITABLE);
        }
        else
        {
            tv_targetTemp.setText(restoredText, TextView.BufferType.EDITABLE);
        }

        //btn_setPit = (Button)thisView.findViewById(R.id.btn_setPit);



        /*Intent i = new Intent(getActivity(), DataService.class);
        i.putExtra("temps", new String[]{"1000","2000"});
        getActivity().startService(i);

        databaseReadTask = Executors.newScheduledThreadPool(5);

        StartService();*/

       /* scheduleFuture = databaseReadTask.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                DatabaseHandler DBHandler = dbHandler.getInstance(getContext());
                TemperatureEntry entry = DBHandler.getLastEntry();
                Bundle bundle = new Bundle();
                bundle.putSerializable("entry", entry);
                Message message = new Message();
                message.setData(bundle);
                runnableCallback.sendMessage(message);

            }
        }, 10000, 10000, TimeUnit.MILLISECONDS);*/


        //need to set the target temp from the preferences
       /* btn_setPit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                s_targetPitTemp = String.valueOf(ed_PitEditBox.getText());
            }
        });*/



        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLastStopTime == 0) {
                    chrono.setBase(SystemClock.elapsedRealtime());
                } else {
                    long intervalOnPause = (SystemClock.elapsedRealtime() - mLastStopTime);
                    chrono.setBase(chrono.getBase() + intervalOnPause);
                }
                chrono.start();
                //start sampling data
                Intent i = new Intent(getActivity(), DataService.class);
                i.putExtra("temps", new String[]{"1000", "2000"});
                getActivity().startService(i);

                databaseReadTask = Executors.newScheduledThreadPool(5);
                StartService();
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

    public void StopService()
    {

    }

    private static Handler runnableCallback = new Handler()
    {
        public void handleMessage(Message msg)
        {
            Bundle bundle = msg.getData();
            TemperatureEntry entry = (TemperatureEntry)bundle.getSerializable("entry");
            tv_PitText.setText(entry.getPitTemp());

            tv_MeatText.setText(entry.getMeatTemp());
        }
    };

    /*private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            TemperatureEntry entry = (TemperatureEntry)intent.getSerializableExtra("results");
            Log.d("entry meat from intent", entry.getMeatTemp());
            Log.d("entry pit from intext", entry.getPitTemp());
        }
    };*/

}
