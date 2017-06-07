package com.abe.timer2;

import android.content.Context;
import android.widget.ToggleButton;

/**
 * Created by abe on 2017/06/07.
 */

public class CountUpTimerD extends CountUpTimerC {

    public CountUpTimerD(Context context, long total, long count){
        super(context,total, count);
        timerButton = (ToggleButton)((TimerActivity)context).findViewById(R.id.dTimer);
    }

    @Override
    public void run() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                count();
                long p = (total*100)/(count+total);
                percentageText.setText(String.format("%d %%",p));
            }
        });

    }
}
