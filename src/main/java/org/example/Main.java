/*GROUP MEMBERS
* S11171153 Pranav Chand
* Chen
* Aryan
* */

package org.example;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;


import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;

public class Main {
    public static void main(String[] args) {
        String filePath = Main.class.getClassLoader().getResource("Article.csv").getPath();
        System.out.println(filePath);

        //Reading files and instantiating data structures.
        List<Article> articleLinkedList = new LinkedList<>(readFile(filePath));
        List<Article> articleArrayList = new ArrayList<>(readFile(filePath));

        //ASSIGNMENT QUESTION 3
        //Creating 30 threads for each algorithm and executing simultaneously
        //Threads are stored in array list of threads and started at the same time
        //Times are stored in array list to perform analysis of best,worst and average time taken.

        //Data structure to store execution times for analysis
        List<List<Long>> allExecutionTimes = null;
        List<Long> executionTimeLSA = new ArrayList<>();
        List<Long> executionTimeLSL = new ArrayList<>();
        List<Long> executionTimeBSA = new ArrayList<>();
        List<Long> executionTimeBSL = new ArrayList<>();
        List<Long> executionTimeSA = new ArrayList<>();
        List<Long> executionTimeSL = new ArrayList<>();
        List<Long> executionTimeJA = new ArrayList<>();
        List<Long> executionTimeJL = new ArrayList<>();


        LinearSearch<Article> LSA = new LinearSearch<>(articleArrayList);
        LinearSearch<Article> LSL = new LinearSearch<>(articleLinkedList);

        BinarySearch<Article> BSA= new BinarySearch<>(articleArrayList);
        BinarySearch<Article> BSL = new BinarySearch<>(articleLinkedList);



        //TODO: IMPLEMENT BELOW AS FUNCTIONS
        for (int i = 0; i < 3; i++) {
            LSA.setTarget(new Article(generateRandomKey(articleArrayList.size())));
            LSL.setTarget(new Article(generateRandomKey(articleLinkedList.size())));

            BSA.setTarget(new Article(generateRandomKey(articleArrayList.size())));
            BSL.setTarget(new Article(generateRandomKey(articleLinkedList.size())));

            Thread runLSA = new Thread(LSA,"LSA"+i);
            Thread runLSL = new Thread(LSL,"LSL"+i);

            Thread runBSA = new Thread(BSA,"BSA"+i);
            Thread runBSL = new Thread(BSL,"BSL"+i);

            runLSA.start();
            runLSL.start();

            runBSA.start();
            runBSL.start();

            try {
                runLSA.join();
                runLSL.join();
                runBSA.join();
                runBSL.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            executionTimeLSA.add(LSA.getExecutionTime());
            executionTimeLSL.add(LSL.getExecutionTime());
            executionTimeBSA.add(BSA.getExecutionTime());
            executionTimeBSL.add(BSL.getExecutionTime());
        }

        //Starting Multithread operations and performing time analysis
        timeAnalysis("LSA",executionTimeLSA);
        timeAnalysis("LSL",executionTimeLSL);
        timeAnalysis("BSA",executionTimeBSA);
        timeAnalysis("BSL",executionTimeBSL);

    }


    public static List<Article> readFile(String filePath){
        //Method to read the CSV file and return Articles.
        List<Article> articles = new ArrayList<>();
        //Reading file
        try {
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(',')
                    .withEscapeChar('\\')
                    .build();

            CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();
            //Read CSV line by line and store strings as fields in Article class
            String[] nextLine;
            while((nextLine = reader.readNext()) != null){
                int id = Integer.parseInt(nextLine[0]);
                String title = nextLine[1];
                String abs = nextLine[2];
                int csType =Integer.parseInt(nextLine[3]);
                int physicsType = Integer.parseInt(nextLine[4]);
                int mathType= Integer.parseInt(nextLine[5]);
                int statsType= Integer.parseInt(nextLine[6]);
                int qbType= Integer.parseInt(nextLine[7]);
                int qfType=Integer.parseInt(nextLine[8]);

                Article article = new Article(id,title,abs,csType,physicsType,mathType,statsType,qbType,qfType);
                articles.add(article);
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

  /*  //Generic Linear Search algorithm which searches for any User define datatype for both linked list and array list
    static  <T> int linearSearch(List<T> list,int key){
        key--;
        for(int i = 0; i< list.size();i++){
            if (list.indexOf(list.get(i))==key){
                return i;
            }
        }
        return -1;
    }*/

    private static int generateRandomKey(int max) {
        //Generate a random key which may or may not exist
        //Between 0(inclusive) and maximum(exclusive)
        int min = 0;
        max = max + 10000; //To generate 10000 keys which do not exist.
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static void timeAnalysis(String analysisName, List<Long> executionTimes){
        // Calculate time metrics
        long totalExecutionTime = 0;
        long bestTime = Long.MAX_VALUE;
        long worstTime = Long.MIN_VALUE;

        for (long executionTime : executionTimes) {
            totalExecutionTime += executionTime;
            if (executionTime < bestTime) {
                bestTime = executionTime;
            }
            if (executionTime > worstTime) {
                worstTime = executionTime;
            }
        }

        double averageTime = (double) totalExecutionTime / executionTimes.size();

        System.out.println(analysisName + " Analysis");
        System.out.println("Average Time: " + averageTime + " ms");
        System.out.println("Best Time: " + bestTime + " ms");
        System.out.println("Worst Time: " + worstTime + " ms\n");
    }

    //DRAFT CODE BELOW
/*    public static <T> List<Long> startLSArray(List<T> list) {
        List<Long> allExecutionTimes = new ArrayList<>(); // Results for Linear Search on Array List
        for (int i = 1; i <= 30; i++) {
            int randomKey1 = generateRandomKey(list.size());
            LinearSearch<T> linearSearch = new LinearSearch<>(list, randomKey1);
            Thread thread1 = new Thread(linearSearch);
            thread1.setName("Array List Linear Search " + i);

            //Start all threads and return execution times.
            long startTime = System.currentTimeMillis();
            thread1.start();
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            allExecutionTimes.add(executionTime);
        }
        System.out.println("Linear Search Complete");

        return allExecutionTimes;
    }

    *//*public static <T> List<Long> startLSLinked(List<T> list){

    }

    public static <T> List<Long> startBSArray(List<T> list){

    }

    public static <T> List<Long> startBSLinked(List<T> list){

    }

    public static <T> List<Long> startSArray(List<T> list){

    }

    public static <T> List<Long> startSLinked(List<T> list){

    }

    public static <T> List<Long> startJSArray(List<T> list){

    }

    public static <T> List<Long> startJSLinked(List<T> list){

    }*//*

    public static <T> List<List<Long>> startMultithread(List<T> list1, List<T> list2){
        List<List<Long>> allExecutionTimes = new ArrayList<>(8);
        List<Long> executionTimesLSAL = new ArrayList<>(); // Results for Linear Search on Array List
        List<Long> executionTimesLSLinked = new ArrayList<>(); // Results for Linear Search on Linked List
        List<Long> executionTimesBSAL = new ArrayList<>(); // Results for Binary Search on Array List
        List<Long> executionTimesBSLinked = new ArrayList<>(); // Results for Binary Search on Linked List
        List<Long> executionTimesSAL = new ArrayList<>(); // Results for Sentinel Search on Array List
        List<Long> executionTimesSLinked = new ArrayList<>(); // Results for Sentinel Search on Linked List
        List<Long> executionTimesJSAL = new ArrayList<>(); // Results for Jump Search on Array List
        List<Long> executionTimesJSLinked = new ArrayList<>(); // Results for Jump Search on Linked List
        for (int i = 1; i <= 30; i++) {
            int randomKey1 = generateRandomKey(list1.size());
            int randomKey2 = generateRandomKey(list2.size());
            LinearSearch<T> linearSearch1 = new LinearSearch<>(list1,randomKey1);
            LinearSearch<T> linearSearch2 = new LinearSearch<>(list2,randomKey2);

            Runnable binarySearch1 = new BinarySearch();
            Runnable binarySearch2 = new BinarySearch();

            Runnable sentinelSearch1 = new SentinelLinearSearch();
            Runnable sentinelSearch2 = new SentinelLinearSearch();

            Runnable jumpSearch1 = new JumpSearch();
            Runnable jumpSearch2 = new JumpSearch();

            //Setting up thread for each(8) algorithm which will run 30 times.
            Thread thread1 = new Thread(linearSearch1);
            thread1.setName("LinearSearchA " + i);
            Thread thread2 = new Thread(linearSearch2);
            thread2.setName("LinearSearchB "+ i);

            Thread thread3 = new Thread(binarySearch1);
            thread3.setName("BinarySearchA "+ i);
            Thread thread4 = new Thread(binarySearch2);
            thread4.setName("BinarySearchB "+ i);

            Thread thread5 = new Thread(sentinelSearch1);
            thread5.setName("SentinelSearchA "+ i);
            Thread thread6 = new Thread(sentinelSearch2);
            thread6.setName("SentinelSearchB "+ i);

            Thread thread7 = new Thread(jumpSearch1);
            thread7.setName("JumpSearchA "+ i);
            Thread thread8 = new Thread(jumpSearch2);
            thread8.setName("JumpSearchB "+ i);

            //Start all threads and return execution times.
            long startTime = System.currentTimeMillis();
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();
            thread6.start();
            thread7.start();
            thread8.start();
            try {
                // Wait for the thread to finish
                thread1.join();
                thread2.join();
                thread3.join();
                thread4.join();
                thread5.join();
                thread6.join();
                thread7.join();
                thread8.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            executionTimesLSAL.add(executionTime);
        }
        System.out.println("All Searches Completed");
        //All execution times are returned for each respective algorithms is returned
        allExecutionTimes.add(0,executionTimesLSAL);
        allExecutionTimes.add(1,executionTimesLSLinked);
        return allExecutionTimes;
    }

    public static void timeAnalysis(String analysisName, List<Long> executionTimes){
        // Calculate time metrics
        long totalExecutionTime = 0;
        long bestTime = Long.MAX_VALUE;
        long worstTime = Long.MIN_VALUE;

        for (long executionTime : executionTimes) {
            totalExecutionTime += executionTime;
            if (executionTime < bestTime) {
                bestTime = executionTime;
            }
            if (executionTime > worstTime) {
                worstTime = executionTime;
            }
        }

        double averageTime = (double) totalExecutionTime / executionTimes.size();

        System.out.println(analysisName + " Analysis");
        System.out.println("Average Time: " + averageTime + " ms");
        System.out.println("Best Time: " + bestTime + " ms");
        System.out.println("Worst Time: " + worstTime + " ms\n");
    }*/
    }

