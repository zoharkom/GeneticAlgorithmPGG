package algorithm.components;

import graph.Graph;
import graph.Vertex;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CandidateSolution {
	
	private Map<Vertex, Boolean> vertexToAction;
	private int size;
	
	public CandidateSolution(Graph g, Random rand){
		vertexToAction = new HashMap<>();
		
		for(Vertex v : g.getVertices()){
			vertexToAction.put(v, rand.nextBoolean());
		}
		
		size = vertexToAction.size();
	}
	
	public int getSize(){
		return this.size;
	}

}
