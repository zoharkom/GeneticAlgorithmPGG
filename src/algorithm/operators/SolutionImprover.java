package algorithm.operators;

import algorithm.components.CandidateSolution;

public interface SolutionImprover {
	public CandidateSolution improveSolution(CandidateSolution s);
}
