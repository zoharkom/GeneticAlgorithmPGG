package graph.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.UnexpectedException;

import algorithm.components.CandidateSolution;
import graph.Graph;
import graph.Vertex;

public class GDFWriter {
	
	/**
	 * Write the given 
	 * @param graph
	 * @param path
	 * @throws IOException 
	 */
	public static void write(Graph graph,CandidateSolution s, String path) throws IOException{
		if(!path.endsWith(".gdf")){
			throw new UnexpectedException("File name should end with .gdf!");
		}
		
		if(s.getSize() != graph.getVertices().size() ){
			throw new UnexpectedException("The solution size does not fit the number of nodes!");
		}
		
		File outFile = new File(path);
		
		if (!outFile.exists()) {
			outFile.createNewFile();
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(outFile.getAbsoluteFile()));
		
		//Write vertices:
		bw.write("nodedef> name,label\n");
		for(Vertex v : graph.getVertices()){
			bw.write("v"+String.valueOf(v.getId()));
			bw.write(",");
			bw.write(s.getAllele(v.getId()) ? "T" : "F");
			bw.write("\n");
		}
		//Write edges:
		bw.write("edgedef> node1, node2\n");
		for(Vertex v1 : graph.getVertices()){
			for(Vertex v2 : graph.getNeighbours(v1)){
				if(v1.getId() < v2.getId()){
					bw.write("v"+String.valueOf(v1.getId()));
					bw.write(",");
					bw.write("v"+String.valueOf(v2.getId()));
					bw.write("\n");
				}
			}
		}
		//Close file:
		bw.close();
	}

}
