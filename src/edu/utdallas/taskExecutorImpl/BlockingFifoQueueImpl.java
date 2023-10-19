package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.BlockingFifoQueue;
import edu.utdallas.taskExecutor.Task;

public class BlockingFifoQueueImpl implements BlockingFifoQueue {
	
	private int BUFFER_SIZE;
	private Task[] buffer;

	public BlockingFifoQueueImpl(int bufferSize) {
		BUFFER_SIZE = bufferSize;
		buffer = new Task[BUFFER_SIZE];
	}
	
	@Override
	public void put(Task newTask) {
		

	}

	@Override
	public Task take() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
