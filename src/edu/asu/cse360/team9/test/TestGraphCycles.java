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
		g.addEdge("A", "B");
		g.addEdge("B", "A");
		
		fail("Not yet implemented");
	}
	
	@Test
	void testCycle02() {
		/*
		  
		  		[A] --> [B] <===> [C]
		  
		  
		 	Extended idea of test 01.
		 */
		Graph g = new Graph();
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		g.addEdge("C", "B");
		
		fail("Not yet implemented");
		
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
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		g.addEdge("C", "A");
		
		fail("Not yet implemented");
	}
}
