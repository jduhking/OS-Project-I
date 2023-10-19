package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.BlockingQueue;
import edu.utdallas.taskExecutor.Task;

public class TaskRunner implements Runnable {

	private final BlockingQueue blockingFifo;
	
	public TaskRunner(BlockingQueue fifo) {
		blockingFifo = fifo;
	}
	@Override
	public void run() {
		while(true) {
			Task newTask = blockingFifo.take();
		}

	}

}
