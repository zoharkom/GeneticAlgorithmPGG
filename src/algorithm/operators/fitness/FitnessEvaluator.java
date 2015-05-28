package algorithm.operators.fitness;

import algorithm.components.CandidateSolution;

public interface FitnessEvaluator {

	int evaluate(CandidateSolution currentSol);

}
