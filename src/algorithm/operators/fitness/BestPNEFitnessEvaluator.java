package algorithm.operators.fitness;

import simulation.Simulator;
import graph.Graph;
import graph.Vertex;
import graph.util.GraphUtils;
import algorithm.components.CandidateSolution;

public class BestPNEFitnessEvaluator implements FitnessEvaluator {

	private static double STABILITY_CONSTANT = 1;
	
	

	@Override
	public double evaluate(Graph g, CandidateSolution s) {
		double fitness = 0;
		
		for(Vertex v : g.getVertices()){
			//Add "stability constant" for each stable node:
			if(GraphUtils.isStable(g, s, v)){
				fitness += (STABILITY_CONSTANT*g.getVertices().size());
			}
			
			
			//Add nodes' utility:
			if(s.getAllele(v.getId())){
				fitness += (1 - Simulator.ACTION_COST);
			}else if(GraphUtils.hasTrueNeighbor(g, v, s)){
				fitness += 1;
			}
		}
		
		s.setFitness(fitness);
		return fitness;
	}

}
