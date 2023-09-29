# CS219 Assignment1
### Group Members
* S11171153 Pranav Chand
* S11209162 Pui Chen
* S11210082 Aryan Sharma
<br><br>

### CRITICAL: THIS PROGRAM REQUIRES MATLAB ENGINE JAR TO EXECUTE. MATLAB R2023a IS UTILISED.

### Search Algorithm Functions Implemented
Each Search Algorithm is implemented in its own class.
It keeps track of counter values and execution times for each instance it is run. If an element is found it returns the index, and if not found it returns -1.
1. **Linear Search**
Linear search iterates List elements one by one and compares each element to target until they are matched.
2. **Binary Search**
Binary Search splits the data structure in two halves as it searches for element in middle of list.
3. **Interpolation Search**
Searches for an element in sorted and uniformly distributed list. It utilised a Probe formula derived from linear function y=mx+c to approximate location of target in list, thus it is well suited for Linear Distributed data.
4. **Jump Search**
Searches for element in predefined blocks of the list. If element is not in one block it moves to the next until all blocks are iterated

## Utility Functions
1. **Random Key Generator**
Returns an Article with randomly generated ID to represent the target to be searched.
2. **Multithread functions**
Two multithread functions are implemented, for empirical analysis and for worst case analysis respectfully. The function instantiates 8 threads for each algorithm and runs them simultaneously 30 times.
   //Creates 8 threads for each algorithm and executing simultaneously
   //Threads are stored in array list of threads and started at the same time
   //Times are stored in array list to perform analysis of best,worst and average time taken.
4. **Time Analysis**
Reads execution time for each algorithm and outputs the best, worst and average time.
5. **Read File**
CSV file is read using OpenCSV library. A Custom Parser is implemented using ParserBuilder() and the files are read into Array List and Linked List. Please note 3 changes are made to the CSV files inorder for Parser to function.


### MATLAB Engine API
Matlab Engine API is used to handle graphing aspect. A line graph of Number of Operations vs Input Size is plotted under Algorithm Worst Case condition.

### How to run?
Simply run the Main.java class to run all algorithms, analysis and graphing, which has all been automated.
