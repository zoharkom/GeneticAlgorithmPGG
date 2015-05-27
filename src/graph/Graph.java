package graph;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Graph {
	private Set<Vertex> vertices;
	private Set<Edge> edges;

	public Graph(int n) {
		vertices = new HashSet<>();
		edges = new HashSet<>();

		for (int i = 0; i < n; i++) {
			vertices.add(new Vertex());
		}
	}

	public Set<Vertex> getVertices() {
		return vertices;
	}

	public Set<Edge> getEdges() {
		return edges;
	}
	
	public void addEdge(Vertex start, Vertex end) {
		addEdge(start, end, Edge.class);
	}
	
	public void addUndirectedEdge(Vertex start, Vertex end) {
		addEdge(start, end, UndirectedEdge.class);
	}	
	
	public <T extends Edge> void addEdge(Vertex start, Vertex end, Class<T> clazz) {
		if (!vertices.contains(start) || !vertices.contains(end)) {
			throw new UnsupportedOperationException();
		}

		try {
			edges.add(clazz.getConstructor(Vertex.class, Vertex.class).newInstance(start, end));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public Set<Vertex> getNeighbours(Vertex v) {
		if (!vertices.contains(v)) {
			return null;
		}

		Set<Vertex> neighbours = new HashSet<>();

		for (Edge e : edges) {
			if (e.startsWith(v)) {
				neighbours.add(e.getStart());
				neighbours.add(e.getEnd());
			}
		}

		neighbours.remove(v);

		return neighbours;
	}

	public Set<Edge> getOutgoingEdges(Vertex v) {
		if (!vertices.contains(v)) {
			return null;
		}

		Set<Edge> outgoing = new HashSet<>();

		for (Edge e : edges) {
			if (e.startsWith(v)) {
				outgoing.add(e);
			}
		}

		return outgoing;
	}

	public Set<Edge> getIncommingEdges(Vertex v) {
		if (!vertices.contains(v)) {
			return null;
		}

		Set<Edge> incomming = new HashSet<>();

		for (Edge e : edges) {
			if (e.endsWith(v)) {
				incomming.add(e);
			}
		}

		return incomming;
	}

	public static interface GraphGenerator {
		Graph generate(int n, Random rand);
	}
}
