package algorithm;

import graph.Graph;

import java.rmi.UnexpectedException;
import algorithm.components.CandidateSolution;
import algorithm.config.BruteForceConfiguration;
import algorithm.operators.fitness.FitnessEvaluator;

public class BruteForceAlgorithm implements PGGAlgorithm {
	private FitnessEvaluator fitnessEvaluator;

	public void algorithmConfig(BruteForceConfiguration conf) {
		this.fitnessEvaluator = conf.getFitnessEvaluator();
	}
	
	@Override
	public CandidateSolution findSolution(Graph g) throws UnexpectedException {
		int n = g.getVertices().size();
		int p = 1 << n;
		
		CandidateSolution currentSol = new CandidateSolution(g);
		
		CandidateSolution bestSol = null;
		double bestSW = Double.MIN_VALUE;
		
		for(int i = 0; i <= p ; i++){
			currentSol = new CandidateSolution(i,n);
			double currentSW = fitnessEvaluator.evaluate(g, currentSol);
			
			if(currentSW > bestSW){
				bestSW = currentSW;
				bestSol = currentSol;
			}
		}
		
		return bestSol;
		
	}

}
