package algorithm.operators.mutation;

import java.util.Random;

import algorithm.components.CandidateSolution;

public class SimpleMutationOperator implements MutationOperator {

	private Random rand;
	private double mutationProb;
	
	public SimpleMutationOperator(Random r, double mp) {
		this.rand = r;
		this.mutationProb = mp;
	}
	
	@Override
	public CandidateSolution doMutation(CandidateSolution s) {
		CandidateSolution afterMutation = new CandidateSolution(s);
	    for(int i=0 ; i < s.getSize() ; i++){
	    	if(rand.nextDouble() < mutationProb){
	    		boolean newVal = !(s.getAllele(i));
		    	afterMutation.setAllele(i,newVal);	
	    	}
	    }
	    
	    return afterMutation;
	}

}
