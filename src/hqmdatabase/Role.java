package hqmdatabase;

public enum Role {
	SNIPER,PLAYMAKER,DANGLER,TWO_WAY_FORWARD,OFFENSIVE_DMAN,DEFENSIVE_DMAN,GOALIE,DEVELOPER,NO_ROLE;
	
	public String toString() {
		switch (this) {
		case SNIPER: return "Sniper";
		case PLAYMAKER: return "Playmaker";
		case DANGLER: return "Dangler";
		case TWO_WAY_FORWARD: return "Two Way Forward";
		case OFFENSIVE_DMAN: return "Offensive Defenceman";
		case DEFENSIVE_DMAN: return "Defensive Defenceman";
		case GOALIE: return "Goalie";
		case DEVELOPER: return "Developer";
		default: return "No Role";
		}
	}
	 
	public double[] getWeights() {
        switch (this) {
        case SNIPER: return new double[] {0.041,0.049,0.049,0.061,0.046,0.048,0.044,0.056,0.058,0.058,0.056,0.055,0.057,0.041,0.04,0.043,0.044,0.059,0.041,0.053};
        case PLAYMAKER: return new double[] {0.054,0.044,0.046,0.053,0.053,0.054,0.053,0.051,0.05,0.051,0.051,0.051,0.05,0.054,0.048,0.049,0.048,0.055,0.053,0.047};
        case DANGLER: return new double[] {0.053,0.05,0.048,0.064,0.05,0.05,0.04,0.054,0.057,0.067,0.066,0.069,0.056,0.053,0.032,0.027,0.039,0.066,0.032,0.053};
        case TWO_WAY_FORWARD: return new double[] {0.047,0.046,0.042,0.052,0.048,0.052,0.048,0.05,0.05,0.059,0.049,0.047,0.048,0.047,0.054,0.052,0.048,0.054,0.053,0.05};
        case OFFENSIVE_DMAN: return new double[] {0.032,0.045,0.048,0.05,0.052,0.051,0.051,0.053,0.053,0.056,0.056,0.053,0.052,0.032,0.051,0.051,0.051,0.058,0.05,0.048};
        case DEFENSIVE_DMAN: return new double[] {0.02,0.053,0.058,0.045,0.051,0.051,0.056,0.053,0.04,0.046,0.04,0.03,0.049,0.02,0.059,0.068,0.06,0.049,0.063,0.051};
        case GOALIE: return new double[] {0.138,0.141,0.126,0.129,0.131,0.105,0.105,0.125};
        case DEVELOPER: return new double[] {0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05};
        default: return new double[] {0.00,0.0,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.0,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00};
        }
	}
	
	/**
	 * @return single dimensional double array containing the max possible values in each category whereas {offensiveMax, defensiveMax, bothMax}
	 */
	public double[] getMax() {
		switch (this) {
        case SNIPER: return new double[] {13.60,5.34,1.06};
        case PLAYMAKER: return new double[] {13.32,5.78,0.94};
        case DANGLER: return new double[] {14.68,4.30,1.06};
        case TWO_WAY_FORWARD: return new double[] {12.88,6.10,1.00};
        case OFFENSIVE_DMAN: return new double[] {13.16,5.92,0.96};
        case DEFENSIVE_DMAN: return new double[] {11.84,7.18,1.02};
        case GOALIE: return new double[] {0.00,15.40,2.5};
        case DEVELOPER: return new double[] {14.00, 4.00, 3.00};
        default: return new double[] {0.00,0.00,0.00};
		}
	}
}
