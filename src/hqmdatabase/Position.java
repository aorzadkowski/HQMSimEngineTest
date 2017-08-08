package hqmdatabase;

public enum Position {
	LW,C,LD,RD,G,NO_POS;
	
	public String toString() {
		switch (this) {
		case LW: return "LW";
		case C: return "C";
		case LD: return "LD";
		case RD: return "RD";
		case G: return "G";
		default: return "NO POS";
		}
	}
}
