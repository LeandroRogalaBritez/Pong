package br.com.leandro.pong.cmd;

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

}
