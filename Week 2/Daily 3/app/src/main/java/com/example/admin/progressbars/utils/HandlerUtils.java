package com.example.admin.progressbars.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.admin.progressbars.Tasks.ProgressRunnable;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HandlerUtils {

    private static HandlerUtils instance = null;
    Handler handler;

    private static ThreadPoolExecutor threadPoolExecutor;
    private static BlockingQueue<Runnable> progressQueue = new LinkedBlockingQueue<Runnable>();
//    private List<Runnable> runnables; //TODO Clean this

    private HandlerUtils() {

    }

    public static HandlerUtils with(Handler handler) {

        if (instance == null) {
            instance = new HandlerUtils(handler);
        }
        instance.setHandler(handler);
        return instance;
    }

    //TODO clean this
//    public void setRunnables(List<Runnable> runnables){
//        this.runnables = runnables;
//    }

    private HandlerUtils(Handler handler) {
        this.handler = handler;
    }

    private void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void sendMessage(String MESSAGE) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("data", MESSAGE);
        message.setData(bundle);
        handler.sendMessage(message);
    }

    public void executeTasks(List<Runnable> runnables)
    {
        int numCores = Runtime.getRuntime().availableProcessors();

        for (Runnable task: runnables)
        {
            progressQueue.add(task);
        }
        threadPoolExecutor = new ThreadPoolExecutor(numCores, 3, 20,
                TimeUnit.SECONDS, progressQueue);


//        int shortestRunTime = 0;
        for (Runnable task: runnables)
        {
//            if (shortestRunTime == 0 || shortestRunTime > ((ProgressRunnable)task).progressTotalTime)
//                shortestRunTime = ((ProgressRunnable)task).progressTotalTime;
            threadPoolExecutor.execute(task);
        }
    }

}
