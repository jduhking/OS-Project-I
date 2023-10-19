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
        blockingFifo = new BlockingFifoQueueImpl(); // instantiate blocking fifo and runner pool
        runnerPool = new TaskRunner[threadPoolSize];
        
        for(int i = 0; i < runnerPool.length; i++) { // initializing of task runner thread pool
        	TaskRunner taskRunner = runnerPool[i] = new TaskRunner(blockingFifo, i + 1);
        	Thread thread = new Thread(taskRunner);
        	thread.start(); // start the thread
        }
    }

    @Override
    public void addTask(Task task)
    {

       blockingFifo.put(task);

    }
    

}
