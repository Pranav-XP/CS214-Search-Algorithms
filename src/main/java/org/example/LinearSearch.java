package org.example;

import java.util.List;

public class LinearSearch<T extends Comparable<T>> implements Runnable{
    private List<T> list;
    private T target;
    private volatile long startTime;
    private volatile long endTime;
    private volatile long executionTime;

    private int counter;

    public LinearSearch(List<T> list, T target) {
        this.list = list;
        this.target = target;
    }

    public LinearSearch(List<T> list){
        this.list=list;
    }

    @Override
    public void run() {
        startTime= System.currentTimeMillis();
        int index = linearSearch(list,target);
        endTime = System.currentTimeMillis();
        executionTime = endTime-startTime;
        System.out.println(Thread.currentThread().getName()+":File "+index+" found in "+executionTime+" ms\n");
    }

    //Generic Linear Search algorithm which searches for any User define datatype for both linked list and array list
    public int linearSearch(List<T> list,T target){
        counter = 0;
        for(int i = 0; i< list.size();i++){
            counter++;
            if (list.get(i).compareTo(target) == 0){
                setCounter(counter);
                return i;
            }
        }

        return -1;
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

    public synchronized long getExecutionTime() {
        return executionTime;
    }

    public synchronized void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
