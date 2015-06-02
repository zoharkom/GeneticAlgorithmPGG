package algorithm;

import graph.Graph;
import graph.Vertex;
import graph.util.GraphUtils;

import java.rmi.UnexpectedException;
import java.util.Random;

import algorithm.components.CandidateSolution;

public class BestResponseAlgorithm implements PGGAlgorithm {
	Graph graph;
	CandidateSolution initialSolution;
	
	public BestResponseAlgorithm(Graph g) {
		graph = g;
		initialSolution = new CandidateSolution(g,new Random());
	}
	
	public void setInitialSolution(CandidateSolution s){
		initialSolution = new CandidateSolution(s);
	}

	@Override
	public CandidateSolution findSolution(Graph g) throws UnexpectedException {
		boolean shouldStop = false;
		CandidateSolution currentSol = initialSolution;
		while(!shouldStop){
			shouldStop = true;
			for(Vertex v : g.getVertices()){
				
				if(!GraphUtils.isStable(g, currentSol, v)){
					shouldStop = false;
					boolean currentAssignment = currentSol.getAllele(v.getId());
					currentSol.setAllele(v.getId(), !currentAssignment);
					continue;
				}

			}
		}
		return currentSol;
	}

}
