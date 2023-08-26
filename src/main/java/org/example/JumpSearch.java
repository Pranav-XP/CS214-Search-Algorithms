package org.example;

import java.util.List;

public class JumpSearch<T> implements Runnable{
    private List<T> list;
    private int target;
    private volatile long startTime;
    private volatile long endTime;
    private volatile long executionTime;
    private int counter;

    public JumpSearch(List<T> list) {
        this.list = list;
    }

    public JumpSearch(List<T> list, int target) {
        this.list = list;
        this.target = target;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ " completed\n");
    }

    //JumpSearch Algorithm

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
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
