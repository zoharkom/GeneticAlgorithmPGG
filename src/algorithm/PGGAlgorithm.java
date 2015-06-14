package algorithm;

import java.rmi.UnexpectedException;
import java.util.ArrayList;

import graph.Graph;
import algorithm.components.CandidateSolution;

public interface PGGAlgorithm {
	public CandidateSolution findSolution(Graph g, ArrayList<double[]> stat) throws UnexpectedException;
}
