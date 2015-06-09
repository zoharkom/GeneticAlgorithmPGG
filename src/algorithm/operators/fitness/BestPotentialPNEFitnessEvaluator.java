package algorithm.operators.fitness;

import java.rmi.UnexpectedException;

import simulation.MaxSocialWelfareSimulator;
import graph.Graph;
import graph.util.GraphUtils;
import algorithm.BestResponseAlgorithm;
import algorithm.components.CandidateSolution;
import algorithm.operators.improve.BestResponseImprover;

public class BestPotentialPNEFitnessEvaluator implements FitnessEvaluator {

	@Override
	public double evaluate(Graph g, CandidateSolution sol) throws UnexpectedException {
		
		BestResponseImprover emp = new BestResponseImprover();
		CandidateSolution pneSol = emp.improveSolution(g, sol);
		
		MaxSocialWelfareFitnessEvaluator eval = new MaxSocialWelfareFitnessEvaluator();
		
		return eval.evaluate(g, pneSol);
	}

}
