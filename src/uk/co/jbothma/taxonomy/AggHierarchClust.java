package uk.co.jbothma.taxonomy;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AggHierarchClust {

	Set<Cluster> clusters;
	Set<ClusterPair> pairs;
	
	public AggHierarchClust(String[][] terms) {
		clusters = new HashSet<Cluster>();
		for (String[] primTerm : terms) {
			clusters.add(new Cluster(new Term(primTerm)));
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[][] terms = 
			{{"a"}, {"b"}, {"c"},
			 {"a","b"}, {"a","c"}, {"b","c"}, {"b","d"}, {"d","b"},
			 {"a","b","c"}, {"b","c","d"},
			 {"a","b","c","d"}, {"a","c","b","d"}};
		AggHierarchClust clustering = new AggHierarchClust(terms);
		clustering.cluster();
	}
	
	public void cluster() {
		// TODO: Stop clustering when clusters don't have common heads any more
		while (clusters.size() > 1) {
			makePairs();
			calculatePairSimilarity();
			mergeMaximalPair();
		}
	}
	
	private void makePairs() {
		pairs = new HashSet<ClusterPair>();
		for (Cluster clustA : clusters.toArray(new Cluster[]{})) {
			for (Cluster clustB : clusters.toArray(new Cluster[]{})) {
				if (clustA.equals(clustB)) {
					continue;
				}
				ClusterPair pair = new ClusterPair(clustA, clustB);
				pairs.add(pair);
			}
		}
	}
	
	private void calculatePairSimilarity() {
		for (ClusterPair pair : pairs) {
			pair.calculateSimilarity();
		}
	}
	
	private void mergeMaximalPair() {
		ClusterPair max = Collections.max(pairs, new ClusterPairSimilarityComparator());
		System.out.println("Max: " + max);
		Cluster merged = new Cluster(max.getA().getTerms(), max.getB().getTerms());
		clusters.remove(max.getA());
		clusters.remove(max.getB());
		clusters.add(merged);
	}
}
