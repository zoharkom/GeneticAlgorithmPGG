package algorithm.operators;

import graph.Graph;
import algorithm.components.CandidateSolution;

public interface SolutionImprover {
	public CandidateSolution improveSolution(Graph g, CandidateSolution s);
}
