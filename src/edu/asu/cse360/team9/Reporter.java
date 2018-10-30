package edu.asu.cse360.team9;

import java.util.*;
import java.io.*;

/**
 * The Reporter provides the functionality to export a network
 * configuration, and its path data to a text document of the
 * user's choosing.
 */
public class Reporter 
{
	private static int LINE_WIDTH = 80;
	private String path;
	private String title;
	private String createdOn;
	private ArrayList<Record> records;
	private ArrayList<Result> results;
	
	public Reporter(ArrayList<Record> records, ArrayList<Result> results, String path, String title, String createdOn)
	{
		this.path = path;
		this.title = title;
		this.createdOn = createdOn;
		Collections.sort(records);
		this.records = records;
		this.results = results;
	}
	
	public void write() throws IOException
	{
		String newline = System.getProperty( "line.separator" );
		String divider = "";
		for(int i=0; i < LINE_WIDTH; i++)
			divider += "-";
		try {
			FileWriter w = new FileWriter(path, false);
			w.write(title + newline + newline);

			String pad = getPadding("Activities", "Durations");
			w.write("Activities" + pad + "Durations" + newline);
			w.write(divider + newline);
			for(Record r : records)
			{
				pad = getPadding(r.getActivity(), ""+r.getDuration());
				w.write(r.getActivity() + pad + r.getDuration() + newline);
			}
			w.write(newline);
			w.write(divider + newline + newline);
			w.write("Paths" + newline);
			w.write(divider + newline);
			
			for(Result r : results)
			{
				w.write("Path:\t\t");
				w.write(r.getPath());
				w.write(newline);
				w.write("Duration:\t");
				w.write(r.getDuration() + newline + newline);
			}
			
			w.write(divider + newline);
			pad = getPadding("Created On: ", createdOn);
			w.write(pad + "Created On: " + createdOn + newline);
			
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IOException(e);
			//e.printStackTrace();
		}
	}
	
	private String getPadding(String left, String right)
	{
		String pad = "";
		int header_pad = LINE_WIDTH - (left.length() + right.length());
		System.out.println(header_pad);
		for(int i=0; i < header_pad; i++)
			pad += " ";
		return pad;
	}
	
	/* UNCOMMENT TO SEE IN ACTION
	public static void main(String[] args)
	{
		ArrayList<Record> records = new ArrayList<>();
		records.add(new Record("A", 1, "".split(",")));
		records.add(new Record("B", 2, "A".split(",")));
		records.add(new Record("C", 3, "A".split(",")));
		records.add(new Record("D", 4, "B,C".split(",")));
		records.add(new Record("E", 5, "D".split(",")));
		records.add(new Record("F", 6, "D".split(",")));
		records.add(new Record("G", 7, "D".split(",")));
		records.add(new Record("H", 8, "E,F,G".split(",")));
		
		HashMap<String, Integer> durations = new HashMap<String, Integer>() {
			{ 
				put(Graph.START_ACTIVITY, 0);
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
		
		Graph g = new Graph();
		g.setStopActivity("H");
		
		g.addEdge(Graph.START_ACTIVITY, "A");
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
		
		ArrayList<Result> results = g.getPaths(durations);
		
		Reporter r = new Reporter(records, results, "example_file.txt", "NETWORK DIAGRAM 001", "10/20/2018");
		r.write();
	}
	*/
}
