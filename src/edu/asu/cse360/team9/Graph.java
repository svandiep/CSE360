package edu.asu.cse360.team9;
import java.util.*;
import java.util.Map.Entry;

/**
 * Provides representation of a directed graph as an adjacency list.
 * 
 */
public class Graph {

	// Maintained list of existing edges to prevent duplication.
	private ArrayList<String> edges;
	
	// Adjacency list maps the activity name to the linked list.
	private HashMap<String, LinkedList<Edge>> adjList;
	
	// Multiple start nodes require an internal start node that all provided
	// start nodes will depend on. This is stripped out of any final results
	// further up the call stack.
	public static final String START_ACTIVITY = "__START__";
	
	// The stop activity has nothing depending on it, and there can only be one.
	private String stopActivity = "";
	
	/**
	 * Container for source and destination nodes to produce a directed edge.
	 * 
	 */
	private class Edge
	{
		String source;
		String destination;
		
		Edge(String source, String destination)
		{
			this.source = source;
			this.destination = destination;
		}
		
		public String toString()
		{
			return "[" + source + ", " + destination + "]";
		}
	}
	
	
	public Graph()
	{
		adjList = new HashMap<String, LinkedList<Edge>>();
		edges = new ArrayList<String>();
	}
	
	/**
	 * Add a new edge to the correct index of the adjacency list
	 * and add those previously unseen activities as keys.
	 * 
	 * @param source Where this edge starts
	 * @param destination Where this edge goes
	 * 
	 * @throws IllegalArgumentException When the edge exists
	 */
	public void addEdge(String source, String destination)
	{	
		if(edges.contains(source + "-" + destination))
			throw new IllegalArgumentException("Edge already exists");
		
		Edge e = new Edge(source, destination);
		
		if(!adjList.containsKey(source))
		{
			adjList.put(source, new LinkedList<Edge>());
		}
		
		if(!adjList.containsKey(destination))
		{
			adjList.put(destination, new LinkedList<Edge>());
		}
		
		adjList.get(source).add(e);
		edges.add(source + "-" + destination);
	}
	
	/**
	 * Identify all paths between start and final nodes and their total
	 * duration as a Result, which is collected into a List, and returned.
	 * 
	 * Utilizes depth-first search algorithm to perform calculation.
	 * 
	 * @param durations Map<String, Integer> where key is activity.
	 */
	public ArrayList<Result> getPaths(HashMap<String, Integer> durations)
	{
		ArrayList<Result> results = new ArrayList<>();
		HashMap<String, Boolean> visited = new HashMap<>();
		for(String s : adjList.keySet())
		{
			visited.put(s, false);
		}
		ArrayList<String> path = new ArrayList<>();
		path.add(START_ACTIVITY);
		
		List<List<String>> paths = new ArrayList<List<String>>();
		DFS(paths, START_ACTIVITY, visited, path);
		
		// Allows for test code to not need to provide durations when that feature
		// is not under test.
		if(durations != null)
		{
			for(List<String> list : paths)
			{
				Result r = new Result();
				for(String s : list)
				{
					r.addPathActivity(s, durations.get(s));
				}
				results.add(r);
			}
		}
		// Sorts list in descending order by duration.
		Collections.sort(results);
		return results;
	}
	
	/**
	 * Recursive implementation of depth-first search algorithm to identify all paths between
	 * start and final nodes.
	 * 
	 * Algorithm:
	 * Set current node to visited
	 * If current node is the stop activity, then path has been found, so add to output
	 * For all edges in this node's adjacency list:
	 * 		If current edge's destination node is not visited, add it to path
	 * 		recur on destination
	 * 		Remove current edge's destination node from path.
	 * Set current node to not visited
	 * 
	 * @param output The final result
	 * @param current The current activity
	 * @param visited Map<String, Boolean> where key is activity and boolean is if it has been visited.
	 * @param path The current path in question.
	 * 
	 * @throws IllegalStateException when a visited node is reached, which is a cycle.
	 */
	private void DFS(List<List<String>> output, String current, Map<String, Boolean> visited, List<String> path) {
		
		visited.put(current, true);
		
		if(current.equals(stopActivity))
		{
			output.add(new ArrayList<String>(path));
		}
		
		for(Edge e : adjList.get(current))
		{
			if(!visited.get(e.destination))
			{
				path.add(e.destination);
				DFS(output, e.destination, visited, path);
				path.remove(e.destination);
			}
			else
			{
				throw new IllegalStateException("Cycle detected.");
			}
		}
		
		visited.put(current, false);
	}

	/**
	 * Allows calling code to set the stop activity only if it has not been set before.
	 * 
	 * @param stopActivity What the stop activity should be.
	 * 
	 * @throws IllegalArgumentException When the stop activity has previously been set.
	 */
	public void setStopActivity(String stopActivity)
	{
		if(this.stopActivity.isEmpty())
			this.stopActivity = stopActivity;
		else
			throw new IllegalArgumentException("Stop activity was previously configured");
	}
}
