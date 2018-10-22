package basics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class StringCollection{
	
	List<String> shared = new ArrayList<>();
	
	void addString(String str) {
		synchronized (StringCollection.class) {
			shared.add(str);
		}
	}
	
	List<String> getStringCollection(){
		synchronized (StringCollection.class) {
			return shared;
		}
	}
}

class StringCollectionRunnable implements Runnable{
	
	private final StringCollection stringCollection;
	private final int stringCount;
	private final String threadName;
	
	public StringCollectionRunnable(StringCollection stringCo, int stringCou, String threadNa) {
		this.stringCollection = stringCo;
		this.stringCount = stringCou;
		this.threadName = threadNa;
	} 
	
	@Override
	public void run() {
		for(int j = 0; j < stringCount; j++)
			this.stringCollection.addString(threadName+String.valueOf(j+1));
	}
}

public class Question {
	
	private static final Scanner scan = new Scanner(System.in);
	private static final StringCollection str = new StringCollection(); 
	
	public static void main(String[] args) {
		int threadCount = Integer.parseInt(scan.nextLine());
		Thread[] threads = new Thread[threadCount];
		
		for(int i = 0; i < threadCount; i++) {
			int stringCount = Integer.parseInt(scan.nextLine());
			
			threads[i] = new Thread(new StringCollectionRunnable(str, stringCount, String.valueOf(i+1)));
			threads[i].start();
		}
		
		for(int i = 0; i < threadCount; i++) {
			try {
				threads[i].join();
			}catch (InterruptedException e) {
				System.err.println(e);
			}
		}
		
		List<String> stringColect = str.getStringCollection();
		System.out.println(stringColect.size());
		
		int nonNullStrings = 0;
		for(String string:stringColect) {
			if(string != null)
				nonNullStrings++;
		}
		
		System.out.println(nonNullStrings);
	}

	

}
