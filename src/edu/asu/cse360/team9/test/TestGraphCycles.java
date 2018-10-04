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
		g.setStartActivity("A");
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
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		g.addEdge("C", "B");
		g.setStartActivity("A");
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
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		g.addEdge("C", "A");
		g.setStartActivity("A");
		g.setStopActivity("C");
		
		assertThrows(IllegalStateException.class, () -> {
			g.getPaths(null); // we don't care about results here.
	    });
	}
}
