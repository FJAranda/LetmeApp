package com.example.letmeapp.service;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;

public class RequestService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
