package basics;

public class StartThread extends Thread{

	public StartThread(String name) {
		super(name);
	}
	
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		StartThread st = new StartThread("first thread");
		st.start();
	}

}
