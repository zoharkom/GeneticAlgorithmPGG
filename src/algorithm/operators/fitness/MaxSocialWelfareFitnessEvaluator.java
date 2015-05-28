package algorithm.operators.fitness;

import simulation.Simulator;
import graph.Graph;
import graph.Vertex;
import graph.util.GraphUtils;
import algorithm.components.CandidateSolution;

public class MaxSocialWelfareFitnessEvaluator implements FitnessEvaluator {

	@Override
	public double evaluate(Graph g, CandidateSolution currentSol) {
		double socialWelfare = 0;
		
		for(Vertex v : g.getVertices()){
			if(currentSol.getAllele(v.getId())){//If this vertex takes the action
				socialWelfare += (1 - Simulator.ACTION_COST);
			}else if(GraphUtils.hasTrueNeighbor(g, v, currentSol)){
				socialWelfare += 1;
			}
		}
		
		return socialWelfare;
	}

}
