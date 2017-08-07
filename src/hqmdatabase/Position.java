package hqmdatabase;

public enum Position {
	LW,C,LD,RD,NO_POS;
	
	public String toString() {
		switch (this) {
		case LW: return "LW";
		case C: return "C";
		case LD: return "LD";
		case RD: return "RD";
		default: return "NO POS";
		}
	}
}
