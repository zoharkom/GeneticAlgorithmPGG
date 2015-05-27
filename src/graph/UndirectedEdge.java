package graph;

public class UndirectedEdge extends Edge{

	public UndirectedEdge(Vertex start, Vertex end) {
		super(start, end);
	}

	@Override
	public boolean startsWith(Vertex v) {
		return v.equals(getStart()) || v.equals(getEnd());
	}
	
	@Override
	public boolean endsWith(Vertex v) {
		return startsWith(v);
	}	
}
