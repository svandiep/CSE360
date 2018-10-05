package edu.asu.cse360.team9;

import java.util.*;

/**
 * This class is supposed to provide a common pass-through between
 * the GUI layer and the internal model.
 * 
 * See Model-View-Controller design pattern: https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller
 */
public class Controller {
	
	private Graph graph;
	private HashMap<String, Integer> durations;
	
	/**
	 * Constructs graph object for future path calculation based on provided records.
	 * @param records The collection of records provided by the table data in MainFrame.
	 */
	public void populateGraph(List<Record> records)
	{
		// Given a collection of records, do all calculations to find all paths.
		//
		// First, create a Graph, and assign internal start node.
		graph = new Graph();
		durations = new HashMap<String, Integer>();
		durations.put(Graph.START_ACTIVITY, 0);
		for(Record r : records)
		{
			durations.put(r.getActivity(), r.getDuration());
		}
		
		// If an activity is a dependency, then it cannot be a final node.
		ArrayList<String> dependents = new ArrayList<String>();
		for(Record r : records)
		{			
			for(String s : r.getDependencies())
			{
				// This is a start node:
				if(s.isEmpty())
				{
					graph.addEdge(Graph.START_ACTIVITY, r.getActivity());
					continue;
				}
				
				dependents.add(s);
				graph.addEdge(s, r.getActivity());
			}
		}
		
		// For all activities' keys, if it doesn't exist in dependents, it's final.
		for(String s : durations.keySet())
		{
			if(!s.equals(Graph.START_ACTIVITY) && !dependents.contains(s))
			{
				graph.setStopActivity(s);
			}
		}
	}
	
	/**
	 * With a populated graph, this provides the calculated results
	 *  
	 * @return ArrayList<Result> of all results generated.
	 */
	public ArrayList<Result> getResults()
	{
		return graph.getPaths(durations);
	}
}
