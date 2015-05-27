package graph;

public class Vertex {
	
	private static int indexGenerator = 0;
	
	private int id;
	
	public Vertex() {
		id = indexGenerator++;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object arg) {
		return (arg instanceof Vertex) && ((Vertex)arg).id == id;
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public String toString() {
		return "v_" + id;
	}
}
