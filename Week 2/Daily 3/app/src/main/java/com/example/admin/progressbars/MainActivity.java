package com.example.admin.progressbars;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.admin.progressbars.Tasks.ProgressRunnable;
import com.example.admin.progressbars.utils.Constants;
import com.example.admin.progressbars.utils.HandlerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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
    private TextView time1;
    private TextView time2;
    private TextView time3;
    private TextView time4;

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
        time1 = findViewById(R.id.tvTimeLabel1);
        time2 = findViewById(R.id.tvTimeLabel2);
        time3 = findViewById(R.id.tvTimeLabel3);
        time4 = findViewById(R.id.tvTimeLabel4);
    }

    public void onStartTasks(View view) {
        int time1 = (int)Math.random() * 30 + 5;
        ProgressRunnable runnable1 = new ProgressRunnable(handler, 111);
        ProgressRunnable runnable2 = new ProgressRunnable(handler, 222);
        ProgressRunnable runnable3 = new ProgressRunnable(handler, 333);
        ProgressRunnable runnable4 = new ProgressRunnable(handler, 444);
        List<Runnable> runnables = new ArrayList<Runnable>();
//        BlockingQueue<Runnable> progressQueue = new LinkedBlockingQueue<Runnable>();
//        progressQueue.add(runnable1);
//        progressQueue.add(runnable2);
//        progressQueue.add(runnable3);
//        progressQueue.add(runnable4);
        runnables.add(runnable1);
        runnables.add(runnable2);
        runnables.add(runnable3);
        runnables.add(runnable4);
//        HandlerUtils.with(handler).executeTasks(runnables);
        HandlerUtils.with(handler).executeTasks(runnables);
    }

    @Override
    public boolean handleMessage(Message msg) {
        int progress = msg.getData().getInt(Constants.Key.PROGRESS_CURRENT);
//        progBar1.setProgress(progress);
        Log.d("MAINTAG", msg.getData().getString("data"));
        return false;
    }
}
