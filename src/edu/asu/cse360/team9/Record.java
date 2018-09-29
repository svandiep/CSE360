package edu.asu.cse360.team9;

/*
 * Represents a non-GUI tied model object of
 * Activity, Duration, Path.
 * 
 */
public class Record {
	
	private String activity;
	private int duration;
	private String path;
	
	public Record()
	{
		
	}
	
	public Record(String activity, int duration, String path)
	{
		this.activity = activity;
		this.duration = duration;
		this.path = path;
	}
	
	public String getActivity()
	{
		return activity;
	}
	
	public void setActivity(String activity)
	{
		this.activity = activity;
	}
	
	public int getDuration()
	{
		return duration;
	}
	
	public void setDuration(int duration)
	{
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
	
}
