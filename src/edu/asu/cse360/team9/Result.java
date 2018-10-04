package edu.asu.cse360.team9;

import java.util.*;

import sun.reflect.generics.tree.ClassTypeSignature;
/*
 * Represents a non-GUI model object of 
 * Path, Total Duration
 */
public class Result implements Comparable<Result> {
	
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

	@Override
	public int compareTo(Result other) {
		// We want to sort in descending order by duration.
		return other.duration - this.duration;
	}
}
