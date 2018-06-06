package com.example.admin.progressbars.Tasks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.admin.progressbars.utils.Constants;
import com.example.admin.progressbars.utils.HandlerUtils;

import java.util.ArrayList;

public class ProgressRunnable implements Runnable {
    Handler handler;
    // TODO: do these need to be public?
    public int progressTotalTime;
    public int progressCurrentTime;

    public ProgressRunnable(Handler handler, int progressTotalTime) {
        this.handler = handler;
        this.progressTotalTime = progressTotalTime;
        this.progressCurrentTime = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
//            for (int i = 0; i < this.progressTotal; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.progressCurrentTime ++;
//            Message message = new Message();
//            Bundle bundle = new Bundle();
//            bundle.putInt(Constants.Key.PROGRESS_CURRENT, this.progressCurrentTime);
//            message.setData(bundle);
//            handler.sendMessage(message);
            HandlerUtils.with(handler).sendMessage("Runnable " + this.progressTotalTime + " Count:" + progressCurrentTime);
        }

    }
}
