package algorithm.operators.fitness;

import graph.Graph;
import algorithm.components.CandidateSolution;

public interface FitnessEvaluator {

	public int evaluate(Graph g, CandidateSolution currentSol);

}
