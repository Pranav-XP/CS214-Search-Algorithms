package org.example;

import java.util.List;

public class BinarySearch<T extends Comparable<T>> implements Runnable {
    private List<T> list;
    private T target;
    private volatile long startTime;
    private volatile long endTime;
    private volatile long executionTime;
    private int counter;

    public BinarySearch(List<T> list) {
        this.list = list;
    }

    public BinarySearch(List<T> list, T target) {
        this.list = list;
        this.target = target;
    }

    public int binarySearch(List<T> list, T key) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparisonResult = list.get(mid).compareTo(key);

            if (comparisonResult == 0) {
                return mid;
            } else if (comparisonResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // Element not found
    }

    @Override
    public void run() {
        startTime=System.currentTimeMillis();
        int index = binarySearch(list,target);
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        System.out.println(Thread.currentThread().getName()+":File "+index+" found in "+executionTime+" ms\n");
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
