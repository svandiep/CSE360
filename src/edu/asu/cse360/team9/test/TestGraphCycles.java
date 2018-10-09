package edu.asu.cse360.team9.test;

import edu.asu.cse360.team9.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestGraphCycles {

	@Test
	void testCycle01() {
		/*
		 
		  		[A]<===>[B]
		  
		  Smallest cycle possible.
		  
		 */
		Graph g = new Graph();
		g.addEdge(Graph.START_ACTIVITY, "A");
		g.addEdge("A", "B");
		g.addEdge("B", "A");
		g.setStopActivity("B");
		
		assertThrows(IllegalStateException.class, () -> {
			g.getPaths(null); // we don't care about results here.
	    });
	}
	
	@Test
	void testCycle02() {
		/*
		  
		  		[A] --> [B] <===> [C]
		  
		  
		 	Extended idea of test 01.
		 */
		Graph g = new Graph();
		
		g.addEdge(Graph.START_ACTIVITY, "A");
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		g.addEdge("C", "B");
		g.setStopActivity("C");
		
		assertThrows(IllegalStateException.class, () -> {
			g.getPaths(null); // we don't care about results here.
	    });
		
	}
	
	@Test
	void testCycle03() {
		/*
		 		[A] --> [B] --> [C]
		         ^               |
		         +---------------+
		  
		  	Slightly different type of cycle.
		 */
		
		Graph g = new Graph();
		g.addEdge(Graph.START_ACTIVITY, "A");
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		g.addEdge("C", "A");
		g.setStopActivity("C");
		
		assertThrows(IllegalStateException.class, () -> {
			g.getPaths(null); // we don't care about results here.
	    });
	}
	
	@Test
	void testCycle04() {
		/*
		      	  	    			      	  4
		  		    				+---> [Foxtrot] --+
		  		  		   5	    |         ^       |
		[Alpha]---+---> [Delta] ----+         |  	  |        6
		      	  |        ^               	  |		  +----> [Gulf]
		          |		   +---------------<--+
		          |         	       		  |
		          |			8	     		  |
		          +---> [Epsilon] --------->--+	                      

	 */
		
		Graph g = new Graph();
		g.addEdge(Graph.START_ACTIVITY, "Alpha");
		g.addEdge("Alpha", "Delta");
		g.addEdge("Alpha", "Epsilon");
		g.addEdge("Delta", "Foxtrot");
		g.addEdge("Epsilon", "Foxtrot");
		g.addEdge("Epsilon", "Delta");
		g.addEdge("Foxtrot", "Gulf");
		g.setStopActivity("Gulf");
		
		//g.getPaths(null);
		
		assertThrows(IllegalStateException.class, () -> {
			g.getPaths(null); // we don't care about results here.
	    });
	}
	
	@Test
	void testCycle05() {
		/*
		 
		 	[A] --> [B] --> [C] --> [D] --> [E]
		             |               ^
		             +---------------+
		             
		    
		 */
		
		Graph g = new Graph();
		g.addEdge(Graph.START_ACTIVITY, "A");
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		g.addEdge("B", "D");
		g.addEdge("C", "D");
		g.addEdge("D", "E");
		g.setStopActivity("E");
		
		assertThrows(IllegalStateException.class, () -> {
			g.getPaths(null); // we don't care about results here.
	    });
	}
	
	@Test
	void testCycle06() {
		/*
		 
	 		[A] --> [B] --> [C] --> [D] --> [E] --> [F]
	             	 |               		 ^
	             	 +-----------------------+
	             
	    
		 */
		
		Graph g = new Graph();
		g.addEdge(Graph.START_ACTIVITY, "A");
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		g.addEdge("B", "E");
		g.addEdge("C", "D");
		g.addEdge("D", "E");
		g.addEdge("E", "F");
		g.setStopActivity("F");
		
		assertThrows(IllegalStateException.class, () -> {
			g.getPaths(null); // we don't care about results here.
	    });	
	}
}
