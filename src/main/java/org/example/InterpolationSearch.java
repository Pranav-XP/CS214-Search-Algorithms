package org.example;

import java.util.List;

public class InterpolationSearch<T extends Comparable<T>> implements Runnable {
    private List<T> list;
    private T target;
    private volatile long startTime;
    private volatile long endTime;
    private volatile long executionTime;
    private int counter;

    //Constructors
    public InterpolationSearch(List<T> list, T target) {
        this.list = list;
        this.target = target;
    }

    public InterpolationSearch(List<T> list) {
        this.list = list;
    }

    @Override
    public void run() {

    }
    public int interpolationSearch(List<T> list, T target) {

        int left = 0;
        int right = list.size() - 1;
        int pos;
        if(left<=right && target.compareTo(list.get(left))>=0 && target.compareTo(list.get(right))<=0){
            pos = left + (((right - left) / (list.get(right).compareTo(list.get(left)))) * (target.compareTo(list.get(left))));
            return pos;
        }else {
            return -1;
        }
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
