package org.example;

import java.util.List;

public class InterpolationSearch<T extends Comparable<T>> implements Runnable {
    private List<T> list;
    private T target;
    private volatile long startTime;
    private volatile long endTime;
    private volatile long executionTime;
    private int counter;
    private int left;
    private int right;

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
        startTime=System.currentTimeMillis();
        int index =interpolationSearch(list,target,0, list.size()-1);
        endTime=System.currentTimeMillis();
        executionTime=endTime-startTime;
        System.out.println(Thread.currentThread().getName()+":File "+index+" found in "+executionTime+" ms after "
                +counter+" comparisons\n");
    }

    //Interpolation Search Algorithm
    public int interpolationSearch(List<T> list, T target,int left, int right) {
        counter=0;
        int pos;
        if(left<=right && target.compareTo(list.get(left))>=0 && target.compareTo(list.get(right))<=0) {
            //Probing the array which is uniformly distributed to find Target
            //Formula is derived from y=mx+c whereby array list is linearly distributed
            // pos = lo + (((hi - lo) / (arr[hi] - arr[lo])) * (x - arr[lo]))
            pos = left + ( ((right-left) * (target.compareTo(list.get(left))))
                                /(list.get(right).compareTo(list.get(left)))    );

            counter++;
            //Checking if Target Found
            if (list.get(pos).compareTo(target)==0) {
                return pos;
            }else if (list.get(pos).compareTo(target)<0) {
                // If target is larger, target is in right sub array
                return interpolationSearch(list, target, pos + 1, right);
            }else{
                // If target is smaller, target is in left sub array
                return interpolationSearch(list,target,left,pos-1);
            }
        }

        return -1; //Element not found
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

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}
