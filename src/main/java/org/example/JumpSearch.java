package org.example;

import java.util.List;

public class JumpSearch<T extends Comparable<T>> implements Runnable{
    private List<T> list;
    private T target;
    private volatile long startTime;
    private volatile long endTime;
    private volatile long executionTime;
    private int counter;

    public JumpSearch(List<T> list) {
        this.list = list;
    }

    public JumpSearch(List<T> list, T target) {
        this.list = list;
        this.target = target;
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        int index=jumpSearch(list,target);
        endTime = System.currentTimeMillis();
        executionTime = endTime-startTime;
        System.out.println(Thread.currentThread().getName()+":File "+index+" found in "+executionTime+" ms after "
                +counter+" comparisons\n");
    }

    //JumpSearch Algorithm
    public int jumpSearch(List<T> list, T target) {
        counter=0;
        int n = list.size();
        int step = (int)Math.floor(Math.sqrt(n));
        int prev = 0;

        for (int minStep = Math.min(step,n)-1;list.get(minStep).compareTo(target)<0;minStep=Math.min(step,n)-1){
            prev = step;
            step += (int)Math.floor(Math.sqrt(n));

            if (prev >= n) {
                return -1;
            }
            counter++;
        }

        //Linear search within block
        while (list.get(prev).compareTo(target)<0) {
            prev++;

            //If we reach end of block or array
            if (prev == Math.min(step, n)) {
                return -1;
            }
            counter++;
        }

        //if element is found
        if (list.get(prev).compareTo(target)==0) {
            return prev;
        }

        return -1; // Element not found
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
