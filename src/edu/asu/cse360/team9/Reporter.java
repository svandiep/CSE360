package edu.asu.cse360.team9;

import java.util.*;

/**
 * The Reporter provides the functionality to export a network
 * configuration, and its path data to a text document of the
 * user's choosing.
 */
public class Reporter 
{
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
		this.records = records;
		this.results = results;
	}
	
	public void write()
	{
		// TODO: Implement writing functionality
	}
}
