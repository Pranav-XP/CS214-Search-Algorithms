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
1. **Linear Search**<br>
Linear search iterates List elements one by one and compares each element to target until they are matched.
2. **Binary Search**<br>
Binary Search splits the data structure in two halves as it searches for element in middle of list.
3. **Interpolation Search**<br>
Searches for an element in sorted and uniformly distributed list. It utilised a Probe formula derived from linear function y=mx+c to approximate location of target in list, thus it is well suited for Linear Distributed data.
4. **Jump Search**<br>
Searches for element in predefined blocks of the list. If element is not in one block it moves to the next until all blocks are iterated
<br>
### Utility Functions
1. **Random Key Generator**<br>
Returns an Article with randomly generated ID to represent the target to be searched.
2. **Multithread functions**<br>
Two multithread functions are implemented, for empirical analysis and for worst case analysis respectfully. The function instantiates 8 threads for each algorithm and runs them simultaneously 30 times.
3. **Time Analysis**<br>
Reads execution time for each algorithm and outputs the best, worst and average time.
4. **Read File**<br>
CSV file is read using OpenCSV library. A Custom Parser is implemented using ParserBuilder() and the files are read into Array List and Linked List.
<br>

### MATLAB Engine API
Matlab Engine API is used to handle graphing aspect. A line graph of Number of Operations vs Input Size is plotted under Algorithm Worst Case condition.

### How to run?
Simply run the Main.java class to run all algorithms, analysis and graphing, which has all been automated.