package algorithm.components.operators;

import algorithm.components.CandidateSolution;

public interface MutationOperator {
	public CandidateSolution doMutation(CandidateSolution s);
}
