
public class Team {
	public Player[] forwards;
	public Player[] defencemen;
	public String teamName;
	
	public double[] weightsForO = new double[9];
	public double[] weightsForD = new double[9];
	
	public double offenseTotal;
	public double defenseTotal;
	public double bothTotal;
	
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
		
	    // New stuff in new commit
		if ( (forwards[0].role == Role.PLAYMAKER) && (forwards[1].role == Role.PLAYMAKER)) {
    	
		forwards[0].stats[1] = forwards[0].stats[1] + 1; 
	    forwards[1].stats[1] = forwards[1].stats[1] + 1; 
		forwards[0].stats[2] = forwards[0].stats[2] + 1; 
    	forwards[1].stats[2] = forwards[1].stats[2] + 1; 
    	forwards[0].stats[6] = forwards[0].stats[6] + 1; 
    	forwards[1].stats[6] = forwards[1].stats[6] + 1; 
    	forwards[0].stats[3] = forwards[0].stats[3] - 2; 
    	forwards[1].stats[3] = forwards[1].stats[3] - 2; 
    	forwards[0].stats[4] = forwards[0].stats[4] - 2; 
    	forwards[1].stats[4] = forwards[1].stats[4] - 2; 
    	forwards[0].stats[5] = forwards[0].stats[5] - 2; 
    	forwards[1].stats[5] = forwards[1].stats[5] - 2; 
    	forwards[0].stats[7] = forwards[0].stats[7] - 2; 
    	forwards[1].stats[7] = forwards[1].stats[7] - 2; 
		}	
		initSimData();
	}
	

	
	public void generateTeam(PlayerFactory factory) {
		forwards = new Player[] {factory.generateRandomOMan(), factory.generateRandomOMan()};
		defencemen = new Player[] {factory.generateRandomDMan(), factory.generateRandomDMan()};
	}
	
/*
	
	public void synChange( Player f1 , Player f2) {
		
		if ( (f1.role == Role.PLAYMAKER) && (f2.role == Role.PLAYMAKER)) {
			for ( int i = 0; i < 9; i++) {
				if ( i != 1 || i != 2 || i != 6 ) {
			
					forwards = new Player[] { f1.stats[i] = f1.stats[i] - 2 , f2.stats[i] = f2.stats[i] - 2};
			
					}
			}
		}
	}
	
*/
	
	public String toString() {
		return teamName + ": " + forwards[0].getName() + ", " + forwards[1].getName() + ", " + defencemen[0].getName() + ", " + defencemen[1].getName();
	}
	
	public void initSimData() {
		for (int i = 0; i < weightsForO.length; i++) {
			weightsForO[i] = (forwards[0].getStats()[i] + forwards[1].getStats()[i]) * Player.OFFENSIVE_WEIGHTS[i];
			weightsForD[i] = (defencemen[0].getStats()[i] + defencemen[1].getStats()[i]) * Player.DEFENSIVE_WEIGHTS[i];
		}
		
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
