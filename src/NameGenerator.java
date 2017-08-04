
public class NameGenerator {
	private static String[] firstNames = {"A.", "B.", "C.", "D.", "E.", "F."};
	private static String[] lastNames = {"Test", "Testerson", "Demo", "Omed", "Testski", "Anderson", "Ron", "Ryan"};
	
	public static String getName() {
		return firstNames[(int) (Math.random() * firstNames.length)] + " " + lastNames[(int) (Math.random() * lastNames.length)];
	}
}
