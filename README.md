# Parallel-Concurrent-and-Distributed-Programming

## Parallel Programming
1. ```
ForkJoinPool pool = new ForkJoinPool(2);//2 mean number of cores we would like to use in parallel
pool.invoke(t);//t is the task to run in a core
   ```

   or 
   ```
ForkJoinPool.commonPool().invoke(t);
   ```