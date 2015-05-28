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
	
}
