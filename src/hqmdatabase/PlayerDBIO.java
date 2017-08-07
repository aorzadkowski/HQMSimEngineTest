package hqmdatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlayerDBIO {
	public static final String KEE = "Shitter,Season,Position,Role,Faceoffs,Long Shots,Point Shots,Wrist Shots,Boards Passing,Open Ice Passing,Breakout Receiving,Boards Receiving,Open Ice Receiving,Hand Eye,Stickhandling,Dangling,Possession,Shot Blocking,Hitting,Positioning,Pokecheck,Off. Awareness,Def. Awareness,Consistency";
	
	public static void addFileToDB(String filepath, PlayerDatabase db) {
		if (!filepath.endsWith(".csv")) {
			System.err.println("Filepath does not end with \".csv\".");
			return;
		} else if (filepath.length() < 1) {
			System.err.println("Filepath must not be an empty string.");
		}
		
		Scanner scan = null;
		
		try {
			scan = new Scanner( new File(filepath) );
			
			String headerLine = scan.nextLine();
			
			if (!headerLine.equals(KEE)) {
				System.err.println("File does not have proper header.");
				return;
			}
			
			int rowCounter = 0;
			
			while (scan.hasNextLine()) {
				String in = scan.nextLine();
				rowCounter++;
				
				String[] splitString = in.split(",");
				
				if (splitString.length != 24) { //24 is the number of elements per row in our desired sim.  (4 for identification, 20 stats)
					System.err.println("Invalid number of elements in row: " + rowCounter);
					return;
				}
				
				String name = splitString[0];
				Season season = parseSeason(splitString[1]);
				Position position = parsePosition(splitString[2]);
				Role role = parseRole(splitString[3]);
				
				int[] stats = new int[20];
				
				for (int i = 4; i < splitString.length; i++) {
					try {
						stats[i - 4] = Integer.parseInt(splitString[i]);
					} catch (NumberFormatException E) {
						System.err.println("PlayerDBIO: Non parsable value was found at row,element(" + rowCounter + ", " + i + ").");
						return;
					}
				}
				
				db.addPlayer(new Player(name, season, position, role, stats));
			}
			
		} catch (FileNotFoundException E) {
			System.err.println("PlayerDBIO: filepath not found.");
			E.printStackTrace();
			return;
		} finally {
			scan.close();
		}
	}
	
	private static Role parseRole(String role) {
		switch (role) {
		case "Sniper": return Role.SNIPER;
		case "Playmaker": return Role.PLAYMAKER;
		case "Dangler": return Role.DANGLER;
		case "Two Way Forward": return Role.TWO_WAY_FORWARD;
		case "Offensive Defenceman": return Role.OFFENSIVE_DMAN;
		case "Defensive Defenceman": return Role.DEFENSIVE_DMAN;
		default: return Role.NO_ROLE;
		}
	}
	
	private static Position parsePosition(String position) {
		switch (position) {
		case "LW": return Position.LW;
		case "C": return Position.C;
		case "LD": return Position.LD;
		case "RD": return Position.RD;
		default: return Position.NO_POS;
		}
	}
	
	private static Season parseSeason(String season) {
		switch (season) {
		case "LHL S13": return Season.LHLS13;
		default: return Season.NO_SEASON;
		}
	}
}