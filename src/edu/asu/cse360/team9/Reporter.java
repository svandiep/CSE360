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

// THIS IS THE TEMPLATE. Numbers are not to be included.
// Durations are right-justified for activities listing
/*
<TITLE OF REPORT>
Created On: [Creation Date]

01234567890123456789012345678901234567890123456789012345678901234567890123456789

Activities:															   Durations
[Activity.........................................]					  [duration]
[Activity.........................................]					  [duration]
[Activity.........................................]					  [duration]
[Activity.........................................]					  [duration]
[Activity.........................................]					  [duration]

Paths:
Path:     [path,path,path......................................................]
Duration: [duration]

Path:     [path,path,path......................................................]
Duration: [duration]

Path:     [path,path,path......................................................]
Duration: [duration]

Path:     [path,path,path......................................................]
Duration: [duration]
*/
	}
}
