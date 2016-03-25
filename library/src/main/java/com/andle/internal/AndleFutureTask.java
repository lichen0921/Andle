/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.andle.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.andle.Andle;

/**
 * FutureTask
 *
 * Created by lichen13
 */
public class AndleFutureTask<T> extends FutureTask<T> {

    private Callable<T> mCallable;
    private Task.TaskCallback<T> mCallback;
    private static final int TIME_OUT = 5000;

    public AndleFutureTask(Callable<T> callable, Task.TaskCallback<T> callback) {
        this(callable);
        mCallable = callable;
        mCallback = callback;
    }

    public AndleFutureTask(Callable<T> callable) {
        super(callable);
    }

    public AndleFutureTask(Runnable runnable, T result) {
        super(runnable, result);
    }

    @Override
    protected void done() {
        if (mCallback != null) {
            try {
                T result = get(TIME_OUT, TimeUnit.MILLISECONDS);
                mCallback.onSuccess(result);
                Thread.sleep(1000);
                Andle.getInstance().execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
                mCallback.onError(0, "InterruptedException");
            } catch (ExecutionException e) {
                e.printStackTrace();
                mCallback.onError(1, "ExecutionException");
            } catch (TimeoutException e) {
                e.printStackTrace();
                mCallback.onError(2, "time out");
            } finally {

            }
        }
    }

}
