package com.abe.timer2;

import android.content.Context;
import android.os.Handler;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by abe on 17/06/02.
 */

public class CountUpTimerC extends TimerTask{
    protected Context context;
    protected TextView percentageText, totalText;
    protected ToggleButton timerButton;
    protected long total, count;
    protected Handler handler = new Handler();



    public CountUpTimerC(Context context, long total, long count){
        this.context = context;
        this.total = (long)((int)((total - count)/10))*10;
        this.count = count;
        timerButton = (ToggleButton)((TimerActivity)context).findViewById(R.id.cTimer);
        percentageText = (TextView)((TimerActivity)context).findViewById(R.id.percentage);
        totalText = (TextView)((TimerActivity)context).findViewById(R.id.total);
    }


    @Override
    public void run() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                count();
                long p = (count*100)/(count+total);
                percentageText.setText(String.format("%d %%",p));
            }
        });


    }

    public void count(){
        count++;
        long hh = count/36000;
        long mm = (count%36000)/600;
        long ss = (count-36000*hh-600*mm)/10;
        long th = (total+count)/36000;
        long tm = ((total+count)%36000)/600;
        long ts = ((total+count)-36000*th-600*tm)/10;
        timerButton.setText(String.format("%02d:%02d:%02d",hh,mm,ss));
        totalText.setText(String.format("%02d:%02d:%02d",th,tm,ts));
    }

    public void setCount(){
        ((TimerActivity)context).setTotal(total);
        ((TimerActivity)context).setcCount(count);
    }


}