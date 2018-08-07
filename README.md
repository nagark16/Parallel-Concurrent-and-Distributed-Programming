# Parallel-Concurrent-and-Distributed-Programming

## Parallel Programming
### Task Parallelism
* ```
	ForkJoinPool pool = new ForkJoinPool(2);//2 mean number of cores we would like to use in parallel
	pool.invoke(t);//t is the task to run in a core
   ```

   or 
   ```
    ForkJoinPool.commonPool().invoke(t);
   ```
* We have to extend RecursiveAction for Task parallelism and implement compute() method for central logic.
### Functional Parallelism
* We convert functional program to parallel program using *futures*
* Future tasks are tasks with return values, and a future object (also called promise object) is a “handle” for accessing a task’s return value.
* The content of the future object is constrained to be single assignment (similar to a final variable in Java), and cannot be modified after the future task has returned.
* We have to extend RecursiveTask to get *future*. Here too we need to implement compute() method with non-void return type. Here when we call fork(), we are implicitly calling *future* and call of join() is like calling *future* get(). This join() has return value. This is all because RecursiveTask implements *future* interface.
* *Memoization*: Store futures in data structures for later use. So that when we do lookup get() operation on future is called.
* *Java Streams*: An aggregate data query or data transformation can be specified by building a stream pipeline consisting of a source (typically by invoking the .stream() method on a data collection , a sequence of intermediate operations such as map() and filter(), and an optional terminal operation such as forEach() or average().
		Example: ```
				students.stream()
		    			.filter(s -> s.getStatus() == Student.ACTIVE)
		    			.map(a -> a.getAge())
		    			.average();
    			```
    	We can make the above code parallel by replacing ``` students.stream() ``` with ``` students.parallelStream() ``` or ``` Stream.of(students).parallel() ```
* *Determinism* : 
	*Functional Determinism*: Same input -> same output all the time
	*Structural Determinism*: Same input -> same computational graph
	data race freedom mean we have both functional determinism and structural determinism
	*Benign nondeterminism*: cases where parallel programs produce different output each time we run. These different outputs are acceptable.
* *Data race*: happen when read-write or write-write situations. We can use *future* to avoid data race.