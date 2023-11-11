package network.cmd;


import br.com.leandro.pong.model.GameState;
import game.MatchRoom;

import java.net.Socket;

public class SearchRoomSucessCommand extends Command {
	private static final long serialVersionUID = 1094719716851217045L;
	private MatchRoom matchRoom;

	public SearchRoomSucessCommand(MatchRoom matchRoom) {
		super(CommandType.SEARCH_ROOM_SUCESS);
		this.matchRoom = matchRoom;
	}

	@Override
	public void executeClient(Socket client, String player) {
		GameState.getInstance().setMatchRoom(matchRoom);
	}

}
