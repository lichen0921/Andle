/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.andle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import com.andle.internal.AndleFutureTask;
import com.andle.internal.Task;
import com.andle.internal.TaskQueue;

public class Andle {

    private static volatile Andle andle;
    private TaskQueue<Task> mQueue;
    private ExecutorService mExecutor;

    public Andle() {
        init();
    }

    private void init() {
        mQueue = new TaskQueue<Task>();
    }

    public static Andle getInstance() {
        if (null == andle) {
            synchronized (Andle.class) {
                if (null == andle) {
                    andle = new Andle();
                    andle.mQueue = new TaskQueue<Task>();
                    andle.mExecutor = Executors.newSingleThreadExecutor();
                }
            }
        }
        return andle;
    }

    public void add(Task order) {
        mQueue.add(order);
    }

    public void execute() {
        Task task = mQueue.get();
        if (task == null) {
            //            mExecutor.shutdown();
            return;
        }
        FutureTask<Boolean> futureTask = new AndleFutureTask<Boolean>(task.callable, task.callback);
        mExecutor.execute(futureTask);
    }
}
