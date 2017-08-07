package hqmdatabase;

public enum Season {
	LHLS13,NO_SEASON;
	
	public String toString() {
		switch (this) {
		case LHLS13: return "LHL S13";
		default: return "";
		}
	}
}
