package edu.asu.cse360.team9.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import edu.asu.cse360.team9.*;

class TestGraphPaths {

	@Test
	void testPath01() {
		/*
		     1       2
	 [S] -> [A] --> [B]
	  
	 		Smallest path possible = A, B : 3
	  
		 */
		
		ArrayList<Result> expected_paths = new ArrayList<>();
		Result result = new Result();
		result.addPathActivity("A", 1);
		result.addPathActivity("B", 2);
		expected_paths.add(result);
		
		Graph g = new Graph();
		//g.setStartActivity("A");
		g.setStopActivity("B");
		
		g.addEdge("A", "B");
		HashMap<String, Integer> durations = new HashMap<String, Integer>() {
			{ 
				put("A", 1);
				put("B", 2);
			}
		};
		ArrayList<Result> actual = g.getPaths(durations);
		
		assertTrue(expected_paths.size() == actual.size());
		
		for(int i = 0; i < expected_paths.size(); i++)
		{
			assertTrue( actual.contains(expected_paths.get(i)) );
		}
	}
	
	@Test
	void testPath02() {
		/*
		     1       2       3
	 		[A] --> [B] --> [C]
	 
			Path = A, B, C : 6
 
		 */
		
		ArrayList<Result> expected_paths = new ArrayList<>();
		Result result = new Result();
		result.addPathActivity("A", 1);
		result.addPathActivity("B", 2);
		result.addPathActivity("C", 3);
		expected_paths.add(result);
		
		Graph g = new Graph();
		//g.setStartActivity("A");
		g.setStopActivity("C");
		
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		
		HashMap<String, Integer> durations = new HashMap<String, Integer>() {
			{ 
				put("A", 1);
				put("B", 2);
				put("C", 3);
			}
		};
		ArrayList<Result> actual = g.getPaths(durations);
		
		assertTrue(expected_paths.size() == actual.size());
		
		for(int i = 0; i < expected_paths.size(); i++)
		{
			assertTrue( actual.contains(expected_paths.get(i)) );
		}
	}
	
	@Test 
	void testPath03() {
		/*
		     1       2           4
			[A] --> [B] --+---> [D]
			 |            |
			 +----> [C] --+
	                 3
	 
			Path = 	A, C, D : 8
					A, B, D : 7

		 */
		
		ArrayList<Result> expected_paths = new ArrayList<>();
		
		Result p1 = new Result();
		p1.addPathActivity("A", 1);
		p1.addPathActivity("C", 3);
		p1.addPathActivity("D", 4);
		expected_paths.add(p1);
		
		Result p2 = new Result();
		p2.addPathActivity("A", 1);
		p2.addPathActivity("B", 2);
		p2.addPathActivity("D", 4);
		expected_paths.add(p2);
		
		Graph g = new Graph();
		//g.setStartActivity("A");
		g.setStopActivity("D");
		
		g.addEdge("A", "B");
		g.addEdge("A", "C");
		g.addEdge("B", "D");
		g.addEdge("C", "D");
		
		HashMap<String, Integer> durations = new HashMap<String, Integer>() {
			{ 
				put("A", 1);
				put("B", 2);
				put("C", 3);
				put("D", 4);
			}
		};
		ArrayList<Result> actual = g.getPaths(durations);
		
		assertTrue(expected_paths.size() == actual.size());
		
		for(int i = 0; i < expected_paths.size(); i++)
		{
			assertTrue( actual.contains(expected_paths.get(i)) );
		}
	}
	
	@Test
	void testPath04() {
		/*
		     1       2
			[A] --> [B] --+
			 |       3    |      5
			 +----> [C] --+---> [E]
	         |       4    |
	         +----> [D] --+
	
			Path = 	A, D, E : 10
					A, C, E : 9
					A, B, E : 8

		 */
		
		ArrayList<Result> expected_paths = new ArrayList<>();
		
		Result p1 = new Result();
		p1.addPathActivity("A", 1);
		p1.addPathActivity("D", 4);
		p1.addPathActivity("E", 5);
		expected_paths.add(p1);
		
		Result p2 = new Result();
		p2.addPathActivity("A", 1);
		p2.addPathActivity("C", 3);
		p2.addPathActivity("E", 5);
		expected_paths.add(p2);
		
		Result p3 = new Result();
		p3.addPathActivity("A", 1);
		p3.addPathActivity("B", 2);
		p3.addPathActivity("E", 5);
		expected_paths.add(p3);
		
		Graph g = new Graph();
		//g.setStartActivity("A");
		g.setStopActivity("E");
		
		g.addEdge("A", "B");
		g.addEdge("B", "E");
		
		g.addEdge("A", "C");
		g.addEdge("C", "E");
		
		g.addEdge("A", "D");
		g.addEdge("D", "E");
		
		HashMap<String, Integer> durations = new HashMap<String, Integer>() {
			{ 
				put("A", 1);
				put("B", 2);
				put("C", 3);
				put("D", 4);
				put("E", 5);
			}
		};
		ArrayList<Result> actual = g.getPaths(durations);
		
		assertTrue(expected_paths.size() == actual.size());
		
		for(int i = 0; i < expected_paths.size(); i++)
		{
			assertTrue( actual.contains(expected_paths.get(i)) );
		}
	}
	
