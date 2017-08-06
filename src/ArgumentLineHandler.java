
public class ArgumentLineHandler {
	public static void processArguments(String[] args) {
		for (String arg: args) {
			if (arg.contains(".csv")) populateDB(arg);
		}
	}
	
	public static void populateDB(String filePath) {
		
	}
	
	public static void verbose() {
		
	}
}
