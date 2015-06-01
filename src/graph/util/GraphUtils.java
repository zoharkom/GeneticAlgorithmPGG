package graph.util;

import algorithm.components.CandidateSolution;
import graph.Graph;
import graph.Vertex;

public class GraphUtils {

	public static boolean hasTrueNeighbor(Graph g, Vertex v, CandidateSolution s){
		for(Vertex neighbor : g.getNeighbours(v)){
			if(s.getAllele(neighbor.getId())){
				return true;
			}
		}
		return false;
	}

	public static boolean isStable(Graph g, CandidateSolution s, Vertex v) {
		return (isFT(g,s,v) || isTF(g,s,v));
	}

	public static boolean isTT(Graph g, CandidateSolution s, Vertex v) {
		if(!s.getAllele(v.getId())){// If v's action is F
			return false; 
		}
		
		//If we got here, v's action is T
		
		
		for(Vertex neighbor : g.getNeighbours(v)){
			if(s.getAllele(neighbor.getId())){
				//v has a neighbor with action T so v's state is: TT
				return true;
			}
		}
		
		return false;
	}


	public static boolean isTF(Graph g, CandidateSolution s, Vertex v) {
		if(!s.getAllele(v.getId())){// If v's action is F
			return false; 
		}
		
		//If we got here, v's action is T
		
		
		for(Vertex neighbor : g.getNeighbours(v)){
			if(s.getAllele(neighbor.getId())){
				//v has a neighbor with action T so v's state is: TT
				return false;
			}
		}
		
		//v has no neighbor with action T so v's state is: TF
		return true;
	}

	public static boolean isFF(Graph g, CandidateSolution s, Vertex v) {
		if(s.getAllele(v.getId())){// If v's action is T
			return false; 
		}
		
		for(Vertex neighbor : g.getNeighbours(v)){
			if(s.getAllele(neighbor.getId())){
				//v has a neighbor with action T so v's state is: FT
				return false;
			}
		}
		
		//v has no neighbor with action T so v's state is: FF
		return true;
	}
	

	public static boolean isFT(Graph g, CandidateSolution s, Vertex v) {
		if(s.getAllele(v.getId())){// If v's action is T
			return false; 
		}
		
		for(Vertex neighbor : g.getNeighbours(v)){
			if(s.getAllele(neighbor.getId())){
				//v has a neighbor with action T so v's state is: FT
				return true;
			}
		}
		
		//v has no neighbor with action T so v's state is: FF
		return false;
	}
	
}
