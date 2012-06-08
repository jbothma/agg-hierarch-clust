package uk.co.jbothma.taxonomy;

public class ClusterPair {
	float similarity;
	Cluster a, b;
	
	ClusterPair(Cluster a, Cluster b) {
		this.a = a;
		this.b = b;
	}
	
	public Cluster getA() {
		return a;
	}
	
	public Cluster getB() {
		return b;
	}
	
	public float getSimilarity() {
		return similarity;
	}
	
	public float calculateSimilarity() {
		similarity = a.similarityTo(b);
		return similarity;
	}
	
	@Override public String toString() {
		return "ClusterPair[" + similarity + " " + a + ", " + b + "]";
	}
}
