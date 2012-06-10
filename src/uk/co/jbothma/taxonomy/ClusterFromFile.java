package uk.co.jbothma.taxonomy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ClusterFromFile {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		String inFilename = "/home/jdb/thesis/results/CValueDemo.jrc_2006_100_top_picks.500";
		String inFilename = args[0];
		ArrayList<String> termParts;
		ArrayList<String[]> terms;
		String line;
		int partCount = 0;
		
		terms = new ArrayList<String[]>();
		BufferedReader inReader = new BufferedReader(new FileReader(inFilename));
		while ((line = inReader.readLine()) != null)   {
		  termParts = new ArrayList<String>(Arrays.asList(line.split(" ")));
		  termParts = new ArrayList<String>(termParts.subList(5, termParts.size()-1));
		  partCount += termParts.size();
		  terms.add(termParts.toArray(new String[]{}));
		}
		System.out.println(partCount + " parts in " + terms.size() + " terms.");
		AggHierarchClust clustering = new AggHierarchClust(terms.toArray(new String[][]{}));
		terms = null; // free some memory
		clustering.cluster();
	}

}
