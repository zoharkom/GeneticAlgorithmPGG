package graph;

public abstract class Edge {
	
	private final Vertex start;
	private final Vertex end;

	public Edge(Vertex start, Vertex end) {
		this.start = start;
		this.end = end;

		if (start == null || end == null || start.equals(end)) {
			throw new UnsupportedOperationException("Unsupported edge");
		}
	}

	public Vertex getStart() {
		return start;
	}

	public Vertex getEnd() {
		return end;
	}

	public boolean startsWith(Vertex v) {
		return v.equals(start);
	}

	public boolean endsWith(Vertex v) {
		return v.equals(end);
	}

	@Override
	public boolean equals(Object arg) {
		if (!getClass().equals(arg.getClass())) {
			return false;
		}

		Edge e = (Edge) arg;

		return startsWith(e.start) && endsWith(e.end);
	}
	
	@Override
	public String toString() {
		return "e(" + start.toString() + "," + end.toString() + ")";
	}
}
