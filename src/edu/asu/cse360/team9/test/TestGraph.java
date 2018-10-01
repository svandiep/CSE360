package edu.asu.cse360.team9.test;

import edu.asu.cse360.team9.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestGraph {

	@Test
	void testCycle01() {
		/*
		 
		  		[A]<===>[B]
		  
		  Smallest cycle possible.
		  
		 */
		Graph g = new Graph();
		g.addEdge("A", "B", 1);
		g.addEdge("B", "A", 1);
		
		fail("Not yet implemented");
	}
	
	void testCycle02() {
		/*
		  
		  		[A] --> [B] <===> [C]
		  
		  
		 	Extended idea of test 01.
		 */
		Graph g = new Graph();
		g.addEdge("A", "B", 1);
		g.addEdge("B", "C", 1);
		g.addEdge("C", "B", 1);
		
		fail("Not yet implemented");
		
	}
	
	void testCycle03() {
		/*
		 		[A] --> [B] --> [C]
		         ^               |
		         +---------------+
		  
		  	Slightly different type of cycle.
		 */
		
		Graph g = new Graph();
		g.addEdge("A", "B", 1);
		g.addEdge("B", "C", 1);
		g.addEdge("C", "A", 1);
		
		fail("Not yet implemented");
	}

}
