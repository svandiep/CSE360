package edu.asu.cse360.team9;

import java.util.*;

import sun.reflect.generics.tree.ClassTypeSignature;

/**
 * Represents a non-GUI model object of 
 * Path, Total Duration
 * 
 */
public class Result implements Comparable<Result> {
	
	private ArrayList<String> path;
	private int duration;
	
	public Result()
	{
		path = new ArrayList<String>();
	}
	
	/**
	 * Adds an entry to the path, and increments duration by
	 * provided duration
	 * 
	 * @param activity the activity
	 * @param duration the duration
	 */
	public void addPathActivity(String activity, int duration)
	{
		if(activity.equals(Graph.START_ACTIVITY))
			return;
		
		path.add(activity);
		this.duration += duration;
	}
	
	/**
	 * Get the stored path as a comma separated list.
	 * 
	 * @return comma separated list of activities
	 */
	public String getPath()
	{
		return String.join(",", path);
	}
	
	/**
	 * Get the total duration for given path.
	 * 
	 * @return total duration
	 */
	public int getDuration()
	{
		return duration;
	}
	
	/**
	 * Implementation of equals, which determines sameness to be path equals, and duration equals.
	 * 
	 */
	@Override
	public boolean equals(Object o)
	{
		System.out.println("Comparing...");
		
		if (o == null)
			return false;
		
		if(o.getClass() != this.getClass() )
			return false;
		
		Result r = (Result)o;
		
		return (this.getPath().equals(r.getPath()) && this.getDuration() == r.getDuration());
	}

	/**
	 * Allows for collection sorting, which is implemented as descending by duration.
	 */
	@Override
	public int compareTo(Result other) {
		// We want to sort in descending order by duration.
		return other.duration - this.duration;
	}
}
