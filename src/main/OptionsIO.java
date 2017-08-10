package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class OptionsIO {
	public static void readFile() {
		File file = new File("options.ini");
		
		if (!file.exists()) {
			System.err.println("Options file did not exist! Writing default values to it now.");
			writeDefaultsToNewFile();
		}
		
		Scanner in = null;
		try {
			in = new Scanner(file);
			
			int lineCount = 0;
			
			while (in.hasNextLine()) {
				String[] nextLine = in.nextLine().split("=");
				
				lineCount++;
				
				if (nextLine[1].contains("#")) {
					nextLine[1] = nextLine[1].substring(0, nextLine[1].indexOf('#'));

					nextLine = new String[] {nextLine[0], nextLine[1]};
				}
				
				if (nextLine[0].contains("#")) {
					continue;
				} else if (nextLine.length != 2) {
					System.err.println("Options File has improper formatting on line " + lineCount);
					return;
				}
				
				
				switch (nextLine[0]) {
				case "debug": handleDebug(nextLine[1]); break;
				case "simtype": handleSimtype(nextLine[1]); break;
				case "numberOfGames": handleNumberOfGames(nextLine[1]); break;
				case "numberOfSeasons": handleNumberOfSeasons(nextLine[1]); break;
				}
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} finally {
			in.close();
		}
	}
	
	private static void writeDefaultsToNewFile() {
		File file = new File("options.ini");
		
		PrintWriter writer = null;
		
		try {
			file.createNewFile();
			
			writer = new PrintWriter(file);
			
			writer.println("debug=false");
			writer.println("simtype=2");
			writer.println("numberOfGames=10");
			writer.println("numberOfSeasons=100000");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}
	
	/////////////////////////////////////////////////////
	// Below are the handler methods for lexing input. //
	/////////////////////////////////////////////////////
	
	private static void handleDebug(String in) {
		Options.debug = Boolean.parseBoolean(in.trim());
	}
	
	private static void handleSimtype(String in) {
		int temp = Integer.parseInt(in.trim());
		
		if (temp != 1 && temp != 2 && temp != 3) {
			System.err.println("Simtype value of " + temp + " is invalid.  Must be either 1, 2, or 3. Using default instead!");
		}
		
		Options.simtype = temp;
	}
	
	private static void handleNumberOfGames(String in) {
		int temp = Integer.parseInt(in.trim());
		
		if (temp < 0 && Options.simtype == 2) {
			System.err.println("Number of Games cannot be negative! Using default instead!");
			
			temp = 100;
		}
		
		Options.numberOfGames = temp;
	}
	
	private static void handleNumberOfSeasons(String in) {
		int temp = Integer.parseInt(in.trim());
		
		if (temp < 0 && Options.simtype == 3) {
			System.err.println("Number of Seasons cannot be negative! Using default instead!");
			
			temp = 100;
		}
		
		Options.numberOfSeasons = temp;
	}
}
