package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.BlockingFifoQueue;
import edu.utdallas.taskExecutor.Task;

public class BlockingFifoQueueImpl implements BlockingFifoQueue {
	
	private int BUFFER_SIZE;
	private Task[] buffer;
    private int nextIn, nextOut;
    private int count;
    private Object notFull = new Object(), notEmpty = new Object(); // locks used for synchronization

	public BlockingFifoQueueImpl(int bufferSize) {
		if(bufferSize >= 100) { // buffer size cannot exceed 100
			bufferSize = 100;
		}
		BUFFER_SIZE = bufferSize;
		buffer = new Task[BUFFER_SIZE];
	}
	
	// places task at back of FIFO
	@Override
	public void put(Task newTask) {
    // if buffer is full, wait for take
    // while loop prevents a race condition as waiting thread must recheck
    // BUFFER_SIZE after notified by take()
	synchronized(notFull) {
		while(count == BUFFER_SIZE) 
		{
			try {
				notFull.wait();
		    } catch(InterruptedException e) {
		    	e.printStackTrace();
		    }
		}
		buffer[nextIn] = newTask; 
		nextIn = (nextIn + 1) % BUFFER_SIZE;
		count++;
	
		}
	synchronized(notEmpty) {
		notEmpty.notify(); // signal waiting take threads
	}
	    
//	    for(int i = 0; i < BUFFER_SIZE; i++)
//	    	System.out.println(buffer[i] == null ? "null" : buffer[i].getName());
	}
		
	// takes task from front of FIFO, returns the task
	@Override
	public Task take() {
		Task result = null;
    synchronized(notEmpty) { // only one thread can take at a time,
	                         // prevents race condition
    	while(count == 0)
    	{
	      try {
	    	  notEmpty.wait();
	      // if buffer is empty, wait for put
	      } catch(InterruptedException e) {
	    	  e.printStackTrace();
	      }
    	}
		      
    	result = buffer[nextOut];
    	buffer[nextOut] = null;
		nextOut = (nextOut + 1) % BUFFER_SIZE;
		count--;
	
	}
    synchronized(notFull) {
    	notFull.notify(); // signal waiting put threads
    }
    	return result;	
	}
	
	public int GetCount() { return count; }
	public int GetBufferSize() { return BUFFER_SIZE; }
	
}