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
import algorithm.components.CandidateSolution;
import algorithm.config.BestResponseConfiguration;
import algorithm.config.BruteForceConfiguration;
import algorithm.config.SGAConfiguration;
import algorithm.operators.fitness.FitnessEvaluator;
import algorithm.operators.fitness.MaxSocialWelfareFitnessEvaluator;
<<<<<<< HEAD
=======
import algorithm.operators.improve.BestResponseImprover;
>>>>>>> origin/master
import algorithm.operators.improve.MaxSocialWelfareWithSidePaymentsImprover;
import algorithm.operators.selection.ElitismSurvivorSelector;
import algorithm.operators.selection.RouletWheelFitnessPropParentSelection;
import graph.Graph;
import graph.gen.ScaleFreeGraphGenerator;

public class MaxSocialWelfareSimulator {
	public final static double ACTION_COST= 0.4;
	
	private final static long SEED = 0;
<<<<<<< HEAD
	private final static int REPEAT_COUNT = 100;
	private final static int MIN_NUM_PLAYERS = 50;
	private final static int MAX_NUM_PLAYERS = 300;
	private final static int NUM_PLAYERS_STEP = 50;
	private final static double SF_EDGE_PROB = 0.75;
	private final static int SF_M0 = 10;
=======
	private final static int REPEAT_COUNT = 10;
	private final static int MIN_NUM_PLAYERS = 200;
	private final static int MAX_NUM_PLAYERS = 200;
	private final static int NUM_PLAYERS_STEP = 2;
	private final static double ER_EDGE_PROB = 0.3;
	private final static double SF_EDGE_PROB = 1;
	private final static int SF_M0 = 4;
>>>>>>> origin/master
	private final static int SF_M = 1;
	
	
	public static void main(String[] args){
<<<<<<< HEAD

=======
		ArrayList<double[]> stat1;
		ArrayList<double[]> stat2;
		
>>>>>>> origin/master
		//Configure genetic algorithm #1:
		SimpleGeneticAlgorithm genAlg1 = new SimpleGeneticAlgorithm();
		SGAConfiguration conf1 = SGAConfiguration.generateDefaultSGAConfiguration();
		conf1.setFitnessEvaluator(new MaxSocialWelfareFitnessEvaluator());
		conf1.setSurvivorSelector(new ElitismSurvivorSelector());
		conf1.setSolutionImprover(new MaxSocialWelfareWithSidePaymentsImprover());
		genAlg1.algorithmConfig(conf1);
		
		//Configure genetic algorithm #2:
		SimpleGeneticAlgorithm genAlg2 = new SimpleGeneticAlgorithm();
		SGAConfiguration conf2 = SGAConfiguration.generateDefaultSGAConfiguration();
		conf2.setFitnessEvaluator(new MaxSocialWelfareFitnessEvaluator());
		conf2.setParentSelector(new RouletWheelFitnessPropParentSelection());
		conf2.setSurvivorSelector(new ElitismSurvivorSelector());
		conf2.setSolutionImprover(new MaxSocialWelfareWithSidePaymentsImprover());
		genAlg2.algorithmConfig(conf2);
		
		//Configure best-response algorithm:
		BestResponseAlgorithm bestResponseAlg = new BestResponseAlgorithm();
		BestResponseConfiguration conf3 = BestResponseConfiguration.generateDefaultBruteForceConfiguration();
		bestResponseAlg.algorithmConfig(conf3);
		
		//Configure brute-force algorithm:
<<<<<<< HEAD
//		BruteForceAlgorithm bruteForceAlg = new BruteForceAlgorithm();
//		BruteForceConfiguration conf4 = BruteForceConfiguration.generateDefaultBruteForceConfiguration();
//		conf4.setFitnessEvaluator(new MaxSocialWelfareFitnessEvaluator());
//		bruteForceAlg.algorithmConfig(conf4);
=======
		BruteForceAlgorithm bruteForceAlg = new BruteForceAlgorithm();
		BruteForceConfiguration conf4 = BruteForceConfiguration.generateDefaultBruteForceConfiguration();
		conf4.setFitnessEvaluator(new BestPotentialPNEFitnessEvaluator());
		bruteForceAlg.algorithmConfig(conf4);
		
>>>>>>> origin/master
		
		//Run experiments:
		Random rand = new Random(SEED);
		
		for(int n = MIN_NUM_PLAYERS ; n <= MAX_NUM_PLAYERS; n += NUM_PLAYERS_STEP){
			
			System.out.println("\n-----------------------");
			System.out.println("Number of players: "+ n);
			System.out.println("-----------------------\n");
			
			ArrayList<double[]> solQuality = new ArrayList<double[]>();
			ArrayList<double[]> runTime = new ArrayList<double[]>();
			
			for(int i = 0 ; i < REPEAT_COUNT; i++){
				System.out.println("Generating problem number: "+ i);
				
				ArrayList<double[]> stat1 = new ArrayList<double[]>();
				ArrayList<double[]> stat2 = new ArrayList<double[]>();
				double[] iterRunTime = new double[3];
				double[] iterSolQuality = new double[3];
				
				//Generate a problem instance (i.e., 1 graph):
<<<<<<< HEAD
				Graph g = new ScaleFreeGraphGenerator(SF_M, SF_M0, SF_EDGE_PROB).generate(n, rand);
				
				System.out.println("Graph size: "+g.getVertices().size());
=======
//				Graph g1 = new ErdosReniyGraphGenerator(ER_EDGE_PROB).generate(n, rand);
				Graph g2 = new ScaleFreeGraphGenerator(SF_M, SF_M0, SF_EDGE_PROB).generate(n, rand);

				stat1 = new ArrayList<double[]>();
				stat2 = new ArrayList<double[]>();
>>>>>>> origin/master
				
				//Solve problem using SGA:
				CandidateSolution sol1 = null,sol2 = null, sol3 = null, sol4 = null;
				long t1,t2,t3,t4;
				try {
					//FitnessEvaluator e = new MaxSocialWelfareFitnessEvaluator();
					FitnessEvaluator e = new BestPotentialPNEFitnessEvaluator();
					System.out.println("Social Welfare:");
					//Alg 1 - GA1:
					t1 = System.currentTimeMillis();
					sol1 = genAlg1.findSolution(g, stat1);
					t1 = System.currentTimeMillis() - t1;
					//GDFWriter.write(g2, sol1, "/home/zohar/Desktop/PGGOutput/out-ga-tournament-"+i+".gdf");
					System.out.println("Genetic algorithm tournament: " + e.evaluate(g, sol1) + " (" + t1 + ")");
					writeToCSV("./stats/ga1-gen/big-graph/players-"+n +"/iter-"+ i +  ".csv", stat1);
					
					
					//Alg 2 - GA2:
					t2 = System.currentTimeMillis();
					sol2 = genAlg2.findSolution(g, stat2);
					t2 = System.currentTimeMillis() - t2;
					//GDFWriter.write(g2, sol2, "/home/zohar/Desktop/PGGOutput/out-ga-roulet-"+i+".gdf");
					System.out.println("Genetic algorithm roulet: " + e.evaluate(g, sol2) + " (" + t2 + ")");
					writeToCSV("./stats/ga2-gen/big-graph/players-"+n +"/iter-"+ i +  ".csv", stat2);
					
					//Alg 3 - best response:
					t3 = System.currentTimeMillis();
					sol3 = bestResponseAlg.findSolution(g, null);
					t3 = System.currentTimeMillis() - t3;
					//GDFWriter.write(g2, sol3, "/home/zohar/Desktop/PGGOutput/out-best-response-"+i+".gdf");
					System.out.println("Best response: " + e.evaluate(g, sol3) + " (" + t3 + ")");
					
//					//Alg 4 - Brute force:
//					t4 = System.currentTimeMillis();
//					sol4 = bruteForceAlg.findSolution(g, null);
//					t4 = System.currentTimeMillis() - t4;
//					//GDFWriter.write(g2, sol4, "/home/zohar/Desktop/PGGOutput/out-brute-force-"+i+".gdf");
//					System.out.println("Brute force: " + e.evaluate(g, sol4) + " (" + t4 + ")");					
//					System.out.println("-----------------------------------------------------");
					
					//Fill run time stats for current iteration:
					iterRunTime[0] = t1;
					iterRunTime[1] = t2;
					iterRunTime[2] = t3;
//					iterRunTime[3] = t4;
					runTime.add(iterRunTime);
					
<<<<<<< HEAD
					//Fill solution quality stats for current iteration:
					iterSolQuality[0] = e.evaluate(g, sol1);
					iterSolQuality[1] = e.evaluate(g, sol2);
					iterSolQuality[2] = e.evaluate(g, sol3);
//					iterSolQuality[3] = e.evaluate(g, sol4);
					solQuality.add(iterSolQuality);
=======
					//Alg 4 - Brute force:
					t4 = System.currentTimeMillis();
					//sol4 = bruteForceAlg.findSolution(g2, null);
					t4 = System.currentTimeMillis() - t4;
					//GDFWriter.write(g2, sol4, "/home/zohar/Desktop/PGGOutput/out-brute-force-"+i+".gdf");
					//System.out.println("Brute force: " + e.evaluate(g2, sol4) + " (" + t4 + ")");					
					System.out.println("-----------------------------------------------------");
>>>>>>> origin/master
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			writeToCSV("./stats/run-time/big-graph/players-"+n+".csv", runTime);
			writeToCSV("./stats/sol-quality/big-graph/players-"+n+".csv", solQuality);
		}

	}
	public static void writeToCSV(String path, ArrayList<double[]> stat){
		
		File outFile = new File(path);
		
			try {
				outFile.delete();
				outFile.createNewFile();				
				BufferedWriter bw = new BufferedWriter(new FileWriter(outFile.getAbsoluteFile(),false));
				bw.write(",best fitness, average fitness, worst fitness\n");
				for(int i = 0; i < stat.size(); i++) {
					bw.write("" + i);
					for(int j = 0; j < stat.get(i).length; j++) {
						bw.write("," + (stat.get(i)[j]));
					}
					bw.write("\n");
				}
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
		
	}
}
