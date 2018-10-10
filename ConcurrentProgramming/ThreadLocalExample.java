package basics;

public class ThreadLocalExample implements Runnable{
	
	private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
	private final int value;
	
	public ThreadLocalExample(int val) {
		this.value = val;
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[5];
		for(int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new ThreadLocalExample(i), "thread-"+i);
			threads[i].start();
		}
		for(int i = 0; i < threads.length; i++)
			threads[i].join();
	}

	@Override
	public void run() {
		threadLocal.set(value);
		Integer inte = threadLocal.get();
		System.out.println(Thread.currentThread().getName()+" "+inte);
	}

}
