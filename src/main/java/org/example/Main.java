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

public class Main {
    public static void main(String[] args) {
        String filePath = Objects.requireNonNull(Main.class.getClassLoader().getResource("Article.csv")).getPath();
        System.out.println(filePath);

        //Reading files and instantiating data structures.
        List<Article> articleLinkedList = new LinkedList<>(readFile(filePath));
        List<Article> articleArrayList = new ArrayList<>(readFile(filePath));

        //ASSIGNMENT QUESTION 3
        //Creating 30 threads for each algorithm and executing simultaneously
        //Threads are stored in array list of threads and started at the same time
        //Times are stored in array list to perform analysis of best,worst and average time taken.

        //Data structure to store execution times for analysis
        List<List<Long>> executionTimes;

        //Creating algorithm objects and initialising data structures to be searched
        LinearSearch<Article> LSA = new LinearSearch<>(articleArrayList);
        LinearSearch<Article> LSL = new LinearSearch<>(articleLinkedList);

        BinarySearch<Article> BSA= new BinarySearch<>(articleArrayList);
        BinarySearch<Article> BSL = new BinarySearch<>(articleLinkedList);

        InterpolationSearch<Article> ISA = new InterpolationSearch<>(articleArrayList);
        InterpolationSearch<Article> ISL = new InterpolationSearch<>(articleLinkedList);

        JumpSearch<Article> JA = new JumpSearch<>(articleArrayList);
        JumpSearch<Article> JL = new JumpSearch<>(articleLinkedList);

        //Creating 8 threads for each algorithm and running simultaneously 30 times as multithread.
        //Execution times for each run is stored for analysis.
        executionTimes = startAlgorithms(articleArrayList,articleLinkedList,LSA,LSL,BSA,
                BSL,ISA,ISL,JA,JL);

        //Performing time analysis on data set corresponding to each algorithm
        timeAnalysis("Linear Search Array List",executionTimes.get(0));
        timeAnalysis("Linear Search Linked List",executionTimes.get(1));
        timeAnalysis("Binary Search Array List",executionTimes.get(2));
        timeAnalysis("Binary Search Linked List",executionTimes.get(3));
        timeAnalysis("Interpolation Search Array List",executionTimes.get(4));
        timeAnalysis("Interpolation Search Linked List",executionTimes.get(5));
        timeAnalysis("Jump Search Array List",executionTimes.get(6));
        timeAnalysis("Jump Search Linked List",executionTimes.get(7));
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

    private static Article generateRandomKey(int max) {
        //Generate an Article with random ID which may or may not exist to act as the key
        //Between 0(inclusive) and maximum+1000(exclusive), inorder to simulate keys which may not exist
        int min = 0;
        max = max + 10000; //To generate 10000 keys which do not exist.
        Random r = new Random();
        return new Article( r.nextInt((max - min) + 1) + min);
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

    public static List<List<Long>> startAlgorithms(List<Article> articleArrayList,List<Article> articleLinkedList,
                                                   LinearSearch<Article> LSA,LinearSearch<Article> LSL,
                                                   BinarySearch<Article> BSA, BinarySearch<Article> BSL,
                                                   InterpolationSearch<Article> ISA, InterpolationSearch<Article> ISL,
                                                   JumpSearch<Article> JA,JumpSearch<Article> JL)
    {
        //Data structures to hold execution times for each algorithm
        List<List<Long>> executionTimes = new ArrayList<>();
        List<Long> executionTimeLSA = new ArrayList<>();
        List<Long> executionTimeLSL = new ArrayList<>();
        List<Long> executionTimeBSA = new ArrayList<>();
        List<Long> executionTimeBSL = new ArrayList<>();
        List<Long> executionTimeISA = new ArrayList<>();
        List<Long> executionTimeISL = new ArrayList<>();
        List<Long> executionTimeJA = new ArrayList<>();
        List<Long> executionTimeJL = new ArrayList<>();


        //Run each type of algorithm 30 times simultaneously using a new key for all runs
        for (int i = 0; i < 30; i++) {
            LSA.setTarget(generateRandomKey(articleArrayList.size()));
            LSL.setTarget(generateRandomKey(articleLinkedList.size()));

            BSA.setTarget(generateRandomKey(articleArrayList.size()));
            BSL.setTarget(generateRandomKey(articleLinkedList.size()));

            ISA.setTarget(generateRandomKey(articleArrayList.size()));
            ISL.setTarget(generateRandomKey(articleLinkedList.size()));

            JA.setTarget(generateRandomKey(articleArrayList.size()));
            JL.setTarget(generateRandomKey(articleLinkedList.size()));

            Thread runLSA = new Thread(LSA,"LSA"+i);
            Thread runLSL = new Thread(LSL,"LSL"+i);

            Thread runBSA = new Thread(BSA,"BSA"+i);
            Thread runBSL = new Thread(BSL,"BSL"+i);

            Thread runISA = new Thread(ISA,"ISA"+i);
            Thread runISL = new Thread(ISL,"ISL"+i);

            Thread runJA = new Thread(JA,"JA"+i);
            Thread runJL = new Thread(JL,"JL"+i);

            runLSA.start();
            runLSL.start();

            runBSA.start();
            runBSL.start();

            runISA.start();
            runISL.start();

            runJA.start();
            runJL.start();

            try {
                runLSA.join();
                runLSL.join();
                runBSA.join();
                runBSL.join();
                runISA.join();
                runISL.join();
                runJA.join();
                runJL.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            executionTimeLSA.add(LSA.getExecutionTime());
            executionTimeLSL.add(LSL.getExecutionTime());
            executionTimeBSA.add(BSA.getExecutionTime());
            executionTimeBSL.add(BSL.getExecutionTime());
            executionTimeISA.add(ISA.getExecutionTime());
            executionTimeISL.add(ISL.getExecutionTime());
            executionTimeJA.add(JA.getExecutionTime());
            executionTimeJL.add(JL.getExecutionTime());
        }
        //Collect all Time data into single List
        executionTimes.add(0,executionTimeLSA);
        executionTimes.add(1,executionTimeLSL);
        executionTimes.add(2,executionTimeBSA);
        executionTimes.add(3,executionTimeBSL);
        executionTimes.add(4,executionTimeISA);
        executionTimes.add(5,executionTimeISL);
        executionTimes.add(6,executionTimeJA);
        executionTimes.add(7,executionTimeJL);

        return executionTimes;

    }
    }

