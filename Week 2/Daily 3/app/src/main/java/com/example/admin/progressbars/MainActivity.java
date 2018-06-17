package com.example.admin.progressbars;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.admin.progressbars.Tasks.ProgressRunnable;
import com.example.admin.progressbars.utils.Constants;
import com.example.admin.progressbars.utils.HandlerUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    private Handler handler;
    private ProgressBar progBar1;
    private ProgressBar progBar2;
    private ProgressBar progBar3;
    private ProgressBar progBar4;
    private TextView threadLabel1;
    private TextView threadLabel2;
    private TextView threadLabel3;
    private TextView threadLabel4;
    private TextView tvTime1;
    private TextView tvTime2;
    private TextView tvTime3;
    private TextView tvTime4;
    public static final int ID_1 = 1;
    public static final int ID_2 = 2;
    public static final int ID_3 = 3;
    public static final int ID_4 = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(this);
        progBar1 = findViewById(R.id.progressBar1);
        progBar2 = findViewById(R.id.progressBar2);
        progBar3 = findViewById(R.id.progressBar3);
        progBar4 = findViewById(R.id.progressBar4);
        threadLabel1 = findViewById(R.id.tvThreadLabel1);
        threadLabel2 = findViewById(R.id.tvThreadLabel2);
        threadLabel3 = findViewById(R.id.tvThreadLabel3);
        threadLabel4 = findViewById(R.id.tvThreadLabel4);
        tvTime1 = findViewById(R.id.tvTimeLabel1);
        tvTime2 = findViewById(R.id.tvTimeLabel2);
        tvTime3 = findViewById(R.id.tvTimeLabel3);
        tvTime4 = findViewById(R.id.tvTimeLabel4);
    }

    public void onStartTasks(View view) {
        progBar1.setProgress(0);
        progBar2.setProgress(0);
        progBar3.setProgress(0);
        progBar4.setProgress(0);
        int time1 = (int)(Math.random() * 20) + 5;
        int time2 = (int)(Math.random() * 20) + 5;
        int time3 = (int)(Math.random() * 20) + 5;
        int time4 = (int)(Math.random() * 20) + 5;
        progBar1.setMax(time1);
        progBar2.setMax(time2);
        progBar3.setMax(time3);
        progBar4.setMax(time4);
        ProgressRunnable runnable1 = new ProgressRunnable(handler, time1, ID_1);
        ProgressRunnable runnable2 = new ProgressRunnable(handler, time2, ID_2);
        ProgressRunnable runnable3 = new ProgressRunnable(handler, time3, ID_3);
        ProgressRunnable runnable4 = new ProgressRunnable(handler, time4, ID_4);
        List<Runnable> runnables = new ArrayList<Runnable>();
        runnables.add(runnable1);
        runnables.add(runnable2);
        runnables.add(runnable3);
        runnables.add(runnable4);
        HandlerUtils.with(handler, runnables).executeTasks();
    }

    @Override
    public boolean handleMessage(Message msg) {
        int currentTime = msg.getData().getInt(Constants.Key.PROGRESS_CURRENT);
        int totalTime = msg.getData().getInt(Constants.Key.PROGRESS_TOTAL);
        int remainingTime = totalTime - currentTime;
        int id = msg.getData().getInt(Constants.Key.PROGRESS_ID);
        switch (id){
            case ID_1 : {
                tvTime1.setText("" + remainingTime);
                progBar1.setProgress(currentTime);
                break;
            }
            case ID_2 : {
                tvTime2.setText("" + remainingTime);
                progBar2.setProgress(currentTime);
                break;
            }
            case ID_3 : {
                tvTime3.setText("" + remainingTime);
                progBar3.setProgress(currentTime);
                break;
            }
            case ID_4 : {
                tvTime4.setText("" + remainingTime);
                progBar4.setProgress(currentTime);
                break;
            }
        }
        return false;
    }
}