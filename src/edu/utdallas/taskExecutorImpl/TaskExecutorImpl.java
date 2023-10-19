package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.BlockingQueue;
import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class TaskExecutorImpl implements TaskExecutor
{

    private final BlockingQueue blockingFifo;
    
    private TaskRunner[] runnerPool;

    public TaskExecutorImpl(int threadPoolSize)
    {
        blockingFifo = new BlockingQueueImpl();
        runnerPool = new TaskRunner[threadPoolSize];
    }

    @Override
    public void addTask(Task task)
    {

       blockingFifo.put(task);

    }
    

}
