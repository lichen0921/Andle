/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.andle.internal;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

/**
 * Queue of Task
 * Created by lichen13
 */
public class TaskQueue<T> {

    private LinkedList<T> mQueue;
    private BlockingQueue<T> mBQueue;

    public TaskQueue() {
        init();
    }

    private void init() {
        mQueue = new LinkedList<T>();
    }

    public boolean add(T t) {
        return mQueue.add(t);
    }

    public void addFirst(T t) {
        mQueue.addFirst(t);
    }

    public T get() {
        return mQueue.poll();
    }

    public T getLast() {
        return mQueue.pollLast();
    }

    public T getFirst() {
        return mQueue.pollFirst();
    }

    public T removeFirst() {
        return mQueue.removeFirst();
    }

    public T removeLast() {
        return mQueue.removeLast();
    }

    public void clear() {
        mQueue.clear();
    }

    public boolean removeRunnable(Runnable runnable) {
        return mQueue.remove(runnable);
    }
}
