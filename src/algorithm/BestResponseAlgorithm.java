package algorithm;

import graph.Graph;
import graph.Vertex;
import graph.util.GraphUtils;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Random;

import algorithm.components.CandidateSolution;
import algorithm.config.BestResponseConfiguration;

public class BestResponseAlgorithm implements PGGAlgorithm {
	CandidateSolution initialSolution;
	
	public void algorithmConfig(BestResponseConfiguration conf){
		
	}
	
	public void setInitialSolution(CandidateSolution s){
		initialSolution = new CandidateSolution(s);
	}

	@Override
	public CandidateSolution findSolution(Graph g, ArrayList<double[]> stat) throws UnexpectedException {
		boolean shouldStop = false;
		if(initialSolution == null){
			initialSolution = new CandidateSolution(g, new Random());
		}
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
		initialSolution = null;
		return currentSol;
	}

}
