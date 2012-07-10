package uk.co.jbothma.taxonomy;

import java.util.Arrays;

public class Term {
	String[] parts;
	
	public Term(String[] parts) {
		this.parts = parts;
	}
	
	@Override
	public boolean equals(Object that) {
		Term thatTerm = (Term)that;		
		return Arrays.equals(this.parts, thatTerm.getParts());
	}
	
	/**
	 * Hash code for same series of parts equals.
	 * Hash code for same parts in different order is different.
	 * This is really naive. 
	 */
	@Override
	public int hashCode() {
		int code = 0;
		for (int i = 0; i < parts.length; i++) {
			code += (i+1)*parts[i].hashCode();
		}
		code += parts.length;
		
		return code;
	}
	
	@Override
	public String toString() {
		return "Term" + Arrays.deepToString(parts);
	}
	
	public String[] getParts() {
		return parts;
	}
	
	public String getHead() {
		return parts[parts.length-1];
	}
	
	public float similarityTo(Term that) {
		float similarity;
		float shareCount = 0F;
		
		if (this.getHead().equals(that.getHead())) {
			similarity = 1F;
		} else {
			similarity = 0F;
		}
		
		for (String aPart : this.parts) {
			for (String bPart : that.getParts()) {
				if (aPart.equals(bPart)) {
					shareCount++;
				}
			}
		}
		
		similarity += shareCount/((float)this.parts.length + (float)that.getParts().length);
		return similarity;
	}
}
