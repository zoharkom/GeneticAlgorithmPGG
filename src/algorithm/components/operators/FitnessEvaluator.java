package algorithm.components.operators;

import algorithm.components.CandidateSolution;

public interface FitnessEvaluator {

	int evaluate(CandidateSolution currentSol);

}
