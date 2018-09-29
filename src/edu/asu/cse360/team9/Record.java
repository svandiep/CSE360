package edu.asu.cse360.team9;

import java.util.*;

/*
 * Represents a non-GUI tied model object of
 * Activity, Duration, Path.
 * 
 */
public class Record {
	
	private String activity;
	private int duration;
	private String dependencies;
	
	public Record()
	{
		
	}
	
	public Record(String activity, int duration, String dependencies)
	{
		this.activity = activity;
		this.duration = duration;
		this.dependencies = dependencies;
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
	
	public List<String> getDependencies()
	{
		return Arrays.asList(dependencies.split(","));
	}
	
	public void setDependencies(String dependencies)
	{
		this.dependencies = dependencies;
	}
	
}
