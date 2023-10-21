package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.Task;

public class TaskImpl implements Task
{
	private String name;

	public TaskImpl(String name)
	{
		this.name = name;
	}

	@Override
	public void execute()
	{
	        try {
	            Thread.sleep(100);

	            StringBuilder sb = new StringBuilder();
	            sb.append("Hello From Thread: ");
	            sb.append(Thread.currentThread().getName());
	            sb.append(", Task: ");
	            sb.append(name);
	
	            // Note that the printed message includes the Thread's unique name.
	            System.out.println(sb);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	}

	@Override
	public String getName()
	{
		return name;
	}
}