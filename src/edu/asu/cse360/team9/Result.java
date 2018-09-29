package edu.asu.cse360.team9;

/*
 * Represents a non-GUI model object of 
 * Path, Total Duration
 */
public class Result {
	
	private String path;
	private int duration;
	
	public Result()
	{
		
	}
	
	public Result(String path, int duration)
	{
		this.path = path;
		this.duration = duration;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
	
	public int getDuration()
	{
		return duration;
	}
	
	public void setDuration(int duration)
	{
		this.duration = duration;
	}
	
}
