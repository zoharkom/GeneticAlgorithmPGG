package algorithm.operators.improve;

import java.rmi.UnexpectedException;

import graph.Graph;
import algorithm.components.CandidateSolution;

public interface SolutionImprover {
	public CandidateSolution improveSolution(Graph g, CandidateSolution s) throws UnexpectedException;
}
