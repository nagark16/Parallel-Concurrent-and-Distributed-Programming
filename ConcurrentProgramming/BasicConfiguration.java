package basics;

import java.lang.Thread.State;

public class BasicConfiguration {

	public static void main(String[] args) {
		long id = Thread.currentThread().getId();
		String name = Thread.currentThread().getName();
		int priority = Thread.currentThread().getPriority();
		State state = Thread.currentThread().getState();
		String threadGroupName = Thread.currentThread().getThreadGroup().getName();
		System.out.println("id: "+id);
		System.out.println("name: "+name);
		System.out.println("priority: "+priority);
		System.out.println("state: "+state);
		System.out.println("threadGroupName: "+threadGroupName);
	}

}
