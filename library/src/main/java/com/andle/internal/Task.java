/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.andle.internal;

import java.util.concurrent.Callable;

/**
 * Task unit bean
 *
 * Created by lichen
 */
public class Task<T> {

    public Callable<T> callable;
    public TaskCallback<T> callback;

    public Task(Callable<T> callable, TaskCallback<T> callback) {
        this.callable = callable;
        this.callback = callback;
    }

    public interface TaskCallback<T> {
        void onSuccess(T t);
        void onError(int error, String msg);
    }
}
