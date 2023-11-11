package network.cmd;

import br.com.leandro.pong.screen.multiplayer.MultiPlayerScreen;

import java.net.Socket;

public class SearchRoomFailedCommand extends Command {
	private static final long serialVersionUID = 1094719716851217045L;

	public SearchRoomFailedCommand() {
		super(CommandType.SEARCH_ROOM_FAILED);
	}

	@Override
	public void executeClient(Socket client, String player) {
		MultiPlayerScreen.setFeedBack("RoomID não existe");
	}
}
