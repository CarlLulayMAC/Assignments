package com.example.admin.progressbars.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HandlerUtils {

    private static HandlerUtils instance = null;
    Handler handler;

    private static ThreadPoolExecutor threadPoolExecutor;

    private static final int KEEP_ALIVE_TIME = 1;
    private static TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    public static final int CORE_POOL_SIZE = 3;
    public static final int MAXIMUM_POOL_SIZE = 3;
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private ThreadPoolExecutor timerThreadPool;

    // A queue of Runnables for the image download pool
    private BlockingQueue<Runnable> progressQueue = new LinkedBlockingQueue<Runnable>();


    List<Runnable> runnables = new ArrayList<Runnable>();

    private HandlerUtils() {

    }

    private HandlerUtils(Handler handler, List<Runnable> runThese) //Method overloading
    {
        this.handler = handler;
        this.runnables = runThese;
    }

    public static HandlerUtils with (Handler handler, List<Runnable> tasks)
    {
        if(instance == null)
        {
            instance = new HandlerUtils(handler, tasks);
        }
        instance.setHandler(handler);
        instance.setRunnables(tasks);
        return instance;
    }

    public static HandlerUtils getInstance()
    {
        return instance;
    }

    private HandlerUtils(Handler handler) {
        this.handler = handler;
    }

    private void setHandler(Handler handler) {
        this.handler = handler;
    }

    private void setRunnables(List<Runnable> runnables) {
        this.runnables = runnables;
    }

    public void sendMessage(String key, String msg) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(key, msg);
        message.setData(bundle);
        handler.sendMessage(message);
    }

    public void sendMessage(int id, int currentTime, int totalTime) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.Key.PROGRESS_ID, id);
        bundle.putInt(Constants.Key.PROGRESS_CURRENT, currentTime);
        bundle.putInt(Constants.Key.PROGRESS_TOTAL, totalTime);
        message.setData(bundle);
        handler.sendMessage(message);
    }

    public void executeTasks()
    {
        timerThreadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME,KEEP_ALIVE_TIME_UNIT, progressQueue);
        List<Runnable> temp = new ArrayList<>();
//        temp.add(runnables.get(1));
////        temp.add(runnables.get(2));
//        runnables = temp;
        for (int i = 0; i < runnables.size(); i++) {
            timerThreadPool.execute(runnables.get(i));
        }
    }


//    public void executeTasks(List<Runnable> runnables)
//    {
//        int numCores = Runtime.getRuntime().availableProcessors();
//
//        for (Runnable task: runnables)
//        {
//            progressQueue.add(task);
//        }
//        threadPoolExecutor = new ThreadPoolExecutor(numCores, 3, 20,
//                TimeUnit.SECONDS, progressQueue);
//
//
////        int shortestRunTime = 0;
//        for (Runnable task: runnables)
//        {
////            if (shortestRunTime == 0 || shortestRunTime > ((ProgressRunnable)task).progressTotalTime)
////                shortestRunTime = ((ProgressRunnable)task).progressTotalTime;
//            threadPoolExecutor.execute(task);
//        }
//    }
}
