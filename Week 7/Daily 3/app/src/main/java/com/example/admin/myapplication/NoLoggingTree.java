package com.example.admin.myapplication;

import timber.log.Timber;

public class NoLoggingTree extends Timber.Tree {
    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        // do nothing
    }
}
