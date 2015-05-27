package graph.gen;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import graph.Graph;
import graph.Graph.GraphGenerator;
import graph.Vertex;

public class AverageNeighboursGraphGenerator implements GraphGenerator {
	private final int averageAmountOfNeighbours;
	
	public AverageNeighboursGraphGenerator(int averageAmountOfNeighbours) {
		this.averageAmountOfNeighbours = averageAmountOfNeighbours;
	}
	
	@Override
	public Graph generate(int n, Random rand) {
		Graph g = new Graph(n);
		Map<Vertex, Integer> vertexToIndex = new HashMap<>();
		Map<Integer, Vertex> indexToVertex = new HashMap<>();

		int index = 0;
		for (Vertex v : g.getVertices()) {
			vertexToIndex.put(v, index);
			indexToVertex.put(index, v);
			index++;
		}

		for (int i = 0; i < n; i++) {
			List<Vertex> vertices = new LinkedList<>(g.getVertices());
			Collections.shuffle(vertices, rand);
			Vertex v = indexToVertex.get(i);
			vertices.remove(v);
			vertices.removeAll(g.getNeighbours(v));
			while (g.getNeighbours(v).size() < averageAmountOfNeighbours && !vertices.isEmpty()) {
				Vertex nv = vertices.remove(0);
				g.addUndirectedEdge(v, nv);
			}
		}

		return g;
	}

}
