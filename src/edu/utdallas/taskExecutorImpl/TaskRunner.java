package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.BlockingFifoQueue;
import edu.utdallas.taskExecutor.Task;

public class TaskRunner implements Runnable {

	private final BlockingFifoQueue blockingFifoQueue;
	private final String name;
	
	public TaskRunner(BlockingFifoQueue blockingFifo, String threadName) {
		blockingFifoQueue = blockingFifo;
		name = threadName;
	}
	
	@Override
	public void run() {
		while(true) {
            Task newTask = blockingFifoQueue.take();
            try {
            	newTask.execute();
            	//System.out.println("task runner " + name + " successful");
            }
            catch(Throwable th) {
            	// TODO 
            }
        }
    }

}