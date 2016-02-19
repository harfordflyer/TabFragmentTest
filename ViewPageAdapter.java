package com.example.dhammond1.tabfragmenttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
/**
 * Created by dhammond1 on 2/14/2016.
 */
public class ViewPageAdapter  extends  FragmentStatePagerAdapter{
    int numOfTabs;

    public ViewPageAdapter(FragmentManager fm, int NumOfTabs){
        super(fm);
        this.numOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position){
        switch(position){
            case 0:
                FragmentMain tabMain = new FragmentMain();
                return tabMain;

            case 1:
                FragmentConfiguration tabConfig = new FragmentConfiguration();
                return tabConfig;

            case 2:
                FragmentGraph tabGraph = new FragmentGraph();
                return tabGraph;

            default:
                return null;


        }
    }

    @Override
    public int getCount(){
        return numOfTabs;
    }
}
