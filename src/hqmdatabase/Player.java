package hqmdatabase;

public class Player {
	private String name;
	private Season season;
	public Position position;
	public Role role; 
	
	public int[] stats = new int[20]; 
	
	/**
	 * All ints should be between 0-20;
	 * @param name
	 * @param role
	 * @param faceoffs
	 * @param passing
	 * @param receiving
	 * @param shooting
	 * @param handEye
	 * @param stickhandling
	 * @param offAwareness
	 * @param defAwareness
	 * @param reliability
	 */
	public Player (String name, Season season, Position position, Role role, int faceoffs, int longshots, int pointshots, int wristshots, int boardpassing, 
					int openicepassing, int breakoutpassing, int boardreceiving, int openicereceiving, int handeye, int stickhandling, int dangling,
					int possession, int shotblocking, int hitting, int positioning, int pokecheck, int offAwareness, int defAwareness, int consistency) {
		this.name = name;
		this.role = role;
		this.season = season;
		this.position = position;
		
		stats[0] = faceoffs % 21;
		stats[1] = longshots % 21;
		stats[2] = pointshots % 21;
		stats[3] = wristshots % 21;
		stats[4] = boardpassing % 21;
		stats[5] = openicepassing % 21;
		stats[6] = breakoutpassing % 21;
		stats[7] = boardreceiving % 21;
		stats[8] = openicereceiving % 21;
		stats[9] = handeye % 21;
		stats[10] = stickhandling % 21;
		stats[11] = dangling % 21;
		stats[12] = possession % 21;
		stats[13] = shotblocking % 21;
		stats[14] = hitting % 21;
		stats[15] = positioning % 21;
		stats[16] = pokecheck % 21;
		stats[17] = offAwareness % 21;
		stats[18] = defAwareness % 21;
		stats[19] = consistency % 21;
	}
	
	public Player (String name, Season season, Position position, Role role, int longshotsaves, int wristshotsaves, int breakawaysaves,
			int reboundcontrol, int positioning, int stickhandling, int passing) {
		this.name = name;
		this.role = role;
		this.season = season;
		this.position = position;

		stats[0] = longshotsaves % 21;
		stats[1] = wristshotsaves % 21;
		stats[2] = breakawaysaves % 21;
		stats[3] = reboundcontrol % 21;
		stats[4] = positioning % 21;
		stats[5] = stickhandling % 21;
		stats[6] = passing % 21;
	}
	
	public Player (String name, Season season, Position position, Role role, int[] stats) {
		this.name = name; 
		this.role = role;
		this.season = season;
		this.position = position;
		
		this.stats = stats;
	}
	
	public Player (Player oldPlayer) {
		this.name = new String(oldPlayer.getName());
		this.role = oldPlayer.role;
		this.season = oldPlayer.getSeason();
		this.position = oldPlayer.position;
		
		for (int i = 0; i < oldPlayer.stats.length; i++) {
			this.stats[i] = oldPlayer.stats[i];
		}
	}
	
	public double getAverage() {
		double total = 0;
		
		for (int i = 0; i < stats.length; i++) {
			total += stats[i];
		}
		
		return total / stats.length;
	}
	
	public double getWeightedAverage() {
		double total = 0;
		for (int i = 0; i < stats.length; i++) {
			total += stats[i] * role.getWeights()[i];
		}
		return total;
	}
	
	public int getNHLRating() {
		return (int) (getWeightedAverage() * 5.35);
	}
	
	public void setRole(Role newRole) {
		role = newRole;
	}
	
	public String toString() {
		String toReturn = name + "\t" + role;
		for (int i = 0; i < stats.length; i++) {
			toReturn += "\t" + stats[i];
		}
		
		toReturn += "\t" + getAverage() + "\t" + getWeightedAverage() + "\t" + getNHLRating();
		
		return toReturn;
	}
	
	public String getName() {
		return name;
	}
	
	public Season getSeason() {
		return season;
	}
	
	public int[] getStats() {
		return stats;
	}
	
	public int getStat( int statNum ) {
		return stats[statNum];
	}
	
	public boolean equals(Player other) {
		if (!this.name.equals(other.name) || !this.season.equals(other.season) ||
				this.role != other.role || this.stats.length != other.stats.length)
			return false;
		for (int i = 0; i < stats.length; i++) {
			if (stats[i] != other.stats[i]) return false;
		}
		
		return true;
	}
	
	public int getStat(String stat) {
		switch (stat.toLowerCase()) {
		case "faceoffs" : return stats[0];
		case "longshots": return stats[1];
		case "pointshots": return stats[2];
		case "wristshots": return stats[3];
		case "board passing": return stats[4];
		case "open ice passing": return stats[5];
		case "breakout passing": return stats[6];
		case "board receiving": return stats[7];
		case "open ice receiving": return stats[8];
		case "hand eye": return stats[9];
		case "stickhandling": return stats[10];
		case "dangling": return stats[11];
		case "possession": return stats[12];
		case "shot blocking": return stats[13];
		case "hitting": return stats[14];
		case "positioning": return stats[15];
		case "pokecheck": return stats[16];
		case "off awareness": return stats[17];
		case "def awareness": return stats[18];
		case "consistency": return stats[19];
		default : System.err.println("Invalid getStat() string"); return -1; 
		}
	}
	
	//Faceoffs, Passing, Receiving, Shooting, Hand Eye, Stickhandling, Off. Awareness, Def. Awareness, Reliability
	public static final double[] OFFENSIVE_WEIGHTS = {0.05, 0.125, 0.125, 0.13, 0.15, 0.1, 0.15, 0.12, 0.05};
	
	//Passing, Receiving, Long Shots, Wrist Shots, Stickhandling, Off. Awareness, Def. Awareness, Reliability
	public static final double[] DEFENSIVE_WEIGHTS = {0.15, 0.1, 0.05, 0.1, 0.1, 0.15, 0.2, 0.1, 0.05};
}
