package edu.asu.cse360.team9;
import java.util.*;
import java.util.Map.Entry;

/*
 * This class is the graph representation
 * 
 * 
 */
public class Graph {

	private ArrayList<String> edges;
	private HashMap<String, LinkedList<Edge>> adjList;
	private String startActivity = "";
	private String stopActivity = "";
	
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
	
	// DEBUGGING FUNCTION - TO BE REMOVED
	public void display()
	{
		 for(Entry<String, LinkedList<Edge>> m: adjList.entrySet()){
		        System.out.print(m.getKey()+"-->");
		        
		        String spacer = "";
		        for(Edge e : m.getValue())
		        {
		        	System.out.print(spacer + e);
		        	spacer = " --> ";
		        }
		        System.out.println();
		 }
		 
		 System.out.println("Start: " + startActivity);
		 System.out.println("Stop:  " + stopActivity);
	}
	
	public ArrayList<Result> getPaths(HashMap<String, Integer> durations)
	{
		// NOTE: To pass the tests, this must return them in descending order by duration.
		return null;
	}
	
	private ArrayList<Result> depthFirstSearch() {
		return null;
	}
	
	public void setStartActivity(String startActivity)
	{
		if(this.startActivity.isEmpty())
			this.startActivity = startActivity;
		else
			throw new IllegalArgumentException("Start activity was previously configured");
	}
	
	public void setStopActivity(String stopActivity)
	{
		if(this.stopActivity.isEmpty())
			this.stopActivity = stopActivity;
		else
			throw new IllegalArgumentException("Stop activity was previously configured");
	}
}
