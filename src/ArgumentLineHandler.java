import hqmdatabase.PlayerDBIO;

public class ArgumentLineHandler {
	public static void processArguments(String[] args) {
		for (String arg: args) {
			if (arg.endsWith(".csv")) populateDB(arg);
		}
	}
	
	public static void populateDB(String filePath) {
		PlayerDBIO.addFileToDB(filePath, AppMain.mainDB);
	}
	
	public static void verbose() {
		
	}
}
