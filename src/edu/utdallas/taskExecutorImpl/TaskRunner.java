package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.BlockingFifoQueue;
import edu.utdallas.taskExecutor.Task;

public class TaskRunner implements Runnable {

	private final BlockingFifoQueue blockingFifo;
	
	public TaskRunner(BlockingFifoQueue fifo) {
		blockingFifo = fifo;
	}
	@Override
	public void run() {
		while(true) {
			Task newTask = blockingFifo.take();
		}

	}

}
