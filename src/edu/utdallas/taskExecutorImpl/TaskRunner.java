package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.BlockingFifoQueue;
import edu.utdallas.taskExecutor.Task;

public class TaskRunner implements Runnable {

	private final BlockingFifoQueue blockingFifo;
	private final int id;
	
	public TaskRunner(BlockingFifoQueue fifo, int _id) {
		blockingFifo = fifo;
		id = _id;
	}
	@Override
	public void run() {
		while(true) {
			System.out.println("Executing task runner " + id);
		}

	}

}
