package algorithm;

import java.rmi.UnexpectedException;

import graph.Graph;
import algorithm.components.CandidateSolution;

public interface PGGAlgorithm {
	public CandidateSolution findSolution(Graph g) throws UnexpectedException;
}
