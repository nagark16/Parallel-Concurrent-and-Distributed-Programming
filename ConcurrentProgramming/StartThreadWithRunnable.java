package basics;

public class StartThreadWithRunnable implements Runnable{

	
	
	public static void main(String[] args) {
		Thread th = new Thread(new StartThreadWithRunnable(), "second thread");
		th.start();
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}

}
