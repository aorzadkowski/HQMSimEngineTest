package hqmdatabase;
import java.util.ArrayList;

public class PlayerDatabase {
	private ArrayList<Player> data;
	
	private int size;
	
	public PlayerDatabase() {
		data = new ArrayList<>();
		size = 0;
	}
	
	public boolean addPlayer(Player newPlayer) {
		for (Player player : data) {
			if (newPlayer.getName().equals(player.getName()) && newPlayer.getSeason().equals(player.getSeason())) return false;
		}
		
		data.add(newPlayer);
		
		size++;
		
		return true;
	}
	
	public boolean contains(String name, String season) {
		for (Player player : data) {
			if (name.equals(player.getName()) && season.equals(player.getSeason())) return true;
		}
		
		return false;
	}
	
	public boolean contains(Player player) {
		return data.contains(player);
	}
	
	public int getSize() {
		return size;
	}
	
	public Player retrievePlayer(String name, String season) {
		for (Player player : data) {
			if (name.equals(player.getName()) && season.equals(player.getSeason())) return player;
		}
		
		return null;
	}
	
	public Player replacePlayer(Player newPlayer) {
		Player oldPlayer = new Player(retrievePlayer(newPlayer.getName(), newPlayer.getSeason()));
		
		if (!data.remove(oldPlayer)) System.out.println("Player could not be found!");
		
		return oldPlayer;
	}
	
	public ArrayList<String> getNames() {
		ArrayList<String> names = new ArrayList<>();
		
		for (Player player : data) {
			names.add(player.getName() + " " + player.getSeason());
		}
		
		return names;
	}
}