package uk.co.jbothma.taxonomy;

import java.util.Arrays;
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
		
		Set pairs = new HashSet();

		for (String[] termA : terms) {
			for (String[] termB : terms) {
				Set pair = new HashSet();
				pair.add(termA);
				pair.add(termB);

				if (termA == termB) {
					System.out.println("Skipping comparison to self for " + Arrays.toString(termA));
					continue;
				}

				pairs.add(pair);
			}
		}
		Iterator pairIter = pairs.iterator();
		while (pairIter.hasNext()) {
			Set pair = (Set) pairIter.next();
			String[][] termPair = (String[][]) pair.toArray(new String[][]{});
//			System.out.println(Arrays.deepToString(termPair));
			float similarity = termSim(termPair[0], termPair[1]);
			System.out.println(similarity + "\t" + Arrays.toString(termPair[0]) + "\t" + Arrays.toString(termPair[1]));
		}
	}

	static float termSim(String[] termA, String[] termB) {
		float similarity;
		float shareCount = 0F;
		
		if (termHead(termA).equals(termHead(termB))) {
			similarity = 0.5F;
		} else {
			similarity = 0F;
		}
		
		for (String aPart : termA) {
			for (String bPart : termB) {
				if (aPart.equals(bPart)) {
					shareCount++;
				}
			}
		}
		
		similarity += shareCount/((float)termA.length+termB.length);
		return similarity;
	}
	
	static String termHead(String[] term) {
		return term[term.length-1];
	}
}
