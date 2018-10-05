package basics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreExample implements Runnable{

	private static final Semaphore semaphore = new Semaphore(3, true);
	private static final AtomicInteger counter = new AtomicInteger();
	private static final long endMillis = System.currentTimeMillis() + 10000;
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for(int i = 0; i < 5; i++)
			executorService.execute(new SemaphoreExample());
		executorService.shutdown();
	}

	@Override
	public void run() {
		while(System.currentTimeMillis() < endMillis) {
			try {
				semaphore.acquire();
			}catch(InterruptedException ex) {
				System.out.println(Thread.currentThread().getName() + "interrupted");
			}
			int counterValue = counter.incrementAndGet();
			System.out.println(Thread.currentThread().getName() + "acquired semphore");
			if(counterValue > 3)
				throw new IllegalStateException("More than three threads acquired the lock.");
			counter.decrementAndGet();
			semaphore.release();
		}
	}

}
