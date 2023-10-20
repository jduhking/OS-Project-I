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
            while(blockingFifo.GetCount() <= 0) {
            	try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            Task newTask = blockingFifo.take();

            if(newTask != null) {
                try {
                    newTask.execute();
                }
                catch(Throwable th) {
                	return;
                }
            }
        }
    }

}
		
