package com.example.admin.progressbars.Tasks;

import android.os.Handler;
import com.example.admin.progressbars.utils.HandlerUtils;

public class ProgressRunnable implements Runnable {
    Handler handler;
    int progressTotalTime;
    int progressCurrentTime;
    int id;

    public ProgressRunnable(Handler handler, int progressTotalTime, int id) {
        this.id = id;
        this.handler = handler;
        this.progressTotalTime = progressTotalTime;
        this.progressCurrentTime = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.progressTotalTime; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.progressCurrentTime++;
            // TODO Pass back something like Thread.currentThread().getName()
            HandlerUtils.getInstance().sendMessage(id, progressCurrentTime, progressTotalTime);
        }

    }
}
