package br.com.leandro.pong.session;

import network.cmd.Command;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class SessionUtils {
	

	public static void sendToAll(Map<String, Session> sessions, Command cmd) {
		for (Map.Entry<String, Session> entry : sessions.entrySet()) {
			sendToOne(entry.getValue().getClient(), cmd);
		}
	}

	public static void sendToList(List<Session> sessions, Command cmd) {
		for (Session session : sessions) {
			sendToOne(session.getClient(), cmd);
		}
	}
	
	public static void sendToOne(Socket socket, Command cmd) {
		ObjectOutputStream saida;
		try {
			saida = new ObjectOutputStream(socket.getOutputStream());
			saida.flush();
			saida.writeObject(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}