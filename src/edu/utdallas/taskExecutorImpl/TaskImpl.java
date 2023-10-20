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
		System.out.println(name + " is being executed.");
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println(name + " has been executed.");
	}

	@Override
	public String getName()
	{
		return name;
	}
}
