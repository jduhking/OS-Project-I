package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.BlockingFifoQueue;
import edu.utdallas.taskExecutor.Task;

public class TaskRunner implements Runnable {

	private final BlockingFifoQueue blockingFifo;
	private final String name;
	
	public TaskRunner(BlockingFifoQueue fifo, String threadName) {
		blockingFifo = fifo;
		name = threadName;
	}
	@Override
	public void run() {
		while(true) {
//			System.out.println("Trying to run a task");
            Task newTask = blockingFifo.take();
            if(newTask != null) {
            	newTask.execute();
            }
          
            }
        }

}
	