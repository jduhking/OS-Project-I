package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.BlockingFifoQueue;
import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;

public class TaskExecutorImpl implements TaskExecutor
{

    private final BlockingFifoQueue blockingFifo;
    private TaskRunner[] runnerPool;

    public TaskExecutorImpl(int threadPoolSize)
    {
    	int queueSize = threadPoolSize * 10;
        blockingFifo = new BlockingFifoQueueImpl(queueSize); // instantiate blocking fifo and runner pool
        runnerPool = new TaskRunner[threadPoolSize];
        
        for(int i = 0; i < runnerPool.length; i++) { // initializing of task runner thread pool
        	TaskRunner taskRunner = new TaskRunner(blockingFifo, "TaskThread" + (i + 1));
        	runnerPool[i] = taskRunner;
        	Thread thread = new Thread(taskRunner);
        	thread.start(); // start the thread
        }
    }

    public void addTask(Task task)
    {
       synchronized(this) {

    	   blockingFifo.put(task);
       }
    }
    

}
