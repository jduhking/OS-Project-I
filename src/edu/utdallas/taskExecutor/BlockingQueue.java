package edu.utdallas.taskExecutor;

public interface BlockingQueue {
	
	public void put(Task newTask);
	
	public Task take();

}
