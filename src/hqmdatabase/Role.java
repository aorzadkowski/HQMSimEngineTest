package hqmdatabase;

public enum Role {
	SNIPER,PLAYMAKER,DANGLER,TWO_WAY_FORWARD,OFFENSIVE_DMAN,DEFENSIVE_DMAN,DEVELOPER,NO_ROLE;
	
	public String toString() {
		switch (this) {
		case SNIPER: return "Sniper";
		case PLAYMAKER: return "Playmaker";
		case DANGLER: return "Dangler";
		case TWO_WAY_FORWARD: return "Two Way Forward";
		case OFFENSIVE_DMAN: return "Offensive Defenceman";
		case DEFENSIVE_DMAN: return "Defensive Defenceman";
		case DEVELOPER: return "Developer";
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
        case DEVELOPER: return new double[] {0.1111, 0.1111, 0.1111, 0.1111, 0.1111, 0.1111, 0.1111, 0.1111, 0.1111};
        default: return new double[] {0.00,0.0,0.00,0.00,0.00,0.00,0.00,0.00,0.00};
        }
	 }

	/* This is for the new player categories
	 
	 
	public double[] getWeights() {
        switch (this) {
        case SNIPER: return new double[] {0.041,0.049,0.049,0.061,0.046,0.048,0.044,0.056,0.058,0.058,0.056,0.055,0.057,0.041,0.04,0.043,0.044,0.059,0.041,0.053};
        case PLAYMAKER: return new double[] {0.054,0.044,0.046,0.053,0.053,0.054,0.053,0.051,0.05,0.051,0.051,0.051,0.05,0.054,0.048,0.049,0.048,0.055,0.053,0.047};
        case DANGLER: return new double[] {0.053,0.05,0.048,0.064,0.05,0.05,0.04,0.054,0.057,0.067,0.066,0.069,0.056,0.053,0.032,0.027,0.039,0.066,0.032,0.053};
        case TWO_WAY_FORWARD: return new double[] {0.047,0.046,0.042,0.052,0.048,0.052,0.048,0.05,0.05,0.059,0.049,0.047,0.048,0.047,0.054,0.052,0.048,0.054,0.053,0.05};
        case OFFENSIVE_DMAN: return new double[] {0.032,0.045,0.048,0.05,0.052,0.051,0.051,0.053,0.053,0.056,0.056,0.053,0.052,0.032,0.051,0.051,0.051,0.058,0.05,0.048};
        case DEFENSIVE_DMAN: return new double[] {0.02,0.053,0.058,0.045,0.051,0.051,0.056,0.053,0.04,0.046,0.04,0.03,0.049,0.02,0.059,0.068,0.06,0.049,0.063,0.051};
        case GOALIE: return new double[] {};
        default: return new double[] {0.00,0.0,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.0,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00};
        }
	 }
	 */
}
