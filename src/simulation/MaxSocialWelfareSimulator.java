package simulation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import algorithm.BestResponseAlgorithm;
import algorithm.BruteForceAlgorithm;
import algorithm.SimpleGeneticAlgorithm;
import algorithm.TwoPhaseSimpleGeneticAlgorithm;
import algorithm.components.CandidateSolution;
import algorithm.config.BestResponseConfiguration;
import algorithm.config.BruteForceConfiguration;
import algorithm.config.SGAConfiguration;
import algorithm.operators.crossover.NPointCrossoverOperator;
import algorithm.operators.crossover.UniformCrossoverOperator;
import algorithm.operators.fitness.BestPNEFitnessEvaluator;
import algorithm.operators.fitness.BestPotentialPNEFitnessEvaluator;
import algorithm.operators.fitness.FitnessEvaluator;
import algorithm.operators.fitness.MaxSocialWelfareFitnessEvaluator;
import algorithm.operators.improve.BestResponseImprover;
import algorithm.operators.selection.ElitismSurvivorSelector;
import algorithm.operators.selection.RouletWheelFitnessPropParentSelection;
import graph.Graph;
import graph.gen.ScaleFreeGraphGenerator;
import graph.util.GDFWriter;



public class MaxSocialWelfareSimulator {
	public final static double ACTION_COST= 0.4;
	
	private final static long SEED = 0;
	private final static int REPEAT_COUNT = 10;
	private final static int MIN_NUM_PLAYERS = 20;
	private final static int MAX_NUM_PLAYERS = 20;
	private final static int NUM_PLAYERS_STEP = 2;
	private final static double ER_EDGE_PROB = 0.3;
	private final static double SF_EDGE_PROB = 1;
	private final static int SF_M0 = 4;
	private final static int SF_M = 1;
	
	
	public static void main(String[] args){
		ArrayList<double[]> stat1 = new ArrayList<double[]>();
		ArrayList<double[]> stat2 = new ArrayList<double[]>();
		
		//Configure genetic algorithm #1:
		SimpleGeneticAlgorithm genAlg1 = new SimpleGeneticAlgorithm();
		SGAConfiguration conf1 = SGAConfiguration.generateDefaultSGAConfiguration();
		conf1.setFitnessEvaluator(new BestPotentialPNEFitnessEvaluator());
		conf1.setSurvivorSelector(new ElitismSurvivorSelector());
		conf1.setSolutionImprover(new BestResponseImprover());
		genAlg1.algorithmConfig(conf1);
		
		//Configure genetic algorithm #2:
		SimpleGeneticAlgorithm genAlg2 = new SimpleGeneticAlgorithm();
		SGAConfiguration conf2 = SGAConfiguration.generateDefaultSGAConfiguration();
		conf2.setFitnessEvaluator(new BestPNEFitnessEvaluator());
//		conf2.setParentSelector(new RouletWheelFitnessPropParentSelection());
		conf2.setSurvivorSelector(new ElitismSurvivorSelector());
		conf2.setSolutionImprover(new BestResponseImprover());
		genAlg2.algorithmConfig(conf2);
		
		//Configure best-response algorithm:
		BestResponseAlgorithm bestResponseAlg = new BestResponseAlgorithm();
		BestResponseConfiguration conf3 = BestResponseConfiguration.generateDefaultBruteForceConfiguration();
		bestResponseAlg.algorithmConfig(conf3);
		
		//Configure brute-force algorithm:
		BruteForceAlgorithm bruteForceAlg = new BruteForceAlgorithm();
		BruteForceConfiguration conf4 = BruteForceConfiguration.generateDefaultBruteForceConfiguration();
		conf4.setFitnessEvaluator(new BestPNEFitnessEvaluator());
		bruteForceAlg.algorithmConfig(conf4);
		
		
		//Run experiments:
		Random rand = new Random(SEED);
		
		for(int n = MIN_NUM_PLAYERS ; n <= MAX_NUM_PLAYERS; n += NUM_PLAYERS_STEP){
			
			System.out.println("\n-----------------------");
			System.out.println("Number of players: "+ n);
			System.out.println("-----------------------\n");
			
			for(int i = 0 ; i < REPEAT_COUNT; i++){
				System.out.println("Generating problem number: "+ i);
				
				//Generate a problem instance (i.e., 1 graph):
//				Graph g1 = new ErdosReniyGraphGenerator(ER_EDGE_PROB).generate(n, rand);
				Graph g2 = new ScaleFreeGraphGenerator(SF_M, SF_M0, SF_EDGE_PROB).generate(n, rand);

				//Solve problem using SGA:
				CandidateSolution sol1 = null,sol2 = null, sol3 = null, sol4 = null;
				long t1,t2,t3,t4;
				try {
					FitnessEvaluator e = new MaxSocialWelfareFitnessEvaluator();
					System.out.println("Social Welfare:");
					//Alg 1 - GA1:
					t1 = System.currentTimeMillis();
					sol1 = genAlg1.findSolution(g2, stat1);
					t1 = System.currentTimeMillis() - t1;
					//GDFWriter.write(g2, sol1, "/home/zohar/Desktop/PGGOutput/out-ga-tournament-"+i+".gdf");
					System.out.println("Genetic algorithm tournament: " + e.evaluate(g2, sol1) + " (" + t1 + ")");
					writeToCSV("statistics_tour_" + i + ".csv", stat1);
					
					//Alg 2 - GA2:
					t2 = System.currentTimeMillis();
					sol2 = genAlg2.findSolution(g2, stat2);
					t2 = System.currentTimeMillis() - t2;
					//GDFWriter.write(g2, sol2, "/home/zohar/Desktop/PGGOutput/out-ga-roulet-"+i+".gdf");
					System.out.println("Genetic algorithm roulet: " + e.evaluate(g2, sol2) + " (" + t2 + ")");
					writeToCSV("statistics_roulet_" + i + ".csv", stat2);
					
					//Alg 3 - best response:
					t3 = System.currentTimeMillis();
					sol3 = bestResponseAlg.findSolution(g2, null);
					t3 = System.currentTimeMillis() - t3;
					//GDFWriter.write(g2, sol3, "/home/zohar/Desktop/PGGOutput/out-best-response-"+i+".gdf");
					System.out.println("Best response: " + e.evaluate(g2, sol3) + " (" + t3 + ")");
					
					//Alg 4 - Brute force:
					t4 = System.currentTimeMillis();
					sol4 = bruteForceAlg.findSolution(g2, null);
					t4 = System.currentTimeMillis() - t4;
					//GDFWriter.write(g2, sol4, "/home/zohar/Desktop/PGGOutput/out-brute-force-"+i+".gdf");
					System.out.println("Brute force: " + e.evaluate(g2, sol4) + " (" + t4 + ")");					
					System.out.println("-----------------------------------------------------");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}

	}
	public static void writeToCSV(String path, ArrayList<double[]> stat){
		
		File outFile = new File(path);
		
			try {
				outFile.delete();
				outFile.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(outFile.getAbsoluteFile(),false));
				for(int i = 0; i < stat.size(); i++) {
					bw.write("" + i);
					for(int j = 0; j < stat.get(i).length; j++) {
						bw.write("," + (stat.get(i)[j]*100));
					}
					bw.write("\n");
				}
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
		
	}
}
