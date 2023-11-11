package network.cmd;

import br.com.leandro.pong.screen.menu.MenuScreen;
import br.com.leandro.pong.screen.multiplayer.MultiPlayerScreen;

import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class DisconnectCommand extends Command {
	private static final long serialVersionUID = 1094719716851217045L;
	private List<String> players;
	
	public DisconnectCommand(String player) {
		super(CommandType.DISCONNECT);
		this.players = Arrays.asList(player);
	}
	
	public DisconnectCommand(List<String> players) {
		super(CommandType.DISCONNECT);
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
			MultiPlayerScreen.removePlayer(p);
		}
	}
}
