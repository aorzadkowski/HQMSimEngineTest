package sim;

import generation.PlayerFactory;
import hqmdatabase.Player;

public class Team {
	public Player[] forwards;
	public Player[] defencemen;
	public Player[] teamPlayers;
	public String teamName;
	
	// New stuff for new SimEngine calculator
		
	public double[][] weightsForPlayer = new double[20][4];
	public double[] weightForG = new double[7];
	
	public double[] oRatio = new double[4];
	public double[] dRatio = new double[4];
	public double[] bRatio = new double[4];
	public double[] inverseBRatio = new double[4];
	
	public double[] offenseTotal = new double[4];
	public double[] defenseTotal = new double[4];
	public double[] bothTotal = new double[4];
	
		
	public Team(String teamName) {
		this.teamName = teamName;
	}
	
	public Team(String teamName, Player f1, Player f2, Player d1, Player d2) {
		this.teamName = teamName;
		forwards = new Player[] {f1, f2};
		defencemen = new Player[] {d1, d2};
		teamPlayers = new Player[] {f1, f2, d1, d2};
		
		initSimData( f1, f2, d1, d2);
	}
	
	/*      
	  	f1 = C = array index 0
		f2 = LW = array index 1
		d2 = RD = array index 3
		d1 = LD = array index 2
	*/
	
	
	
	public void generateTeam(PlayerFactory factory) {
		forwards = new Player[] {factory.generateRandomOMan(), factory.generateRandomOMan()};
		defencemen = new Player[] {factory.generateRandomDMan(), factory.generateRandomDMan()};
	}
	
	public String toString() {
		return teamName + ": " + forwards[0].getName() + ", " + forwards[1].getName() + ", " + defencemen[0].getName() + ", " + defencemen[1].getName();
	}
	
	public void initSimData( Player f1, Player f2, Player d1, Player d2) {
		for (int i = 0; i < weightsForPlayer.length; i++) {
			weightsForPlayer[i][0] = forwards[0].getStats()[i] * forwards[0].role.getWeights()[i];
			weightsForPlayer[i][1] = forwards[1].getStats()[i] * forwards[1].role.getWeights()[i];
			if (forwards[0].role == forwards[1].role) { 	weightsForPlayer[i][0] *= 0.95; weightsForPlayer[i][1] *= 0.95;}
			weightsForPlayer[i][2] = defencemen[0].getStats()[i] * defencemen[0].role.getWeights()[i];
			weightsForPlayer[i][3] = defencemen[1].getStats()[i] * defencemen[1].role.getWeights()[i];
			if (defencemen[0].role == defencemen[1].role){ weightsForPlayer[i][2] *= 0.975; weightsForPlayer[i][3] *= 0.975;}
		}
	
	// Adds the stat categories for each player
	for ( int j = 0; j < 4; j++) {
		for ( int i = 0; i < 20; i++) {
			if ( i == 12 || i == 15 || i == 19) bothTotal[j] += weightsForPlayer[i][j];
			if ( i == 13 || i == 14 || i == 16 || i == 18) defenseTotal[j] += weightsForPlayer[i][j];
			else offenseTotal[j] += weightsForPlayer[i][j];
		}
	}
	
	for ( int i = 0; i < 4; i++) {
		double[] roleRatio = new double[3];
		roleRatio = teamPlayers[i].role.getMax();	
				
		oRatio[i] = offenseTotal[i] / roleRatio[0];
		dRatio[i] = defenseTotal[i] / roleRatio[1];
		bRatio[i] = bothTotal[i] / roleRatio[2];
		inverseBRatio[i] = 1 - bRatio[i];
	}
	
	}
}

