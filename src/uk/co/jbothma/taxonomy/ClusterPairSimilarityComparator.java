package uk.co.jbothma.taxonomy;

import java.util.Comparator;

public class ClusterPairSimilarityComparator implements Comparator<ClusterPair> {

	@Override
	public int compare(ClusterPair a, ClusterPair b) {
		float aSim = a.getSimilarity();
		float bSim = b.getSimilarity();
		if (aSim < bSim)
			return -1;
		else if (aSim == bSim)
			return 0;
		else
			return 1;
	}

}
