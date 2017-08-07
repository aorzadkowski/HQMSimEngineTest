package sim;

import generation.PlayerFactory;
import hqmdatabase.Player;

public class Team {
	public Player[] forwards;
	public Player[] defencemen;
	public String teamName;
	
	public double[] weightsForO = new double[9];
	public double[] weightsForD = new double[9];
	
	public double offenseTotal;
	public double defenseTotal;
	public double bothTotal;
	
	// New stuff for new SimEngine calculator
	
	/*
	
	public double[][] weightsForO = new double[20][2];
	public double[][] weightsForD = new double[20][2];
	public double[] weightForG = new double[7];
	
	public double[] oRatio = new double[2];
	public double[] dRatio = new double[2];
	public double[] bRatio = new double[2];
	
	 */
	
	public double oRatio;
	public double dRatio;
	public double bRatio;
	
	public double inverseBRatio;
		
	public Team(String teamName) {
		this.teamName = teamName;
	}
	
	public Team(String teamName, Player f1, Player f2, Player d1, Player d2) {
		this.teamName = teamName;
		forwards = new Player[] {f1, f2};
		defencemen = new Player[] {d1, d2};
		
		initSimData();
	}
	
	public void generateTeam(PlayerFactory factory) {
		forwards = new Player[] {factory.generateRandomOMan(), factory.generateRandomOMan()};
		defencemen = new Player[] {factory.generateRandomDMan(), factory.generateRandomDMan()};
	}
	
	public String toString() {
		return teamName + ": " + forwards[0].getName() + ", " + forwards[1].getName() + ", " + defencemen[0].getName() + ", " + defencemen[1].getName();
	}
	
	public void initSimData() {
		for (int i = 0; i < weightsForO.length; i++) {
			weightsForO[i] = forwards[0].getStats()[i] * forwards[0].role.getWeights()[i] 		+ forwards[1].getStats()[i] * forwards[1].role.getWeights()[i];
			if (forwards[0].role == forwards[1].role) 	weightsForO[i] *= 0.95;
			weightsForD[i] = defencemen[0].getStats()[i] * defencemen[0].role.getWeights()[i] 	+ defencemen[1].getStats()[i] * defencemen[1].role.getWeights()[i];
			if (defencemen[0].role == defencemen[1].role) weightsForD[i] *= 0.975;
		}
		
	/*
		
	public void initSimData() {
		for (int i = 0; i < weightsForO.length; i++) {
			weightsForO[i][0] = forwards[0].getStats()[i] * forwards[0].role.getWeights()[i];
			weightsForO[i][1] = forwards[1].getStats()[i] * forwards[1].role.getWeights()[i];
			if (forwards[0].role == forwards[1].role) { 	weightsForO[i][0] *= 0.95; weightsForO[i][1] *= 0.95;}
			weightsForD[i][0]0 = defencemen[0].getStats()[i] * defencemen[0].role.getWeights()[i];
			weightsForD[i][0] = defencemen[1].getStats()[i] * defencemen[1].role.getWeights()[i];
			if (defencemen[0].role == defencemen[1].role){ weightsForD[i][0] *= 0.975; weightsForD[i][1] *= 0.975;}
		}
		
	*/
		
		//Only the first 7 stats are needed for offense calculation from forwards.
		for (int i = 0; i < 7; i++) {
			offenseTotal += weightsForO[i];
		}
		//Only the first 6 stats are needed for offense calculation from defencemen.
		for (int i = 0; i < 6; i++) {
			offenseTotal += weightsForD[i];
		}
		
		//Only these three stats are used for defense calculation.
		defenseTotal = weightsForO[7] + weightsForD[6] + weightsForD[7];
		
		//Reliability stat
		bothTotal = weightsForO[8] + weightsForD[8];
		
		oRatio = offenseTotal / SimEngine.maxOffense;
		dRatio = defenseTotal / SimEngine.maxDefense;
		bRatio = bothTotal / SimEngine.maxBoth;
		
		inverseBRatio = 1 - bRatio;
	}
}
