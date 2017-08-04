
public class Player {
	private String name;
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
	public Player (String name, Role role, int faceoffs, int passing, int receiving, int shooting,
					int handEye, int stickhandling, int offAwareness, int defAwareness, int reliability) {
		this.name = name;
		this.role = role;
		
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
	
	public double getAverage() {
		double total = 0;
		
		for (int i = 0; i < stats.length; i++) {
			total += stats[i];
		}
		
		return total / stats.length;
	}
	
	public double getWeightedAverage() {
		switch (role) {
			case SNIPER:
			case PLAYMAKER:
			case DANGLER:
			case TWO_WAY_FORWARD:
				double total = 0;
				for (int i = 0; i < stats.length; i++) {
					total += stats[i] * OFFENSIVE_WEIGHTS[i];
				}
				return total;
			case OFFENSIVE_DMAN:
			case DEFENSIVE_DMAN:
				double totalD = 0;
				for (int i = 0; i < stats.length; i++) {
					totalD += stats[i] * DEFENSIVE_WEIGHTS[i];
				}
				return totalD;
			default :
				return 0.0;
		}
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
	
	public int[] getStats() {
		return stats;
	}
	
	//Faceoffs, Passing, Receiving, Shooting, Hand Eye, Stickhandling, Off. Awareness, Def. Awareness, Reliability
	public static final double[] OFFENSIVE_WEIGHTS = {0.05, 0.125, 0.125, 0.13, 0.15, 0.1, 0.15, 0.12, 0.05};
	
	//Passing, Receiving, Long Shots, Wrist Shots, Stickhandling, Off. Awareness, Def. Awareness, Reliability
	public static final double[] DEFENSIVE_WEIGHTS = {0.15, 0.1, 0.05, 0.1, 0.1, 0.15, 0.2, 0.1, 0.05};
}
