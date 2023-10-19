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
			System.out.println("Executing on thread " + name);
            Task newTask = null;
            try {
                newTask = blockingFifoQueue.take();
            }
            catch(InterruptedException e) {
        
                System.out.println("Thread interrupted: " + e.getMessage());
            }

            if(newTask != null) {
                try {
                    newTask.execute();
                }
                catch(Throwable th) {
                    System.out.println("Task execution failed: " + th.getMessage());
                }
            }
        }
    }

}
		
