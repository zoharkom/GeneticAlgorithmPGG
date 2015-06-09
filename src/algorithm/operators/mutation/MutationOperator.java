package algorithm.operators.mutation;

import algorithm.components.CandidateSolution;


public interface MutationOperator {
	public CandidateSolution doMutation(CandidateSolution s);
}
