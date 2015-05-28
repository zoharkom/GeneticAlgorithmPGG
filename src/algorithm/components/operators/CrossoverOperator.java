package algorithm.components.operators;

import algorithm.components.CandidateSolution;

public interface CrossoverOperator {
	public CandidateSolution doCrossover(CandidateSolution s1, CandidateSolution s2);
}
