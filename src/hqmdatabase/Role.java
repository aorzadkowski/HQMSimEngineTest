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
        case GOALIE: return new double[] {1/7,1/7,1/7,1/7,1/7,1/7,1/7};
	case DEVELOPER: return new double[] {0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05};
        default: return new double[] {0.00,0.0,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.0,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00};
        }
	}
	
	public double[] getMax() {
		switch (this) {
        case SNIPER: return new double[] {13.60,3.34,3.06};
        case PLAYMAKER: return new double[] {13.32,3.80,2.92};
        case DANGLER: return new double[] {14.68,2.64,2.72};
        case TWO_WAY_FORWARD: return new double[] {12.88,4.10,3.00};
        case OFFENSIVE_DMAN: return new double[] {13.16,3.86,3.02};
        case DEFENSIVE_DMAN: return new double[] {11.84,4.84,3.36};
        case GOALIE: return new double[] {0.00,14.29,0.00};
	case DEVELOPER: return new double[] {14.00, 4.00, 3.00};
        default: return new double[] {0.00,0.00,0.00};
		}
	}
}
