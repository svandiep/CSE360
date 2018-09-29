package edu.asu.cse360.team9;
import java.util.*;
import java.util.Map.Entry;

/*
 * This class is the graph representation
 * 
 * 
 */
public class Graph {

	private HashMap<String, LinkedList<Edge>> adjList;
	
	private class Edge
	{
		String source;
		String destination;
		int weight;
		
		Edge(String source, String destination, int weight)
		{
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}
		
		public String toString()
		{
			return "[" + source + ", " + destination + ", " + weight + "]";
		}
	}
	
	
	public Graph()
	{
		adjList = new HashMap<String, LinkedList<Edge>>();
	}
	
	public void addEdge(String source, String destination, int weight)
	{
		Edge e = new Edge(source, destination, weight);
		
		if(!adjList.containsKey(source))
		{
			adjList.put(source, new LinkedList<Edge>());
		}
		
		if(!adjList.containsKey(destination))
		{
			adjList.put(destination, new LinkedList<Edge>());
		}
		
		adjList.get(source).add(e);
	}
	
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
	}
	
	/*
	public static void main(String[] args)
	{
		// From PERT slides:
		/*
		 	Activity		Predecessor		Weeks		comment
		 	{dest}			{source}
		 	A				--				2			0 (not entirely sure about this yet)
		 	B				A				4			1
		 	C				B				5			2
		 	D				B,F				6			3
		 	E				C,D				3			4
		 	F				A				5			5
		 	
		 	
		 	   
		 	Ex: 
		 	
		 	
		 	     2         4        5
		 	[A] ----> [B] ----> [C] -------+
		 	 |         |   4               +----> [E] 
		 	 |         +------+          6 |
		 	 |      2         +----> [D] --+
		     +--------> [F] --+ 5
		 
		 
		 	Algorithm:
		 	1) Build paired list of activities and durations P
		 	2) Go through list, and build edge where source is predecessor, destination
		 	   is activity, and duration is P[predecessor].duration.
		 
		 //
		
		HashMap<String, Integer> activities = new HashMap<String, Integer>();
		activities.put("A", 2);
		activities.put("B", 4);
		activities.put("C", 5);
		activities.put("D", 6);
		activities.put("E", 3);
		activities.put("F", 5);
		
        Graph graph = new Graph();
        graph.addEdge("A", "B", activities.get("A"));		// 1
        graph.addEdge("B", "C", activities.get("B"));		// 2
        
        graph.addEdge("B", "D", activities.get("B"));		// 3
        graph.addEdge("F", "D", activities.get("F"));
        
        graph.addEdge("C", "E", activities.get("C"));		// 4
        graph.addEdge("D", "E", activities.get("D"));
        
        graph.addEdge("A", "F", activities.get("A"));		// 5
        graph.display();
		
	}
	*/
}
