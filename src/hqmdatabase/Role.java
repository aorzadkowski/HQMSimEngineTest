package hqmdatabase;

public enum Role {
	SNIPER,PLAYMAKER,DANGLER,TWO_WAY_FORWARD,OFFENSIVE_DMAN,DEFENSIVE_DMAN,NO_ROLE;
	
	public String toString() {
		switch (this) {
		case SNIPER: return "Sniper";
		case PLAYMAKER: return "Playmaker";
		case DANGLER: return "Dangler";
		case TWO_WAY_FORWARD: return "Two Way Forward";
		case OFFENSIVE_DMAN: return "Offensive Defenceman";
		case DEFENSIVE_DMAN: return "Defensive Defenceman";
		default: return "No Role";
		}
	}
	
	public double[] getWeights() {
        switch (this) {
        case SNIPER: return new double[] {0.05,0.115,0.125,0.17,0.14,0.095,0.145,0.11,0.05};
        case PLAYMAKER: return new double[] {0.05,0.145,0.12,0.12,0.14,0.09,0.17,0.115,0.05};
        case DANGLER: return new double[] {0.05,0.105,0.12,0.125,0.16,0.13,0.15,0.11,0.05};
        case TWO_WAY_FORWARD: return new double[] {0.05,0.115,0.115,0.12,0.15,0.09,0.17,0.14,0.05};
        case OFFENSIVE_DMAN: return new double[] {0.155, 0.105, 0.055, 0.105, 0.105, 0.155, 0.185, 0.085, 0.05};
        case DEFENSIVE_DMAN: return new double[] {0.145, 0.095, 0.045, 0.095, 0.095, 0.145, 0.215, 0.115, 0.05};
        default: return new double[] {0.00,0.0,0.00,0.00,0.00,0.00,0.00,0.00,0.00};
        }
	 }
}
