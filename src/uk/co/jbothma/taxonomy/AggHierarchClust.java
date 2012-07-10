package uk.co.jbothma.taxonomy;

import java.util.HashSet;
import java.util.Iterator;
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
			{{"a"}, {"b"}, {"c"}, {"e"},
			 {"a","b"}, {"a","c"}, {"b","c"}, {"b","d"}, {"d","b"},
			 {"a","b","c"}, {"b","c","d"},
			 {"a","b","c","d"}, {"a","c","b","d"}};
		AggHierarchClust clustering = new AggHierarchClust(terms);
		clustering.cluster();
	}
	
	public void cluster() {
		ClusterPair maxPair;
		do {
			maxPair = maxSimilarPair();
		} while (mergePair(maxPair));
	}
	
	public Set<Cluster> getClusters() {
		return clusters;
	}
			
	private ClusterPair maxSimilarPair() {
		Iterator<Cluster> clustIter;
		Cluster clustB;
		ClusterPair pair; // current pair
		ClusterPair maxPair = null;
		
		for (Cluster clustA : clusters.toArray(new Cluster[]{})) {
			clustIter = clusters.iterator();
			while (clustIter.hasNext()) {
				clustB = clustIter.next();
				if (clustA.equals(clustB)) {
					continue;
				}
				pair = new ClusterPair(clustA, clustB);
				pair.calculateSimilarity();
				if (maxPair == null || pair.getSimilarity() > maxPair.getSimilarity()) {
					maxPair = pair;
				}
			}
		}
		return maxPair;
	}
	
	/**
	 * 
	 * @return true after merging clusters whose heads are equal
	 * 		   false without merging clusters whose heads don't equal
	 */
	private boolean mergePair(ClusterPair maxPair) {
		if (!maxPair.getA().headsEqual(maxPair.getB()))
			return false;
		Cluster merged = new Cluster(maxPair.getA().getTerms(), maxPair.getB().getTerms());
		clusters.remove(maxPair.getA());
		clusters.remove(maxPair.getB());
		clusters.add(merged);
		return true;
	}
}
