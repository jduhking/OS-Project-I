package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.BlockingFifoQueue;
import edu.utdallas.taskExecutor.Task;

public class BlockingFifoQueueImpl implements BlockingFifoQueue {
	
	private int BUFFER_SIZE;
	private Task[] buffer;
  private int nextIn, nextOut;
  private int count;
  private Object notFull, notEmpty; // locks used for synchronization
  private boolean notifiedHasExecuted = 1; // if true, thread waiting to put
                                          // has been notified, has executed
                                          // and has incrmented count.
                                          // solves race condition

	public BlockingFifoQueueImpl(int bufferSize) {
		BUFFER_SIZE = bufferSize;
		buffer = new Task[BUFFER_SIZE];
	}
	
  // places task at end of FIFO
	@Override
	public void put(Task newTask) {
		// if waiting thread that has been notified has not executed, cant put
    if(notifiedHasExecuted = 1)
    {
      // if buffer is full, wait for take
      if(count == BUFFER_SIZE)
      {
      notFull.wait();
      }

      notifiedHasExecuted = 0; // signals thread notified still needs to execute

      // puts task at end of FIFO, increments nextIn and count
      buffer[nextIn] = task; 
      nextIn = (nextIn + 1) % BUFFER_SIZE;
      count++;


      notifiedHasExecuted = 1; // signals that notified thread has executed
      notEmpty.notify(); // signal waiting take threads
    }
	}

  // takes task from front of FIFO, returns the taskExecutor
  // increments nextOut and count
	@Override
	public Task take() {
    // if buffer is empty, wait for put
    if(count == 0 )
    {
      notEmpty.wait();
    }
    
    // sets return value for task, incremtns nextOut, decrements count 
    Task result = buffer[nextOut];
    nextOut = (nextOunt + 1) % BUFFER_SIZE;
    count --;

    notFull.nofity(); // signal waiting put threads
    return result;
	}
	

}
