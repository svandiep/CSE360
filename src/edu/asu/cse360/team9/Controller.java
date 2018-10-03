package edu.asu.cse360.team9;

import java.util.*;

/*
 * This class is supposed to provide a common pass-through between
 * the GUI layer and the internal model.
 * 
 * See Model-View-Controller design pattern: https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller
 */
public class Controller {
	
	private Graph graph;
	
	public void doCalculation(List<Record> records)
	{
		// Given a collection of records, do all calculations to find all paths.
		//
		// First, create a Graph, and identify start node
		graph = new Graph();
		HashMap<String, Integer> activities = new HashMap<String, Integer>();
		for(Record r : records)
		{
			activities.put(r.getActivity(), r.getDuration());
		}
		
		// If an activity is a dependency, then it cannot be a final node.
		ArrayList<String> dependents = new ArrayList<String>();
		for(Record r : records)
		{			
			for(String s : r.getDependencies())
			{
				// This is our start node:
				if(s.isEmpty())
				{
					graph.setStartActivity(r.getActivity());
					continue;
				}
				
				dependents.add(s);
				graph.addEdge(s, r.getActivity() /*, activities.get(s) */);
			}
		}
		
		// For all activities' keys, if it doesn't exist in dependents, it's final.
		for(String s : activities.keySet())
		{
			if(!dependents.contains(s))
			{
				graph.setStopActivity(s);
			}
		}
		
		// debugging
		graph.display();
	}
	
	public ArrayList<Result> getResults()
	{
		ArrayList<Result> output = new ArrayList<Result>();
		Result r1 = new Result();
		r1.addPathNode("A", 2);
		r1.addPathNode("B", 4);
		r1.addPathNode("C", 5);
		r1.addPathNode("E", 3);
		output.add(r1);
		
		Result r2 = new Result();
		r2.addPathNode("A", 2);
		r2.addPathNode("F", 5);
		r2.addPathNode("D", 6);
		r2.addPathNode("E", 3);
		output.add(r2);
		
		Result r3 = new Result();
		r3.addPathNode("A", 2);
		r3.addPathNode("B", 4);
		r3.addPathNode("D", 6);
		r3.addPathNode("E", 3);
		output.add(r3);
		
		// Called by Paths JFrame on spawn to get the data for population of JTable.
		return output;
	}
}
