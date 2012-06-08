package uk.co.jbothma.taxonomy;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AggHierarchClust {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[][] terms = 
			{{"a"}, {"b"}, {"c"},
			 {"a","b"}, {"a","c"}, {"b","c"}, {"b","d"}, {"d","b"},
			 {"a","b","c"}, {"b","c","d"},
			 {"a","b","c","d"}, {"a","c","b","d"}};
		Set<Cluster> clusters = new HashSet<Cluster>();
		for (String[] primTerm : terms) {
			clusters.add(new Cluster(new Term(primTerm)));
		}
		Set<Set<Cluster>> pairs = new HashSet<Set<Cluster>>();
		for (Cluster clustA : clusters.toArray(new Cluster[]{})) {
			for (Cluster clustB : clusters.toArray(new Cluster[]{})) {
				if (clustA.equals(clustB)) {
					continue;
				}
				Set<Cluster> pair = new HashSet<Cluster>();
				pair.add(clustA);
				pair.add(clustB);
				pairs.add(pair);
			}
		}
		
		for (Set<Cluster> pair : pairs) {
			Cluster[] clusterPair = pair.toArray(new Cluster[]{});
			float similarity = clusterPair[0].similarityTo(clusterPair[1]);
			System.out.println(similarity + "\t" + Arrays.deepToString(clusterPair));
		}
	}	
}
