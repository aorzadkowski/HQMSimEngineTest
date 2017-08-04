
public enum Role {
	SNIPER,PLAYMAKER,DANGLER,TWO_WAY_FORWARD,OFFENSIVE_DMAN,DEFENSIVE_DMAN;
	
	public String toString() {
		switch (this) {
		case SNIPER: return "Sniper";
		case PLAYMAKER: return "Playmaker";
		case DANGLER: return "Dangler";
		case TWO_WAY_FORWARD: return "Two Way Forward";
		case OFFENSIVE_DMAN: return "Offensive Defenceman";
		case DEFENSIVE_DMAN: return "Defensive Defenceman";
		default: return "";
		}
	}
}
