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
		// First, create a Graph.
		graph = new Graph();
		HashMap<String, Integer> activities = new HashMap<String, Integer>();
		for(Record r : records)
		{
			activities.put(r.getActivity(), r.getDuration());
		}
		
		for(Record r : records)
		{			
			for(String s : r.getDependencies())
			{
				if(s.isEmpty()) continue;
				
				graph.addEdge(s, r.getActivity(), activities.get(s));
			}
		}
		
		// debugging
		graph.display();
	}
	
	public ArrayList<Result> getResults()
	{
		// Called by Paths JFrame on spawn to get the data for population of JTable.
		return null;
	}
}
