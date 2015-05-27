package graph.gen;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import graph.Graph;
import graph.Graph.GraphGenerator;
import graph.Vertex;

public class ErdosReniyGraphGenerator implements GraphGenerator {
	private final double edgeProbability;
	
	public ErdosReniyGraphGenerator(double edgeProbability) {
		this.edgeProbability = edgeProbability;
	}
	
	@Override
	public Graph generate(int n, Random rand) {
		Graph g = new Graph(n);
		
		Map<Integer, Vertex> indexToVertex = new HashMap<>();

		int index = 0;
		for (Vertex v : g.getVertices()) {
			indexToVertex.put(index, v);
			index++;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (rand.nextDouble() < edgeProbability) {
					g.addUndirectedEdge(indexToVertex.get(i), indexToVertex.get(j));
				}
			}
		}

		return g;
	}

}
