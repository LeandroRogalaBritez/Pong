package network.cmd;

import br.com.leandro.pong.model.GameState;
import br.com.leandro.pong.screen.multiplayer.MultiPlayerScreen;

import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ConnectCommand extends Command {
	private static final long serialVersionUID = 2510692758746009498L;
	private List<String> players;
	private String yourPlayer;

	public ConnectCommand(String player) {
		super(CommandType.CONNECT);
		this.players = Arrays.asList(player);
	}

	public ConnectCommand(List<String> players, String yourPlayer) {
		super(CommandType.CONNECT);
		this.players = players;
		this.yourPlayer = yourPlayer;
	}
	
	public ConnectCommand(List<String> players) {
		super(CommandType.CONNECT);
		this.players = players;
	}

	public List<String> getPlayers() {
		return players;
	}
	
	public void setPlayers(List<String> players) {
		this.players = players;
	}

	@Override
	public void executeClient(Socket client, String player) {
		for (String p : players) {
			MultiPlayerScreen.setNewPlayer(p);
		}
		System.out.println(yourPlayer);
		if (yourPlayer != null) {
			GameState.getInstance().setYourPlayer(yourPlayer);
		}
	}
}