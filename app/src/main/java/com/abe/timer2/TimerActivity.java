package com.abe.timer2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {
    private Timer timer = new Timer(){
        private CountUpTimerC timerTask;

        @Override
        public void schedule(TimerTask task, long delay, long period) {
            super.schedule(task, delay, period);
            this.timerTask = (CountUpTimerC)task;
        }

        @Override
        public void cancel() {
            super.cancel();
            total = timerTask.getTotal();

        }
    };
    private long total,cCount,dCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_timer);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ToggleButton cTimer = (ToggleButton) findViewById(R.id.cTimer);
        ToggleButton dTimer = (ToggleButton) findViewById(R.id.dTimer);
        ToggleButton startBtn = (ToggleButton) findViewById(R.id.startButton);


        dTimer.setEnabled(false);
        dCount = 50000;
        cCount = 0;
        total = 0;
        final CountUpTimerC countUpTimerC = new CountUpTimerC(TimerActivity.this, total, cCount);

        startBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    if(cCount >= 1){
                        timer.cancel();
                        timer = new Timer();
                    }

                    //Toast.makeText(TimerActivity.this, "enabled", Toast.LENGTH_SHORT).show();
                    timer.schedule(new CountUpTimerC(TimerActivity.this, total, cCount), 0, 100);
                } else {
                    // The toggle is disabled
                    timer.cancel();
                    timer = new Timer();
                    //Toast.makeText(TimerActivity.this, "disabled", Toast.LENGTH_SHORT).show();
                    timer.schedule(new CountUpTimerD(TimerActivity.this, total, dCount),0,100);
                    cCount = 1;
                }
            }
        });

        //CountUpTimerC cut = new CountUpTimerC(this);



        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    public void test(){
        timer.schedule(new CountUpTimerC(TimerActivity.this, total, cCount),0,100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
