package algorithm.operators.fitness;

import java.rmi.UnexpectedException;

import graph.Graph;
import algorithm.components.CandidateSolution;

public interface FitnessEvaluator {

	public double evaluate(Graph g, CandidateSolution currentSol) throws UnexpectedException;

}