	@Test
	void testPath05() {
		/*
		     1       2
			[A] --> [B] --+      6
			 |       3    +---> [F] ------+
			 +----> [C] --+               |        8
	         |       4                    +-----> [H]
	         +----> [D] --+      7        |
	         |       5    +---> [G] ------+
	         +----> [E] --+
	
			Path = 	A, E, G, H : 21
					A, D, G, H : 20
					A, C, F, H : 18
					A, B, F, H : 17
		 */
		ArrayList<Result> expected_paths = new ArrayList<>();
		
		Result p1 = new Result();
		p1.addPathActivity("A", 1);
		p1.addPathActivity("E", 5);
		p1.addPathActivity("G", 7);
		p1.addPathActivity("H", 8);
		expected_paths.add(p1);
		
		Result p2 = new Result();
		p2.addPathActivity("A", 1);
		p2.addPathActivity("D", 4);
		p2.addPathActivity("G", 7);
		p2.addPathActivity("H", 8);
		expected_paths.add(p2);
		
		Result p3 = new Result();
		p3.addPathActivity("A", 1);
		p3.addPathActivity("C", 3);
		p3.addPathActivity("F", 6);
		p3.addPathActivity("H", 8);
		expected_paths.add(p3);
		
		Result p4 = new Result();
		p4.addPathActivity("A", 1);
		p4.addPathActivity("B", 2);
		p4.addPathActivity("F", 6);
		p4.addPathActivity("H", 8);
		expected_paths.add(p4);
		
		Graph g = new Graph();
		//g.setStartActivity("A");
		g.setStopActivity("H");
		
		g.addEdge("A", "B");
		g.addEdge("B", "F");
		
		g.addEdge("A", "C");
		g.addEdge("C", "F");
		
		g.addEdge("F", "H");
		
		g.addEdge("A", "D");
		g.addEdge("D", "G");
		
		g.addEdge("A", "E");
		g.addEdge("E", "G");
		
		g.addEdge("G", "H");

		
		HashMap<String, Integer> durations = new HashMap<String, Integer>() {
			{ 
				put("A", 1);
				put("B", 2);
				put("C", 3);
				put("D", 4);
				put("E", 5);
				put("F", 6);
				put("G", 7);
				put("H", 8);
			}
		};
		ArrayList<Result> actual = g.getPaths(durations);
		
		assertTrue(expected_paths.size() == actual.size());
		
		for(int i = 0; i < expected_paths.size(); i++)
		{
			assertTrue( actual.contains(expected_paths.get(i)) );
		}
	}
	
	@Test
	void testPath06() {
		/*
	     1       2
		[A] --> [B] --+      4             5
		 |       3    +---> [D] ---+----> [E] --+
		 +----> [C] --+            |       6    |       8
		                           +----> [F] --+----> [H]
		                           |       7    |
		                           +----> [G] --+                      

		Path = 	A, C, D, G, H : 23
				A, C, D, F, H : 22
				A, C, D, E, H : 21
				A, B, D, G, H : 22
				A, B, D, F, H : 21
				A, B, D, E, H : 20
	 */
		
		ArrayList<Result> expected_paths = new ArrayList<>();
		
		Result p1 = new Result();
		p1.addPathActivity("A", 1);
		p1.addPathActivity("C", 3);
		p1.addPathActivity("D", 4);
		p1.addPathActivity("G", 7);
		p1.addPathActivity("H", 8);
		expected_paths.add(p1);
		
		Result p2 = new Result();
		p2.addPathActivity("A", 1);
		p2.addPathActivity("C", 3);
		p2.addPathActivity("D", 4);
		p2.addPathActivity("F", 6);
		p2.addPathActivity("H", 8);
		expected_paths.add(p2);
		
		Result p3 = new Result();
		p3.addPathActivity("A", 1);
		p3.addPathActivity("C", 3);
		p3.addPathActivity("D", 4);
		p3.addPathActivity("E", 5);
		p3.addPathActivity("H", 8);
		expected_paths.add(p3);
		
		Result p4 = new Result();
		p4.addPathActivity("A", 1);
		p4.addPathActivity("B", 2);
		p4.addPathActivity("D", 4);
		p4.addPathActivity("G", 7);
		p4.addPathActivity("H", 8);
		expected_paths.add(p4);
		
		Result p5 = new Result();
		p5.addPathActivity("A", 1);
		p5.addPathActivity("B", 2);
		p5.addPathActivity("D", 4);
		p5.addPathActivity("F", 6);
		p5.addPathActivity("H", 8);
		expected_paths.add(p5);
		
		Result p6 = new Result();
		p6.addPathActivity("A", 1);
		p6.addPathActivity("B", 2);
		p6.addPathActivity("D", 4);
		p6.addPathActivity("E", 5);
		p6.addPathActivity("H", 8);
		expected_paths.add(p6);
		
		Graph g = new Graph();
		//g.setStartActivity("A");
		g.setStopActivity("H");
		
		g.addEdge("A", "B");
		g.addEdge("A", "C");
		
		g.addEdge("B", "D");
		g.addEdge("C", "D");
		
		g.addEdge("D", "E");
		g.addEdge("D", "F");
		g.addEdge("D", "G");
		
		g.addEdge("E", "H");
		g.addEdge("F", "H");
		g.addEdge("G", "H");
		
		HashMap<String, Integer> durations = new HashMap<String, Integer>() {
			{ 
				put("A", 1);
				put("B", 2);
				put("C", 3);
				put("D", 4);
				put("E", 5);
				put("F", 6);
				put("G", 7);
				put("H", 8);
			}
		};
		ArrayList<Result> actual = g.getPaths(durations);
		
		assertTrue(expected_paths.size() == actual.size());
		
		for(int i = 0; i < expected_paths.size(); i++)
		{
			assertTrue( actual.contains(expected_paths.get(i)) );
		}
	}
}
