package edu.asu.cse360.team9;

import java.util.*;
/*
 * Represents a non-GUI model object of 
 * Path, Total Duration
 */
public class Result {
	
	private ArrayList<String> path;
	private int duration;
	
	public Result()
	{
		path = new ArrayList<String>();
	}
	
	public void addPathNode(String node, int duration)
	{
		path.add(node);
		this.duration += duration;
	}
	
	public String getPath()
	{
		return String.join(",", path);
	}
	
	public int getDuration()
	{
		return duration;
	}
}
