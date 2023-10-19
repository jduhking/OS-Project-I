package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class TaskExecutorImpl implements TaskExecutor
{

    private final Queue<Task> blockingFIFO;
    private ExecutorService executorService;
    public TaskExecutorImpl(int threadPoolSize)
    {
        blockingFIFO = new LinkedList<>();
        executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    @Override
    public void addTask(Task task)
    {

       blockingFIFO.add(task);

    }

}
