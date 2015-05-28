package algorithm;

import graph.Graph;
import algorithm.components.CandidateSolution;
import algorithm.components.PGGAlgorithmConfiguration;

public interface PGGAlgorithm {
	public void algorithmConfig(PGGAlgorithmConfiguration conf);
	public CandidateSolution findSolution(Graph g);
}
