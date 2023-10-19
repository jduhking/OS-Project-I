package edu.utdallas.taskExecutor;

public interface BlockingFifoQueue {
	
	public void put(Task newTask);
	
	public Task take();

}
