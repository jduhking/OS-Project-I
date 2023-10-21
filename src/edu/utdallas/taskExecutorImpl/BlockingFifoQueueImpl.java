package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.BlockingFifoQueue;
import edu.utdallas.taskExecutor.Task;

public class BlockingFifoQueueImpl implements BlockingFifoQueue {
	
	  private int BUFFER_SIZE;
	  private Task[] buffer;
    private int nextIn, nextOut;
    private int count;
    private Object notFull = new Object(); // lock object used by thread attempting to put
    private Object notEmpty = new Object(); // lock object used by thread attempting to take

	public BlockingFifoQueueImpl(int bufferSize) {
		BUFFER_SIZE = bufferSize;
		buffer = new Task[BUFFER_SIZE];
	}
	
  // places task at back of FIFO
	@Override
	public void put(Task newTask) {
    // if buffer is full, wait for take
    // while loop prevents a race condition as waiting thread must recheck
    // BUFFER_SIZE after notified by take()
	//synchronized(notFull) {
		try {
		    	  
		  while(count == BUFFER_SIZE) 
		  {
		    notFull.wait();
	      System.out.println("notFull.wait() successful"); // for debugging, remove before submission
		  }
	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				
		 // puts task at end of FIFO, increments nextIn and count
		buffer[nextIn] = newTask; 
		nextIn = (nextIn + 1) % BUFFER_SIZE;
		count++;
	//}
	
	//synchronized(notEmpty) { // thread must own notEmpty to execute notEmpty.notify()
		try {
			notEmpty.notify(); // signal waiting take threads
			System.out.println("notEmpty.notify() successful");	// for debugging, remove before submission
		} catch(Exception e) {
			e.printStackTrace();
		}
	//}
	 
   
    }
	

  // takes task from front of FIFO, returns the task
	@Override
	public Task take() {
    //synchronized(notEmpty) { // only one thread can take at a time,
	                         // prevents race condition
    	Task result = null;
    	try {
	    // if buffer is empty, wait for put
    		while(count == 0)
		    {
		        notEmpty.wait();
    			System.out.println("notEmpty.wait() successful"); // for debugging, remove before submission
		    }
		    
		    // sets return value for task, increments nextOut, decrements count 
		    result = buffer[nextOut];
		    nextOut = (nextOut + 1) % BUFFER_SIZE;
		    count --;
	
		    System.out.println("notFull.notify() successful"); // for debugging, remove before submission
		    notFull.notify(); // signal waiting put threads
	      } catch(InterruptedException e) {
	    	  e.printStackTrace();
	      }
    //}
    //synchronized(notFull) {	// thread must own notFull to execute notFull.notify();
    	try {
    		notFull.notify(); // signal waiting put threads
		    System.out.println("notFull.notify() successful"); // for debugging, remove before submission
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
	    return result;
	  //}

  }
    public int GetCount() { return count; }
    public int GetBufferSize() { return BUFFER_SIZE; }
}
