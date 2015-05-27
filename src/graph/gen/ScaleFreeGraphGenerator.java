package graph.gen;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import graph.Graph;
import graph.Graph.GraphGenerator;
import graph.Vertex;

public class ScaleFreeGraphGenerator implements GraphGenerator {
	private final int m;
	private final int m0;
	private final double basicEdgeProbability;
	
	
	public ScaleFreeGraphGenerator(int m, int m0, double basicEdgeProb) {
		this.basicEdgeProbability = basicEdgeProb;
		this.m0 = m0;
		this.m = m;
	}
	
	@Override
	public Graph generate(int n, Random rand) {
		Graph result = new Graph(n);
		Map<Integer, Vertex> indexToVertex = new HashMap<>();
		int index = 0;
		
		for (Vertex v : result.getVertices()) {
			indexToVertex.put(index, v);
			index++;
		}
		
		//Create the basic connected component:
		for(int i=0 ; i<this.m0; i++){
			for (int j = i + 1; j < this.m0 ; j++) {
				if (rand.nextDouble() < this.basicEdgeProbability) {
					result.addUndirectedEdge(indexToVertex.get(i), indexToVertex.get(j));
				}
			}
		}
		
		//Add new edges:
		for(int i = this.m0 ; i < n ; i++){
			
			Set<Vertex> neighbors = new HashSet<Vertex>();
			for(int j = 0 ; j < this.m ; j++){
				neighbors.add(createRandomEdge(i,result,indexToVertex.get(j),neighbors,indexToVertex,rand));
			}
			
			//Add new neighbors of vertex i:
			for(Vertex v : neighbors){
				result.addUndirectedEdge(indexToVertex.get(i),v);
			}
			
		}

		return result;
	}

	private Vertex createRandomEdge(int i,Graph result, Vertex vertex, Set<Vertex> neighbors, Map<Integer, Vertex> indexToVertex, Random rand) {
		while(true){
			Vertex newVertex = indexToVertex.get(rand.nextInt(i));
			if(neighbors.contains(newVertex)){
				continue;
			}
			
			double degree = Math.max(1,result.getNeighbours(newVertex).size());
			double denominator = Math.max(result.getVertices().size(),2*result.getEdges().size());
			double attachProb = ( degree / denominator);
			if(rand.nextDouble() <= attachProb){
				return newVertex;
			}
		}
	}

}