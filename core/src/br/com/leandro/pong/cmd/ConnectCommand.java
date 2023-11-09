package br.com.leandro.pong.cmd;

import java.util.Arrays;
import java.util.List;

public class ConnectCommand extends Command {
	private static final long serialVersionUID = 2510692758746009498L;
	private List<String> players;
	
	public ConnectCommand(String player) {
		super(CommandType.CONNECT);
		this.players = Arrays.asList(player);
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
	
}