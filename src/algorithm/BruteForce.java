package algorithm;

import graph.Graph;

import java.rmi.UnexpectedException;

import algorithm.components.CandidateSolution;
import algorithm.operators.fitness.FitnessEvaluator;
import algorithm.operators.fitness.MaxSocialWelfareFitnessEvaluator;

public class BruteForce implements PGGAlgorithm {

	@Override
	public CandidateSolution findSolution(Graph g) throws UnexpectedException {
		int n = g.getVertices().size();
		int p = 1 << n;
		
		FitnessEvaluator eval = new MaxSocialWelfareFitnessEvaluator();
		
		CandidateSolution currentSol = new CandidateSolution(g);
		
		CandidateSolution bestSol = null;
		double bestSW = Double.MIN_VALUE;
		
		for(int i = 0; i <= p ; i++){
			currentSol = new CandidateSolution(i,n);
			double currentSW = eval.evaluate(g, currentSol);
			
			if(currentSW > bestSW){
				bestSW = currentSW;
				bestSol = currentSol;
			}
		}
		
		return bestSol;
		
	}

}
