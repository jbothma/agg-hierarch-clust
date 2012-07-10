package uk.co.jbothma.taxonomy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import uk.co.jbothma.taxonomy.Term;

public class Cluster {
	private Set<Term> terms;
	
	public Cluster(Term term) {
		terms = new HashSet<Term>();
		terms.add(term);
	}
	
	public Cluster(Set<Term> termsA, Set<Term> termsB) {
		terms = new HashSet<Term>();
		terms.addAll(termsA);
		terms.addAll(termsB);
	}

	@Override
	public boolean equals(Object that) {
		Cluster thatCluster = (Cluster) that;
		return terms.equals(thatCluster.getTerms());
	}
	
	@Override
	public int hashCode() {
		return terms.hashCode();
	}
	
	public Set<Term> getTerms() {
		return terms;
	}
	
	@Override
	public String toString() {
		return "Cluster" + Arrays.deepToString(terms.toArray());
	}
	
	public float similarityTo(Cluster that) {
		float similaritySum = 0;
		Set<Set<Term>> pairs = new HashSet<Set<Term>>();
		for (Term myTerm : terms.toArray(new Term[]{})) {
			for (Term theirTerm : that.getTerms().toArray(new Term[]{})) {
				if (myTerm.equals(theirTerm)) {
					System.err.println("My term " + myTerm + " equals theirs?!");
					continue;
				}
				Set<Term> pair = new HashSet<Term>();
				pair.add(myTerm);
				pair.add(theirTerm);
				pairs.add(pair);
			}
		}
		for (Set<Term> pair : pairs) {
			Term[] termPair = pair.toArray(new Term[]{});
			similaritySum += termPair[0].similarityTo(termPair[1]); 
		}
		return similaritySum / (terms.size()*that.getTerms().size());
	}
	
	public boolean headsEqual(Cluster that) {
		String thisHead = this.terms.iterator().next().getHead();
		for (Term thatTerm : that.getTerms()) {
			if (!thatTerm.getHead().equals(thisHead))
				return false;
		}
		return true;
	}
}
