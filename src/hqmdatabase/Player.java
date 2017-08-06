package hqmdatabase;

public class Player {
	private String name;
	private String season;
	public Role role;  // Made public in new commit
	
	public int[] stats = new int[9];  // Made public in new commit
	
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
	public Player (String name, String season, Role role,int faceoffs, int passing, int receiving, int shooting,
					int handEye, int stickhandling, int offAwareness, int defAwareness, int reliability) {
		this.name = name;
		this.role = role;
		this.season = season;
		
		stats[0] = faceoffs % 21;
		stats[1] = passing % 21;
		stats[2] = receiving % 21;
		stats[3] = shooting % 21;
		stats[4] = handEye % 21;
		stats[5] = stickhandling % 21;
		stats[6] = offAwareness % 21;
		stats[7] = defAwareness % 21;
		stats[8] = reliability % 21;
	}
	
	public Player (String name, String season, Role role, int[] stats) {
		this.name = name; 
		this.role = role;
		this.season = season;
		
		this.stats = stats;
	}
	
	public Player (Player oldPlayer) {
		this.name = new String(oldPlayer.getName());
		this.role = oldPlayer.role;
		this.season = new String(oldPlayer.getSeason());
		
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
	
	public String getSeason() {
		return season;
	}
	
	public int[] getStats() {
		return stats;
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
	
	//Faceoffs, Passing, Receiving, Shooting, Hand Eye, Stickhandling, Off. Awareness, Def. Awareness, Reliability
	public static final double[] OFFENSIVE_WEIGHTS = {0.05, 0.125, 0.125, 0.13, 0.15, 0.1, 0.15, 0.12, 0.05};
	
	//Passing, Receiving, Long Shots, Wrist Shots, Stickhandling, Off. Awareness, Def. Awareness, Reliability
	public static final double[] DEFENSIVE_WEIGHTS = {0.15, 0.1, 0.05, 0.1, 0.1, 0.15, 0.2, 0.1, 0.05};
}
