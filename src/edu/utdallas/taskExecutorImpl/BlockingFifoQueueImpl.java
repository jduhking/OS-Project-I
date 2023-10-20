package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.BlockingFifoQueue;
import edu.utdallas.taskExecutor.Task;

public class BlockingFifoQueueImpl implements BlockingFifoQueue {
	
	private int BUFFER_SIZE;
	private Task[] buffer;
    private int nextIn, nextOut;
    private int count;
    private Object notFull, notEmpty; // locks used for synchronization

	public BlockingFifoQueueImpl(int bufferSize) {
		BUFFER_SIZE = bufferSize;
		buffer = new Task[BUFFER_SIZE];
	}
	
  // places task at back of FIFO
	@Override
	public void put(Task newTask) {
    // if buffer is full, wait for take
    // for loop prevents a race condition as waiting thread must recheck
    // BUFFER_SIZE after notified by take()
    // a sychronized block is not inculded as it is alread included in
    // TaskExecutorImpl
	synchronized(this) {
	    if(count == BUFFER_SIZE)
	    {
		      try {
		    	  
		    	while(count == BUFFER_SIZE)
		    		wait();
				
				buffer[nextIn] = newTask; 
			    nextIn = (nextIn + 1) % BUFFER_SIZE;
			    count++;
	
			    	notify(); // signal waiting take threads
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				return;
			}
	    }
	}
    
    // puts task at end of FIFO, increments nextIn and count
   
    }
	

  // takes task from front of FIFO, returns the task
	@Override
	public Task take() {
    synchronized(this) { // only one thread can take at a time,
	                         // prevents race condition
	      try {
	      // if buffer is empty, wait for put
		      while(count == 0)
		      {
		        wait();
		      }
		    
		      // sets return value for task, incremtns nextOut, decrements count 
		      Task result = buffer[nextOut];
		      nextOut = (nextOut + 1) % BUFFER_SIZE;
		      count --;
	
		      notify(); // signal waiting put threads
		      return result;
	      } catch(InterruptedException e) {
	    	  e.printStackTrace();
	      }
	      return null;
	
	    }
  }
	
	public int GetCount() { return count; }
	public int GetBufferSize() { return BUFFER_SIZE; }
	
}
