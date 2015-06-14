package algorithm;

import java.util.ArrayList;

import graph.Graph;
import algorithm.components.CandidateSolution;
import algorithm.config.BNBConfiguration;

public class BranchAndBound implements PGGAlgorithm {

	public void algorithmConfig(BNBConfiguration conf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CandidateSolution findSolution(Graph g, ArrayList<double[]> stat) {
		
		CandidateSolution currentSol = new CandidateSolution(g);
//		int i = 0;
//		int nodesCount = g.getVertices().size();
//		while(true){
//			
//			
//			
//			
//			i++;
//			if(i == nodesCount){
//				
//				break;
//			}
//			
//			
//			
//		}
		
		return currentSol;
	}

}
