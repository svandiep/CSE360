package edu.asu.cse360.team9.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.asu.cse360.team9.*;

class TestGraphEdges {

	@Test
	void testDuplicate01() {
		Graph g = new Graph();
		g.addEdge("A", "B");
		
		assertThrows(IllegalArgumentException.class, () -> {
	        g.addEdge("A", "B");
	    });
	}
	
	@Test
	void testDuplicate02() {
		Graph g = new Graph();
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		
		assertThrows(IllegalArgumentException.class, () -> {
	        g.addEdge("A", "B");
	    });
	}
	
	@Test
	void testDuplicate03() {
		Graph g = new Graph();
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		g.addEdge("C", "D");
		g.addEdge("D", "E");
		g.addEdge("E", "F");
		g.addEdge("F", "G");
		g.addEdge("G", "H");
		
		assertThrows(IllegalArgumentException.class, () -> {
	        g.addEdge("F", "G");
	    });
	}
}
