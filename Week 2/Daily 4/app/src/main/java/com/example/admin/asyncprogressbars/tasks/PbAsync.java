package com.example.admin.asyncprogressbars.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PbAsync extends AsyncTask<String, Integer, String> {
    TextView tvTimeRemaining;
    ProgressBar pbProgress;
    int currTime;
    int totalTime;

    public PbAsync(TextView tvTimeRemaining, ProgressBar pbProgress, int totalTime) {
        this.tvTimeRemaining = tvTimeRemaining;
        this.pbProgress = pbProgress;
        this.totalTime = totalTime;
        this.currTime = 0;
    }

    @Override
    protected String doInBackground(String... strings) {
        for (int i = 0; i < totalTime; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currTime++;
            publishProgress(currTime);
        }
        return "Jobs done";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        tvTimeRemaining.setText("" + (totalTime - currTime));
        pbProgress.setProgress(currTime);
    }
}

