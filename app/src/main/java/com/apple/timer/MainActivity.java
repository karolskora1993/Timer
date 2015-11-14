package com.apple.timer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {
    private int seconds;
    private boolean isRunning;
    private boolean wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds");
            isRunning=savedInstanceState.getBoolean("isRunning");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
}
    @Override
    public void onSaveInstanceState(Bundle bundle){
        bundle.putInt("seconds", seconds);
        bundle.putBoolean("isRunning", isRunning);
        bundle.putBoolean("wasRunning", wasRunning);
    }
    public void onButtonClick(View view){
        int id=view.getId();
        switch(id){
            case(R.id.buttonStart):
                isRunning=true;
                break;
            case(R.id.buttonStop):
                isRunning=false;
                break;
            case(R.id.buttonReset):
                seconds=0;
                break;

        }

    }
    private void runTimer(){
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int minutes=(seconds%3600)/60;
                int hours=seconds/3600;
                int sec=seconds%60;
                String text=String.format("%d:%02d:%02d",hours,minutes,sec);
                TextView textView=(TextView)findViewById(R.id.textView);
                textView.setText(text);
                if(isRunning)
                    seconds++;
                handler.postDelayed(this,1000);
            }
        });
    }
}

