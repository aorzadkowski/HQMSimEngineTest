package main;
import hqmdatabase.PlayerDBIO;
import tests.PlayerDatabaseTest;

public class ArgumentLineHandler {
	public static void processArguments(String[] args) {
		for (String arg: args) {
			if (arg.endsWith(".csv")) populateDB(arg);
			
			switch (arg) {
			case "testPlayerDB": testPlayerDB();
			case "debug" : debug();
			}
		}
	}
	
	private static void populateDB(String filePath) {
		PlayerDBIO.addFileToDB(filePath, AppMain.mainDB);
	}
	
	private static void testPlayerDB() {
		PlayerDatabaseTest.databaseTester();
	}
	
	private static void debug() {
		Options.debug = true;
	}
}
