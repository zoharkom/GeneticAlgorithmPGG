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

	public static boolean hasDependents(Graph g, CandidateSolution s, Vertex v) {
		
		for(Vertex neighbor:g.getNeighbours(v)){
			if(doesDependOn(neighbor, v, g, s)){
				return true;
			}
		}
		
		return false;
	}

	private static boolean doesDependOn(Vertex neighbor, Vertex v, Graph g, CandidateSolution s) {
		boolean vVal = s.getAllele(v.getId());
		boolean neighborVal = s.getAllele(neighbor.getId());
		
		//If 'neighbor' chooses T or v chooses F we know that 'neighbor'
		//does not depend on v: 
		if(neighborVal || !vVal ){
			return false;
		}
		
		for(Vertex k : g.getNeighbours(neighbor)){
			//If 'neighbor' has some neighbor k other than v that chooses T
			//'neighbor' does not depend on v:
			if( (!k.equals(v)) && s.getAllele(k.getId()) ){
				return false;
			}
		}
		
		
		return true;
	}
	
}
