package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.BlockingFifoQueue;
import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;

public class TaskExecutorImpl implements TaskExecutor
{

    private final BlockingFifoQueue blockingFifo;
    
    private Thread[] runnerPool;

    public TaskExecutorImpl(int threadPoolSize)
    {
    	int queueSize = threadPoolSize * 10;
        blockingFifo = new BlockingFifoQueueImpl(queueSize); // instantiate blocking fifo and runner pool
        runnerPool = new Thread[threadPoolSize];
        
        for(int i = 0; i < runnerPool.length; i++) { // initializing of task runner thread pool
        	runnerPool[i] = new Thread(new TaskRunner(blockingFifo, "TaskThread" + (i + 1)));
        	runnerPool[i].start(); // start the thread
        }
    }

    public void addTask(Task task)
    {
       synchronized(this) {
    	   while(blockingFifo.GetCount() >= blockingFifo.GetBufferSize()) {
    		   try {
    		   wait();
    		   } catch(Exception e) {
    			   return;
    		   }
    	   }
    	   blockingFifo.put(task);
    	   notifyAll();
       }
    }
    

}
