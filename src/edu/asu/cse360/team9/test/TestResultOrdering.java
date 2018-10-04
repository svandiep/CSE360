package edu.asu.cse360.team9.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.asu.cse360.team9.*;

import java.util.*;

class TestResultOrdering {
	
	@Test
	void testOrdering01() {
		ArrayList<Result> results = new ArrayList<>();
		
		Result r1 = new Result();
		r1.addPathNode("A", 50);
		results.add(r1);
		
		Result r2 = new Result();
		r2.addPathNode("B", 75);
		results.add(r2);
		
		Result r3 = new Result();
		r3.addPathNode("C", 100);
		results.add(r3);
		
		Collections.sort(results);
		
		assertTrue(results.get(0).getDuration() == 100);
		assertTrue(results.get(1).getDuration() == 75);
		assertTrue(results.get(2).getDuration() == 50);
	}
}
