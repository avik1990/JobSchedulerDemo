package com.app.jobschedulerdemo;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class ExampleJobServices extends JobService {

    boolean jobcancelled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        doBackground(params);
        return true;
    }

    private void doBackground(JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (jobcancelled) {
                    return;
                }

                for (int i = 0; i < 10; i++) {
                    Log.d("Loop", "" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }
                Log.d("Loop", "" + "Job Finished");
            }
        }).start();
        jobFinished(params, false);
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        jobcancelled = true;
        return false;
    }
}
