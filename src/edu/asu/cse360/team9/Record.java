package edu.asu.cse360.team9;

import java.util.*;

/**
 * Represents a non-GUI tied model object of
 * Activity, Duration, Path.
 * 
 */
public class Record {
	
	private String activity;
	private int duration;
	private String[] dependencies;
	
	/**
	 * Create a Record
	 * 
	 * @param activity the activity
	 * @param duration the duration
	 * @param dependencies as comma separated list
	 */
	public Record(String activity, int duration, String[] dependencies)
	{
		this.activity = activity;
		this.duration = duration;
		this.dependencies = dependencies;
	}
	
	/**
	 * Get currently set activity
	 * @return activity
	 */
	public String getActivity()
	{
		return activity;
	}
	
	/**
	 * Get currently set duration
	 * 
	 * @return duration
	 */
	public int getDuration()
	{
		return duration;
	}
	
	/**
	 * Get dependencies as a list
	 * @return collection of dependencies
	 */
	public String[] getDependencies()
	{
		return dependencies;	//Arrays.asList(dependencies.split(","));
	}
}
