package algorithm.operators.improve;

import java.rmi.UnexpectedException;

import graph.Graph;
import algorithm.BestResponseAlgorithm;
import algorithm.components.CandidateSolution;

public class BestResponseImprover implements SolutionImprover {

	@Override
	public CandidateSolution improveSolution(Graph g, CandidateSolution s) throws UnexpectedException {
		BestResponseAlgorithm alg = new BestResponseAlgorithm(g);
		alg.setInitialSolution(s);
		return alg.findSolution(g);
	}

}
