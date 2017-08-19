package hqmdatabase;

public enum Season {
	LHLS13,NO_SEASON;
	
	public String toString() {
		switch (this) {
		case LHLS13: return "LHL S13";
		default: return "";
		}
	}
	
	public static Season parseSeason(String str) {
		switch (str) {
		case "LHL S13": return LHLS13;
		default: return NO_SEASON;
		}
	}
}